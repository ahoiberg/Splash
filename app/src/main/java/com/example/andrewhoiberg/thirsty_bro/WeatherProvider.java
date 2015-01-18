package com.example.andrewhoiberg.thirsty_bro;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherProvider {
	private static String API_KEY = "62e4fdba1393b11a";
	private static String FORMAT = "json";
	private static String WEATHER_SERVICE_ADDRESS = "http://api.wunderground.com/api/";

	
	
	public static WeatherInfo getWeather(String latitude, String longitude)
			throws IOException, JSONException {
		String weatherURLString = createWeatherURL(WEATHER_SERVICE_ADDRESS,
				API_KEY, latitude, longitude, FORMAT);

		JSONObject json = JSONReader.readJsonFromUrl(weatherURLString);

		WeatherInfo weather = WeatherInfoParser.getWeatherInfo(json);
		return weather;
	}

	/**
	 * Takes in a base url for a weather service provider, an api key, a
	 * latitude, longitude and format to form the appropriate url (specific to
	 * weather underground) e.g.
	 * "http://api.wunderground.com/api/62e4fdba1393b11a/conditions/q/37.252194,-121.360474.json"
	 * 
	 * @param baseURL
	 * @param apiKey
	 * @param latitude
	 * @param longitude
	 * @param format
	 * @return formatted url string
	 */
	private static String createWeatherURL(String baseURL, String apiKey,
			String latitude, String longitude, String format) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseURL);
		sb.append(apiKey);
		sb.append("/conditions/q/");
		sb.append(latitude);
		sb.append(",");
		sb.append(longitude);
		sb.append(".");
		sb.append(format);
		return sb.toString();
	}
}
