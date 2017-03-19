package com.prasanna.util;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.PropertyConfigurator;

/**
 * This class loads configurations for the smart-airport project.
 * By default it loads configurations from the 'smart-airport.properties file'.
 * 
 * @author pramakr2
 *
 */
public class PropertiesUtil {

	/**
	 * Holds configurations for the smart-airport project. 
	 */
	private static Properties props = new Properties();
	
	/*
	 * When the class is loaded, It loads configurations from the 'smart-airport.properties' file.
	 * It loads logging configurations from the given log4j properties file.
	 */
	static{
		props.clear();
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("smart-airport");
			Enumeration<String> keys = bundle.getKeys();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				props.put(key, bundle.getString(key));
			}
		} catch (Exception e) {
			System.out.println("Exception loading properties :: " + e);
		}
		initLog4j();
	}
	
	/**
	 * Loads configurations from the specified property file and overrides the default configurations.
	 * Also it loads logging configurations from the given log4j property file and overrides the default logging configurations.
	 * 
	 * @param name Property file name which holds configurations for the smart-airport project
	 * @param log4jName Property file name which holds logging configurations for the smart-airport project
	 */
	public static void loadProperties(String name, String log4jName) {
        props.clear();
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(name);
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                props.put(key, bundle.getString(key));
            }
        } catch (Exception e) {
        	System.out.println("Exception loading properties :: " + e);
        }
        initLog4j(log4jName);
    }

	/**
	 * Gets the property value by key
	 * 
	 * @param key Property key name
	 * @return Property value
	 */
	public static String get(String key) {
		return props.getProperty(key);
	}
	
	private static Properties getProperties(String name) {
        Properties properties = new Properties();
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(name);
            Enumeration<String> keys = bundle.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                properties.put(key, (String) bundle.getObject((String) key));
            }
        } catch (Exception e) {
            System.out.println("Exception while reading the values from the file :: " + name + e);
        }

        return properties;
    }
	
	/**
	 * Initializes the log4j logging framework and loads logging configurations from the 'log4j.properties' file.
	 */
	private static final void initLog4j() {
    	Properties props = getProperties("log4j");
    	PropertyConfigurator.configure(props);
    }
	
	/**
	 * Initializes the log4j logging framework and loads logging configurations from the the given log4j properties file and overrides default logging configurations.
	 */
	private static final void initLog4j(String name) {
    	Properties props = getProperties(name);
    	PropertyConfigurator.configure(props);
    }

}