package com.example.andrewhoiberg.thirsty_bro;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class WeatherInfoParser {
	public static WeatherInfo getWeatherInfo(JSONObject json)
			throws JSONException {
		WeatherInfo weather = new WeatherInfo();
		String elevationFT = (String) json.getJSONObject("current_observation")
				.getJSONObject("observation_location").get("elevation");
		weather.setElevationFT(elevationFT);
        String location = (String) json.getJSONObject("current_observation")
                .getJSONObject("display_location").get("full");
        weather.setLocation(location);
		String humidity = getWeatherParameter(json, "relative_humidity");
		weather.setHumidity(humidity);
		String iconURL = getWeatherParameter(json, "icon_url");
		weather.setIconURL(iconURL);
		float precipitationHrIn = Float.parseFloat(getWeatherParameter(json,
				"precip_1hr_in"));
        precipitationHrIn= precipitationHrIn<0? 0:precipitationHrIn;
		weather.setPrecipitationHrIn(precipitationHrIn);
		float temperatureF = Float.parseFloat(getWeatherParameter(json,
				"temp_f"));
		weather.setTemperatureF(temperatureF);
		String windDescription = getWeatherParameter(json, "wind_string");
		weather.setWindDescription(windDescription);
        float windSpeed = Float.parseFloat(getWeatherParameter(json, "wind_mph"));
        weather.setWindSpeed(windSpeed);
		return weather;
	}
	

	private static String getWeatherParameter(JSONObject json, String param)
			throws JSONException {
		return json.getJSONObject("current_observation").get(param).toString();
	}


}
