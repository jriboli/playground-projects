/**
 * 
 */
package com.promineotech.weather;



/**
 * This interface defines a single method that can be called by the {@link WeatherNews} object.
 * 
 * @author Promineo
 *
 */
public interface WeatherService {

  /**
   * Generate a random weather event and report.
   * 
   * @return The {@link Weather} object
   */
  Weather getRandomWeather();

}
