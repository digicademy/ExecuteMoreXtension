/**
 * ExecuteMoreOSUtils.java - is a helper class providing OS-related methods as used within the ExecuteMoreMoreXtension developed at the Digital Academy
 *  of the Academy of Sciences and Literature | Mainz.
 * @author Patrick D. Brookshire
 * @version 0.1.0
 */
package org.adwmainz.da.extensions.executemore.utils;

public class OSUtils {
	
	// constant value
	protected static final String OS_NAME = System.getProperty("os.name").toLowerCase();
	
	// methods
	/**
	 * Returns <code>true</code> if invoked from a Windows OS
	 */
	public static boolean isWindows() {
		return OS_NAME.contains("win");
	}

	/**
	 * Returns <code>true</code> if invoked from a Mac OS
	 */
	public static boolean isMac() {
		return OS_NAME.contains("mac");
	}

	/**
	 * Returns <code>true</code> if invoked from a Unix OS
	 */
	public static boolean isUnix() {
		if (OS_NAME.contains("nix"))
			return true;
		if (OS_NAME.contains("nux"))
			return true;
		if (OS_NAME.contains("aix"))
			return true;
		return false;
	}

}
