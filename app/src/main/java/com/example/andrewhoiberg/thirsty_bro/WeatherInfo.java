package com.example.andrewhoiberg.thirsty_bro;


public class WeatherInfo {

	private String humidity;//percentage
	private String iconURL;//url for icon
	private String windDescription;//wind mph and direction description
	private double temperatureF;// in Fahrenheit
	private String elevationFT;//
	private float precipitationHrIn;//precipitation within one hour in inches
	
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public String getWindDescription() {
		return windDescription;
	}
	public void setWindDescription(String windDescription) {
		this.windDescription = windDescription;
	}
	public double getTemperatureF() {
		return temperatureF;
	}
	public void setTemperatureF(double temperatureF) {
		this.temperatureF = temperatureF;
	}
	public String getElevationFT() {
		return elevationFT;
	}
	public void setElevationFT(String elevationFT2) {
		this.elevationFT = elevationFT2;
	}
	public float getPrecipitationHrIn() {
		return precipitationHrIn;
	}
	public void setPrecipitationHrIn(float precipitationHrIn) {
		this.precipitationHrIn = precipitationHrIn;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WeatherInfo [humidity=");
		builder.append(humidity);
		builder.append(", iconURL=");
		builder.append(iconURL);
		builder.append(", windDescription=");
		builder.append(windDescription);
		builder.append(", temperatureF=");
		builder.append(temperatureF);
		builder.append(", elevationFT=");
		builder.append(elevationFT);
		builder.append(", precipitationHrIn=");
		builder.append(precipitationHrIn);
		builder.append("]");
		return builder.toString();
	}
	
}
