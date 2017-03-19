package com.prasanna.test;

import java.util.Date;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prasanna.sim.SmartAirportSimulator;
import com.prasanna.util.PropertiesUtil;

/**
 * Class which tests the SmartAirportSimulator
 * 
 * @author pramakr2
 * 
 */
public class SmartAirportSimulatorTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmartAirportSimulatorTest.class);
	
	/**
	 * The reference to SmartAirportSimulator {@link SmartAirportSimulator} which will be used by the test class to execute the simulation.
	 */
	private static SmartAirportSimulator smartAirportSimulator=null;
	
	/**
	 * Initialize SmartAirportSimulator {@link SmartAirportSimulator} before test begins.
	 * Loads configurations from the 'smart-airport-test.properties' file using PropertiesUtil {@link PropertiesUtil}.
	 */
	@BeforeClass
    public static void initialize() {
		PropertiesUtil.loadProperties("smart-airport-test", "log4j-test");
		smartAirportSimulator = new SmartAirportSimulator();
    }
	
	/**
	 * Execute the simulator SmartAirportSimulator {@link SmartAirportSimulator}
	 */
	@Test
	public void executeSimulator(){
		UUID randomNumber = java.util.UUID.randomUUID();
		LOGGER.info("\n");
		LOGGER.info("----------------------------------------------------------------------------------------------------------------------------");
		LOGGER.info("\n");
		LOGGER.info("Simulation Id: "+randomNumber+" and Simulation starts at: "+ new Date());
		LOGGER.info("\n");
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		LOGGER.info("\n");
		LOGGER.info("############################################################################################################################");
		LOGGER.info("################################################# Smart Airport Simulator ##################################################");
		LOGGER.info("############################################################################################################################");
		LOGGER.info("\n");
		smartAirportSimulator.simulate();
		LOGGER.info("\n");
		LOGGER.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Simulation Results $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		LOGGER.info("\n");
		smartAirportSimulator.report();
		LOGGER.info("\n");
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ End @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		LOGGER.info("\n");
		LOGGER.info("Simulation Id: "+randomNumber+" and Simulation ends at: "+ new Date());
		LOGGER.info("\n");
		LOGGER.info("----------------------------------------------------------------------------------------------------------------------------");
		LOGGER.info("\n");
	}
	
	/**
	 * Destroy SmartAirportSimulator {@link SmartAirportSimulator} before test ends.
	 */
	@AfterClass
    public static void destroy() {
		smartAirportSimulator = null;
    }

}
