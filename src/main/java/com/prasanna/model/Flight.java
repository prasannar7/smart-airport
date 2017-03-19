package com.prasanna.model;

import com.prasanna.constant.FlightAction;
import com.prasanna.util.PropertiesUtil;

/**
 * 
 * This class contains code to model a simple flight, used for simulating flight which consists of three attributes,
 * 		1. waitTime - The time the flight is waiting for either landing or taking off.
 * 		2. takeOffTime - The time needed by flight for taking off.
 * 		3. landingTime - The time needed by flight for landing.
 * 
 * @author pramakr2
 *
 */
public class Flight {

	/**
	 * The flight number.
	 */
	private String number;
	/**
	 * The time the flight is waiting for either landing or taking off.
	 */
	private int waitTime; 
	/**
	 * The time needed by flight for taking off.
	 */
	private int takeOffTime;
	/**
	 * The time needed by flight for landing.
	 */
	private int landingTime;
	
	/**
	 * The flight action {@link FlightAction} either LANDING or TAKING_OFF.
	 * 		
	 */
	private FlightAction faction;
	
	/**
	 * WAIT_TIME - Reads wait time from the configuration file 'smart-airport.properties'.
	 * Property key 'sa.flight.wait.time'.
	 */
	public static final int WAIT_TIME = Integer.parseInt(PropertiesUtil.get("sa.flight.wait.time"));
	
	/**
	 * TAKEOFF_TIME - Reads take off time from the configuration file 'smart-airport.properties'.
	 * Property key 'sa.flight.takeoff.time'.
	 */
	public static final int TAKEOFF_TIME = Integer.parseInt(PropertiesUtil.get("sa.flight.takeoff.time"));
	
	/**
	 * LANDING_TIME - Reads landing time from the configuration file 'smart-airport.properties'.
	 * Property key 'sa.flight.landing.time'.
	 */
	public static final int LANDING_TIME = Integer.parseInt(PropertiesUtil.get("sa.flight.landing.time"));

	/**
	 * Parameterized constructor
	 * 
	 * @param faction The flight action {@link FlightAction} either LANDING or TAKING_OFF.
	 */
	public Flight(FlightAction faction) {
		number=String.valueOf(java.util.UUID.randomUUID());
		waitTime = WAIT_TIME;
		takeOffTime = TAKEOFF_TIME;
		landingTime = LANDING_TIME;
		this.faction = faction;
	}
	
	/**
	 * Gets the flight number.
	 * 
	 * @return flight number.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the flight number.
	 * 
	 * @param number flight number.
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Get waitTime for the flight either for landing or taking off.
	 * 
	 * @return waitTime.
	 * 
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Get takingOffTime for the fight to take off.
	 * 
	 * @return takeOffTime.
	 * 
	 */
	public int getTakeOffTime() {
		return takeOffTime;
	}

	/**
	 * Get landingTime for the flight to land.
	 * 
	 * @return landingTime.
	 * 
	 */
	public int getLandingTime() {
		return landingTime;
	}

	/**
	 * Update waitTime for the flight either for taking off or landing.
	 * 
	 * @param delta Delta wait time value.
	 * 
	 */
	public void updateWaitTime(int delta) {
		waitTime += delta;
	}

	/**
	 * Gets flight action either LANDING {@link FlightAction#LANDING} or TAKING_OFF {@link FlightAction#TAKING_OFF}
	 * 
	 * @return flight action {@link FlightAction}
	 */
	public FlightAction getFaction() {
		return faction;
	}
	
}