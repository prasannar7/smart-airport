package com.prasanna.sim;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prasanna.constant.FlightAction;
import com.prasanna.constant.RunwayStatus;
import com.prasanna.model.Flight;
import com.prasanna.model.Runway;
import com.prasanna.util.BooleanSource;
import com.prasanna.util.PropertiesUtil;

/**
 * This class contains code for simulating a smart airport which has only one runway.
 * No two or more flights can share the runway, only one flight can use the runway either for landing or take off.
 * 
 * @author pramakr2
 *
 */
public class SmartAirportSimulator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmartAirportSimulator.class);

	/**
	 * Simulation time - how many times the simulate {@link #simulate()} should get executed. 
	 * Basically used to avoid infinite situation.
	 * 
	 * SIMULATION_LENGTH - Reads simulation length from the configuration file 'smart-airport.properties'.
	 * Property key 'sa.sim.simulation.length'.
	 */
	public static final int SIMULATION_LENGTH = Integer.parseInt(PropertiesUtil.get("sa.sim.simulation.length"));
	
	/**
	 * LANDING_PROBABILITY - Reads landing probability from the configuration file 'smart-airport.properties'.
	 * Property key 'sa.sim.langing.probability'.
	 */
	public static final double LANDING_PROBABILITY = Double.parseDouble(PropertiesUtil.get("sa.sim.langing.probability"));
	
	/**
	 * TAKEOFF_PROBABILITY - Reads take off probability from the configuration file 'smart-airport.properties'.
	 * Property key 'sa.sim.takeoff.probability'.
	 */
	public static final double TAKEOFF_PROBABILITY = Double.parseDouble(PropertiesUtil.get("sa.sim.takeoff.probability"));
	
	/**
	 * DELTA_WAIT_TIME - Reads delta wait time from the configuration file 'smart-airport.properties'.
	 * Property key 'sa.sim.delta.wait.time'.
	 */
	public static final int DELTA_WAIT_TIME = Integer.parseInt(PropertiesUtil.get("sa.sim.delta.wait.time"));
	
	/**
	 * Queue holds flights {@link Flight} waiting for take off.
	 */
	private Queue<Flight> takeOffQ; 
	
	/**
	 * Queue holds flights {@link Flight} waiting for landing.
	 */
	private Queue<Flight> landingQ;
	
	/**
	 * List holds flights {@link Flight} that are generated dynamically.
	 */
	private LinkedList<Flight> dynamicallyGeneratedFlights; 

	/**
	 * BooleanSource {@link BooleanSource}} provides a random sequence of boolean values.
	 * Basically used to create flights dynamically based on the randomly generated boolean value(true) and take off probability.
	 */
	private BooleanSource wantsToTakeOff;
	
	/**
	 * BooleanSource {@link BooleanSource}} provides a random sequence of boolean values.
	 * Basically used to create flights dynamically based on the randomly generated boolean value(true) and landing probability.
	 */
	private BooleanSource wantsToLand;

	/**
	 * The runway {@link Runway} which will be used by the flights to take off or landing.
	 */
	private Runway runway;

	/**
	 * The following attributes are used to capture results on the simulation.
	 */

	/**
	 * Total number of flights that have landed.
	 */
	int totalFlightsLanded; 
	
	/**
	 * Total number of flights that have taken off.
	 */
	int totalFlightsTakenOff;
	
	/**
	 * Flag to check any flight crashed or not
	 */
	boolean flightCrashed;

	/**
	 * Default constructor.
	 */
	public SmartAirportSimulator() {
		wantsToLand = new BooleanSource(LANDING_PROBABILITY);
		wantsToTakeOff = new BooleanSource(TAKEOFF_PROBABILITY);

		runway = Runway.getRunway();

		takeOffQ = new LinkedList<Flight>();
		landingQ = new LinkedList<Flight>();
		dynamicallyGeneratedFlights = new LinkedList<Flight>();

		totalFlightsLanded = 0;
		totalFlightsTakenOff = 0;
	}

	/**
	 * Simulates smart airport by creating flights dynamically using BooleanSource{@link BooleanSource} either for take off or land.
	 * It assigns flights to the runway {@link Runway} either for takeoff or land.
	 * It clears the runway {@link Runway} for the next flight either for take off or land.
	 * Since landing takes priority over taking off. Runway {@link Runway} will be used by the flight that wants to take off, only when no flight wants to land. 
	 * It also populates attributes for capturing results.
	 */
	public void simulate() {

		for (int t = 0; t <= SIMULATION_LENGTH; t++) {
			/*
			 * Checks whether flight wants to take off.
			 * If yes then dynamically generate flight and add it takeOffQ and dynamicallyGeneratedFlights
			 */
			if (wantsToTakeOff.random()) {
				Flight fligt = new Flight(FlightAction.TAKING_OFF);
				takeOffQ.offer(fligt);
				dynamicallyGeneratedFlights.add(fligt);
			}

			/*
			 * Checks whether flight wants to land.
			 * If yes then dynamically generate flight and add it landingQ and dynamicallyGeneratedFlights
			 */
			if (wantsToLand.random()) {
				Flight flight = new Flight(FlightAction.LANDING);
				landingQ.offer(flight);
				dynamicallyGeneratedFlights.add(flight);
			} 
			/*
			 * Runway utilization. Since landing takes priority over taking off. If one flight wants to take off, one flight wants to land at the same time 
			 * and the runway is free then flight that wants to land will be given priority. Runway will be used by the flight that wants to take off, 
			 * only when no flight wants to land and runway is free. Means landingQ will be processed first for every loop. If landingQ is empty then only takeOffQ will be
			 * processed.
			 */
			if (runway.isClear()) {
				if (landingQ.peek() != null) {
					/*
					 * Remove from the landingQ and assign it to runway
					 */
					Flight landingFlight = landingQ.poll();
					runway.useRunway(landingFlight, RunwayStatus.BUSY);
					
					/*
					 * Remove from the dynamicallyGeneratedFlights
					 */
					dynamicallyGeneratedFlights.remove(landingFlight);
				} else if (takeOffQ.peek() != null) {
					/*
					 * Remove from the takeOffQ and assign it to runway
					 */
					Flight takeOffFlight = takeOffQ.poll();
					runway.useRunway(takeOffFlight, RunwayStatus.BUSY);
					
					/*
					 * Remove from the dynamicallyGeneratedFlights
					 */
					dynamicallyGeneratedFlights.remove(takeOffFlight);
				}
			} else {
				/*
				 * Clears runway for next flight either take off or landing
				 */
				FlightAction flightStatus = runway.clearRunWay();
				if(flightStatus.equals(FlightAction.LANDED)){
					/*
					 * Count number of flights successfully landed
					 */
					totalFlightsLanded++;
				}else if(flightStatus.equals(FlightAction.TAKEN_OFF)){
					/*
					 * Count number of flights successfully taken off
					 */
					totalFlightsTakenOff++;
				}
			}
			
			/*
			 * Update delta wait time for all the flights which are waiting for either take off or landing.
			 */
			Iterator<Flight> it = dynamicallyGeneratedFlights.iterator();
			while (it.hasNext()) {
				Flight flight = it.next();
				flight.updateWaitTime(DELTA_WAIT_TIME);
				if(FlightAction.LANDING.equals(flight.getFaction())){
					LOGGER.info("Flight Number: "+flight.getNumber()+", Flight action: "+FlightAction.LANDING+ " Using runway: FALSE, Wait time: "+flight.getWaitTime());
				}else if(FlightAction.TAKING_OFF.equals(flight.getFaction())){
					LOGGER.info("Flight Number: "+flight.getNumber()+", Flight action: "+FlightAction.TAKING_OFF+" Using runway: FALSE, Wait time: "+flight.getWaitTime());
				}
			}
		}
		
		/*
		 * Kill the runway instance once the simulation length is reached.
		 */
		Flight flightOnRuway = runway.getFlight();
		runway=null;
		
		/*
		 * If simulation exceeds SIMULATION_LENGTH then runway will be killed so flight which is either taking off or landing would be crashed.
		 */
		if(null != flightOnRuway){
			flightCrashed=true;
			LOGGER.info("\n");
			LOGGER.info("?????????????????????????????????????????????????????? Flight crashed!! ????????????????????????????????????????????????????");
			LOGGER.info("\n");
			LOGGER.info("Flight crashed!! Runway no longer available because it is killed by the simulator.");
			LOGGER.info("Flight Number: "+flightOnRuway.getNumber()+", Flight action: "+flightOnRuway.getFaction().toString());
			LOGGER.info("TIME needed is "+(flightOnRuway.getFaction().equals(FlightAction.LANDING)?(flightOnRuway.getLandingTime()):(flightOnRuway.getTakeOffTime()))+" for "+flightOnRuway.getFaction().toString());
		}
		
	}

	/**
	 * Generates report for simulation result.
	 */
	public void report() {
		LOGGER.info(totalFlightsLanded + " Flights have landed.");
		LOGGER.info(totalFlightsTakenOff + " Flights have taken off.");
		LOGGER.info(landingQ.size() + " Flights waiting to land.");
		LOGGER.info(takeOffQ.size() + " Flights waiting to takeoff.");
		LOGGER.info((landingQ.size()+takeOffQ.size())+" Flights available in the airport.");
		if(flightCrashed){
			LOGGER.info("1 Flight crashed!!");
		}
	}

	/**
	 * Execute the simulator.
	 * 
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		UUID randomNumber = java.util.UUID.randomUUID();
		LOGGER.info("\n");
		LOGGER.info("---------------------------------------------------------------------------------------------------------------------------");
		LOGGER.info("\n");
		LOGGER.info("Simulation Id: "+randomNumber+" and Simulation starts at: "+ new Date());
		LOGGER.info("\n");
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		LOGGER.info("\n");
		LOGGER.info("############################################################################################################################");
		LOGGER.info("################################################# Smart Airport Simulator ##################################################");
		LOGGER.info("############################################################################################################################");
		LOGGER.info("\n");
		SmartAirportSimulator sim = new SmartAirportSimulator();
		sim.simulate();
		LOGGER.info("\n");
		LOGGER.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Simulation Results $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.info("\n");
		sim.report();
		LOGGER.info("\n");
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		LOGGER.info("\n");
		LOGGER.info("Simulation Id: "+randomNumber+" and Simulation ends at: "+ new Date());
		LOGGER.info("\n");
		LOGGER.info("----------------------------------------------------------------------------------------------------------------------------");
		LOGGER.info("\n");
	}
}