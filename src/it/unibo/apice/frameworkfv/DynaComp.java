/**
 * 
 */
package it.unibo.apice.frameworkfv;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author Danilo Pianini
 * 
 */
public final class DynaComp {

	private static final JavaCompiler JAVAC = ToolProvider.getSystemJavaCompiler();

	private DynaComp() {
	}

	public static Class<?> compileAndLoad(final String name, final String java) throws ClassNotFoundException {
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
			Class<?> clazz = DynaComp.compileAndLoad(className, javaCode);
			if (clazz == null) {
				return "Synctactic error!\n\nPure Java code equivalent to your interpretation request:\n" + javaCode;
			}
			Object myClass = clazz.newInstance();
			Method getResult = clazz.getMethod("getResult", clazz.getClasses());
			return getResult.invoke(myClass).toString();
		} catch (Exception e) {
			return "Internal error! Please report the following to the course tutor, along with your code:\n\n" + ExceptionUtils.getStackTrace(e) + "\n\nGenerated code:\n"+javaCode;
		}

	}

}
