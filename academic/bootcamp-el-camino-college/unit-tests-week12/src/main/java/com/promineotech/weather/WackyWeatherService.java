/**
 * 
 */
package com.promineotech.weather;

import java.util.Random;

/**
 * This class just supplies random weather data to the {@link WeatherNews} object.
 * 
 * @author Promineo
 *
 */
public class WackyWeatherService implements WeatherService {

  private String[] statesAndTerritories = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
      "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana",
      "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
      "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
      "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
      "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
      "West Virginia", "Wisconsin", "Wyoming", "American Samoa", "Guam", "Northern Mariana Islands", "Puerto Rico",
      "Virgin Islands"};

  private Random random = new Random();

  /**
   * Returns a randomly-generated weather event.
   */
  public Weather getRandomWeather() {
    return getWeatherFromServerThatWeHaveNoControlOver();
  }

  /**
   * This method represents calling an <em>actual</em> weather service that would presumably return something close to
   * reality. Reality is not a concern for this weather service so this method just generates some made up values.
   * 
   * @return
   */
  private Weather getWeatherFromServerThatWeHaveNoControlOver() {
    String stateOrTerritory = statesAndTerritories[random.nextInt(statesAndTerritories.length)];

    float high = random.nextFloat() * 100 + 40;
    float low = high - random.nextFloat() * 40 - 20;

    String report = String.format("The weather report for %s today predicts a high of %.1f and a low of %.1f",
        stateOrTerritory, high, low);

    return new Weather(stateOrTerritory, high, low, report);
  }
}
