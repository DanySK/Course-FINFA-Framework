/**
 * 
 */
package it.unibo.apice.frameworkfv;

import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

/**
 * @author Danilo Pianini
 * 
 */
public final class DynaComp {

	private static final JavaCompiler JAVAC = ToolProvider.getSystemJavaCompiler();

	private DynaComp() {
	}

	public static Class<?> compileAndLoad(String name, String java) throws ClassNotFoundException {
		final JavaFileManager fileManager = new ClassFileManager(JAVAC.getStandardFileManager(null, null, null));
		final List<JavaFileObject> jfiles = new ArrayList<>(1);
		jfiles.add(new CharSequenceJavaFileObject(name, java));
		if(JAVAC.getTask(null, fileManager, null, null, null, jfiles).call()){
			return fileManager.getClassLoader(null).loadClass(name);
		}
		return null;
	}

}
