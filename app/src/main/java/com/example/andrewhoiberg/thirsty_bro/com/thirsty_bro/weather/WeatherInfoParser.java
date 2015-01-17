package com.example.andrewhoiberg.thirsty_bro.com.thirsty_bro.weather;

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
		String humidity = getWeatherParameter(json, "relative_humidity");
		weather.setHumidity(humidity);
		String iconURL = getWeatherParameter(json, "icon_url");
		weather.setIconURL(iconURL);
		float precipitationHrIn = Float.parseFloat(getWeatherParameter(json,
				"precip_1hr_in"));
		weather.setPrecipitationHrIn(precipitationHrIn);
		float temperatureF = Float.parseFloat(getWeatherParameter(json,
				"temp_f"));
		weather.setTemperatureF(temperatureF);
		String windDescription = getWeatherParameter(json, "wind_string");
		weather.setWindDescription(windDescription);
		return weather;
	}
	

	private static String getWeatherParameter(JSONObject json, String param)
			throws JSONException {
		return json.getJSONObject("current_observation").get(param).toString();
	}


    public static void main(String[] args) throws IOException, JSONException {
        String latitude = "37.252194";
        String longitude = "-121.360474";
        WeatherInfo weather = WeatherProvider.getWeather(latitude, longitude);
        System.out.println(weather);
    }
}
