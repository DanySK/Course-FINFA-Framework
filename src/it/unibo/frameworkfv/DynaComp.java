/**
 * 
 */
package it.unibo.frameworkfv;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.sun.tools.javac.api.JavacTool;

/**
 * @author Danilo Pianini
 * 
 */
public final class DynaComp {

	private static final JavaCompiler JAVAC = JavacTool.create();
	private static final PrintStream ERR = System.err;

	private DynaComp() {
	}

	public static Class<?> compileAndLoad(final String name, final String java) throws ClassNotFoundException {
		if(JAVAC == null){
			throw new NoJDKException();
		}
		final JavaFileManager fileManager = new ClassFileManager(JAVAC.getStandardFileManager(null, null, null));
		final List<JavaFileObject> jfiles = new ArrayList<>(1);
		jfiles.add(new CharSequenceJavaFileObject(name, java));
		if (JAVAC.getTask(null, fileManager, null, null, null, jfiles).call()) {
			return fileManager.getClassLoader(null).loadClass(name);
		}
		return null;
	}

	public static String interpret(final String className, final String javaCode) {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			System.setErr(new PrintStream(baos));
			Class<?> clazz = DynaComp.compileAndLoad(className, javaCode);
			System.setErr(ERR);
			if (clazz == null) {
				return "Syntax error!\n" + baos.toString() + "\n\nPure Java code equivalent to your interpretation request:\n" + javaCode;
			}
			Object myClass = clazz.newInstance();
			Method getResult = clazz.getMethod("getResult", clazz.getClasses());
			return getResult.invoke(myClass).toString();
		} catch (InvocationTargetException e) {
			return "Runtime error!\n" + e.getCause() + "\n\nPure Java code equivalent to your interpretation request:\n" + javaCode;
		} catch (NoJDKException e) {
			return "JDK TOOLKIT NOT FOUND! Try to run this program from the shell.\n\n" + ExceptionUtils.getStackTrace(e) + "\n\nGenerated code:\n" + javaCode;
		} catch (Exception e) {
			return "Internal error! Please report the following to the course tutor, along with your code:\n\n" + ExceptionUtils.getStackTrace(e) + "\n\nGenerated code:\n" + javaCode;
		}

	}

}
