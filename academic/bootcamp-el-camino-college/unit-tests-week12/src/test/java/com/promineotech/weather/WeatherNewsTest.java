package com.promineotech.weather;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WeatherNewsTest {
	private enum Resolution {
		ALERTS_CALLED, ALERTS_NOT_CALLED
	};

	@Test
	void assertHighAndLowTempsTriggerAlerts() {
		// Given: a high and low weather event
		Weather weather = createHighLowWeatherEvent();
		WeatherNews news = createWeatherNewsMock(weather);
		
		// When: the weather is retrieved
		news.reportTheWeather();
		
		// Then: the alerts were triggered
		verifyAlertsCalled(weather, news, Resolution.ALERTS_CALLED);
	}
	
	@Test
	void assertNormalTempsDoNotTriggerAlerts() {
		// Given: a high and low weather event
		Weather weather = createNormalWeatherEvent();
		WeatherNews news = createWeatherNewsMock(weather);
		
		// When: the weather is retrieved
		news.reportTheWeather();
		
		// Then: the alerts were triggered
		verifyAlertsCalled(weather, news, Resolution.ALERTS_NOT_CALLED);
	}

	private void verifyAlertsCalled(Weather weather, WeatherNews news, Resolution alertsCalled) {
		int timesCalled = Resolution.ALERTS_CALLED == alertsCalled ? 1 : 0;
		
		verify(news, times(timesCalled)).alertHighTemp(weather);
		verify(news, times(timesCalled)).alertLowTemp(weather);
	}

	private WeatherNews createWeatherNewsMock(Weather weather) {
		WeatherNews news = spy(new WeatherNews());
		
		doReturn(weather).when(news).retrieveWeatherFromService();
		lenient().doNothing().when(news).alertHighTemp(weather);
		lenient().doNothing().when(news).alertLowTemp(weather);
		
		return news;		
	}

	private Weather createNormalWeatherEvent() {
		String region = "Rigby, Idaho";
		Float high = WeatherNews.getHighAlertTrigger();
		Float low = WeatherNews.getLowAlertTrigger();
		
		String report = region + "high=" + high + ", low=" + low;
		
		return new Weather(region, high, low, report);
	}

	private Weather createHighLowWeatherEvent() {
		String region = "Rigby, Idaho";
		Float high = WeatherNews.getHighAlertTrigger() + 0.1F;
		Float low = WeatherNews.getLowAlertTrigger() - 0.1F;
		
		String report = region + "high=" + high + ", low=" + low;
		
		return new Weather(region, high, low, report);
	}

}
