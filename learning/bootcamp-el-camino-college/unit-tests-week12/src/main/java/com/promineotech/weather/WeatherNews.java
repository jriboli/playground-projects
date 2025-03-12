/**
 * 
 */
package com.promineotech.weather;

import com.google.common.annotations.VisibleForTesting;

/**
 * This class simulates creating a one-line weather report such as might be seen in a chyron on a Weather News channel.
 * It contains a main method, so can be run directly from the JRE.
 * 
 * @author Promineo
 *
 */
public class WeatherNews {
  /* These are the temperature boundaries, above or below which, a temperature alert is triggered. */
  private static final Float LOW_ALERT_TRIGGER = 20.0F;
  private static final Float HIGH_ALERT_TRIGGER = 110F;

  /**
   * This is the weather service that generates a random weather event. This service is only called when the WeatherNews
   * class is run by the JRE. In the test, the method calling the service is mocked and a {@link Weather} object is
   * returned directly from the test so that the weather service is not called.
   */
  private WeatherService weatherService = new WackyWeatherService();

  /**
   * This is the method that is called by the test. It has an elevated visibility (package instead of private) so it is
   * marked with @VisibleForTesting so that it isn't seen as a mistake. This method obtains a weather event from the
   * {@link WackyWeatherService} or from the test. If the high temperature is greater than the high temperature
   * boundary, a high-temperature alert is triggered. Conversely, if the low temperature is too low, a low-temperature
   * alert is triggered.
   */
  @VisibleForTesting
  void reportTheWeather() {
    Weather weather = retrieveWeatherFromService();

    displayWeather(weather);

    if (weather.getHigh() > HIGH_ALERT_TRIGGER) {
      alertHighTemp(weather);
    }

    if (weather.getLow() < LOW_ALERT_TRIGGER) {
      alertLowTemp(weather);
    }
  }

  /**
   * This method is called for a low-temperature alert.
   * 
   * @param weather The weather object
   */
  @VisibleForTesting
  void alertLowTemp(Weather weather) {
    String lowReport = String.format("Brr! A low of %.1f for %s is extremely chilly. Bundle up!", weather.getLow(),
        weather.getRegion());

    System.out.println(lowReport);
  }

  /**
   * This method is called for a high-temperature alert.
   * 
   * @param weather The weather object
   */
  @VisibleForTesting
  void alertHighTemp(Weather weather) {
    String highReport = String.format("Wow! A high of %.1f for %s is really hot. Eat ice cream!", weather.getHigh(),
        weather.getRegion());

    System.out.println(highReport);
  }

  /**
   * This method is called to display the primary weather report. It simply prints to the console, which is very
   * difficult to test for in a unit test.
   * 
   * @param weather The weather object
   */
  private void displayWeather(Weather weather) {
    System.out.println(weather.getReport());
  }

  /**
   * This method retrieves a random weather event from the weather service. It is mocked by the unit test so that a
   * specific weather event created by the test is returned instead of one from the weather service.
   * 
   * @return
   */
  @VisibleForTesting
  Weather retrieveWeatherFromService() {
    return weatherService.getRandomWeather();
  }

  /**
   * This returns the high-temperature alert boundary.
   * 
   * @return The boundary
   */
  public static float getHighAlertTrigger() {
    return HIGH_ALERT_TRIGGER;
  }

  /**
   * This returns the low-temperature alert boundary.
   * 
   * @return The boundary
   */
  public static float getLowAlertTrigger() {
    return LOW_ALERT_TRIGGER;
  }

  /**
   * The entry point of the Java application called by the JRE.
   * 
   * @param args Command-line arguments (ignored)
   */
  public static void main(String[] args) {
    new WeatherNews().reportTheWeather();
  }

}
