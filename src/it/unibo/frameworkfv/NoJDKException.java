/**
 * 
 */
package it.unibo.frameworkfv;

/**
 * @author Danilo Pianini
 *
 */
public class NoJDKException extends RuntimeException {
	
	private static final long serialVersionUID = -5716773340346917359L;
	private final static StringBuilder sb = new StringBuilder();
	static {
		sb.append("This program requires the JDK to run, ");
		sb.append("you launched it within a JRE instead.\n");
	}

	/**
	 * 
	 */
	public NoJDKException() {
		super(sb.toString());
	}

}
