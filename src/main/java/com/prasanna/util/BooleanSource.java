package com.prasanna.util;

/**
 * BooleanSource provides a random sequence of boolean values based on the probability and {@link Math#random()}.
 * 
 * @author pramakr2
 *
 */
public class BooleanSource {
	
	/**
	 * Probability used along with {@link Math#random()} to generate random boolean values.
	 * Probability value is expressed between 0 and 1. Higher the value, higher the probability.
	 * Lower the value, lower the probability.
	 */
	private double probability; 

	/**
	 * Parameterized constructor.
	 * 
	 * @param probability Probability between 0 and 1
	 * 
	 * @exception IllegalArgumentException
	 *            Indicates that probability is outside of its range and it should be between 0 and 1.
	 */
	public BooleanSource(double probability) {
		if ((probability < 0) || (1 < probability))
			throw new IllegalArgumentException("Illegal probability: " + probability);
		this.probability = probability;
	}

	/**
	 * Generates random boolean value either true or false.
	 * It generates based on the probability and {@link Math#random()}.
	 * 
	 * @return boolean
	 */
	public boolean random() {
		return (Math.random() < probability);
	}

}