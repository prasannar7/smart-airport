package com.prasanna.constant;

/**
 * 
 * This class contains different status that a runway can have and two possible values are,
 * 		1. BUSY (Runway is in use).
 * 		2. FREE (Runway is free).
 * 
 * @author pramakr2
 *
 */
public enum RunwayStatus {
	/**
	 * 
	 * Represents runway is used by flight either for landing or take off.
	 * 
	 */
	BUSY,
	
	/**
	 * 
	 * Represents runway is free.
	 * 
	 */
	FREE
}
