package org.me.gcu.weathermpdcfaw;

public class DaySummary {


    private String dayTitle;
    private String maxTemp;
    private String minTemp;
    private String weatherType;
    private String windDir;
    private String windSpeed;
    private String visibility;
    private String pressure;
    private String humidity;
    private String uvRisk;
    private String pollution;
    private String sunrise;
    private String sunset;


    public DaySummary() {
    }


    public String getDayTitle() {
        return dayTitle;
    }

    @Override
    public String toString() {
        return "DaySummary{" +
                "dayTitle='" + dayTitle + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", minTemp='" + minTemp + '\'' +
                ", weatherType='" + weatherType + '\'' +
                ", windDir='" + windDir + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", visibility='" + visibility + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", uvRisk='" + uvRisk + '\'' +
                ", pollution='" + pollution + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }

    public void setDayTitle(String dayTitle) {
        this.dayTitle = dayTitle;
    }

    public DaySummary(String maxTemp, String minTemp, String weatherType, String windDir, String windSpeed, String visibility, String pressure, String humidity, String uvRisk, String pollution, String sunrise, String sunset) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.weatherType = weatherType;
        this.windDir = windDir;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
        this.pressure = pressure;
        this.humidity = humidity;
        this.uvRisk = uvRisk;
        this.pollution = pollution;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getUvRisk() {
        return uvRisk;
    }

    public void setUvRisk(String uvRisk) {
        this.uvRisk = uvRisk;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
