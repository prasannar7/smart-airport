package com.prasanna.constant;

/**
 * 
 * This class contains different actions that a flight can have and three possibel values are,
 * 		1. LANDING (Flight is on runway).
 * 		2. TAKING_OFF (Flight is on runway).
 * 		3. LANDED (Flight landed successfully).
 * 		4. TAKEN_OFF (Flight taken off successfully).
 * 		5. NONE (Flight is not on runway).
 * 
 * @author pramakr2
 *
 */
public enum FlightAction {
	
	/**
	 * 
	 * Represents flight is landing.
	 * 
	 */
	LANDING,
	
	/**
	 * 
	 * Represents flight is taking off.
	 * 
	 */
	TAKING_OFF,
	
	/**
	 * 
	 * Represents flight landed successfully.
	 * 
	 */
	LANDED,
	
	/**
	 * 
	 * Represents flight taken off successfully.
	 * 
	 */
	TAKEN_OFF,
	
	/**
	 * 
	 * Represents flight is not on runway.
	 * 
	 */
	NONE
	
}