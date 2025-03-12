/**
 * 
 */
package com.promineotech.weather;

/**
 * This class creates an immutable object that contains weather information. It is used to pass weather information to
 * various parts of the application.
 * 
 * @author Promineo
 *
 */
public class Weather {
  private String region;
  private float high;
  private float low;
  private String report;

  /**
   * The constructor. All instance variables are set in this constructor. Once set, they cannot be changed because the
   * class does not define any setters.
   * 
   * @param region The region being reported on
   * @param high The forecasted high temperature
   * @param low The forecasted low temperature
   * @param report A one-line weather report
   */
  public Weather(String region, float high, float low, String report) {
    super();
    this.region = region;
    this.high = high;
    this.low = low;
    this.report = report;
  }

  /**
   * @return the region
   */
  public String getRegion() {
    return region;
  }

  /**
   * @return the high
   */
  public float getHigh() {
    return high;
  }

  /**
   * @return the low
   */
  public float getLow() {
    return low;
  }

  /**
   * @return the report
   */
  public String getReport() {
    return report;
  }

  /**
   * This converts the Weather object to a String representation, with all instance variables represented.
   */
  @Override
  public String toString() {
    return "Weather [region=" + region + ", high=" + high + ", low=" + low + ", report=" + report + "]";
  }

}
