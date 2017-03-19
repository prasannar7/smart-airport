package com.prasanna.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prasanna.constant.FlightAction;
import com.prasanna.constant.RunwayStatus;

/**
 * 
 * This class contains code to model a runway.The runway is either empty or hasa flight on it.
 * The flight remains on the runway for the amount of time needed for either take off or land.
 * 
 * @author pramakr2
 *
 */
public class Runway {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Runway.class);
	
	/**
	 * Static member to hold only one instance of runway.
	 */
	private static Runway runway=null;
	
	/**
	 * The flight {@link Flight} which is currently on runway for either landing or take off.
	 */
	private Flight flight;
	
	/**
	 * The runway status {@link RunwayStatus} either BUSY {@link RunwayStatus#BUSY} or FREE {@link RunwayStatus#FREE}.
	 * 		
	 */
	private RunwayStatus rstatus;
	
	/**
	 * Its a time counter which keeps track of how long the flight is on the runway. It helps in clearing off runway.
	 */
	private int time; 
	
	/**
	 * To prevent instantiation of Runway class from outside the class.
	 */
	private Runway(){
		
	}
	
	/**
	 * Provides global point of access to the Singleton object of Runway and returns the instance to the caller.
	 * 
	 * @return Runway instance
	 */
	public static Runway getRunway(){
		if (runway == null){
		      synchronized(Runway.class){ 
		        if (runway == null){
		            runway = new Runway();
		            runway.rstatus = RunwayStatus.FREE;
		        }
		    }
		}
		return runway;
	}
	
	/**
	 * Gets the flight {@link Flight} on runway	
	 * 
	 * @return flight {@link Flight}
	 */
	public Flight getFlight() {
		return flight;
	}
	
	/**
	 * Checks whether runway is free or not.
	 * 
	 * @return boolean
	 */
	public boolean isClear() {
		return (flight == null && RunwayStatus.FREE.equals(rstatus) ? true : false); 
	}

	
	/**
	 * 
	 * @param flight The flight {@link Flight} which is going to either take off or land {@link FlightAction}
	 * @param rstatus The runway status {@link RunwayStatus} either BUSY {@link RunwayStatus#BUSY} or FREE {@link RunwayStatus#FREE}.
	 */
	public void useRunway(Flight flight, RunwayStatus rstatus) {
		if (this.flight != null)
			throw new RuntimeException("Flight crash!! Multiple flights on the runway at the same time.");
		time = 1;
		this.flight = flight;
		this.rstatus = rstatus;
		LOGGER.info("Flight Number: "+flight.getNumber()+", Flight action: "+flight.getFaction().toString()+" Using runway: TRUE, Current "+ flight.getFaction().toString()+" time: "+(flight.getFaction().equals(FlightAction.LANDING)?(flight.getLandingTime()):(flight.getTakeOffTime())));
	}

	/**
	 * Clears the runway based on the flight {@link Flight} take off or land time.
	 * 
	 * @return The flight action {@link FlightAction} either LALDNING {@link FlightAction#LANDING} or TAKING_OFF {@link FlightAction#LANDING} or LANDED {@link FlightAction#LANDED} or TAKEN_OFF {@link FlightAction#TAKEN_OFF}
	 */
	public FlightAction clearRunWay() {
		if (flight.getFaction().equals(FlightAction.LANDING) && time >= flight.getLandingTime()){
			LOGGER.info("Flight Number: "+flight.getNumber()+" successfully landed.");
			flight = null;
			rstatus = RunwayStatus.FREE;
			return FlightAction.LANDED;
		}else if (flight.getFaction().equals(FlightAction.TAKING_OFF) && time >= flight.getTakeOffTime()){
			LOGGER.info("Flight Number: "+flight.getNumber()+" successfully taken off.");
			flight = null;
			rstatus = RunwayStatus.FREE;
			return FlightAction.TAKEN_OFF;
		}else{
			LOGGER.info("Flight Number: "+flight.getNumber()+", Flight action: "+flight.getFaction().toString()+" Using runway: TRUE, Current "+ flight.getFaction().toString()+" time: "+(flight.getFaction().equals(FlightAction.LANDING)?(flight.getLandingTime()-time):(flight.getTakeOffTime()-time)));
			time++;
		}
		
		return flight.getFaction().equals(FlightAction.LANDING)? FlightAction.LANDING:FlightAction.TAKING_OFF;
	}

}
