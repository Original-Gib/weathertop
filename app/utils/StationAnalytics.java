package utils;

import models.Reading;

import java.util.List;

import java.lang.Math;

public class StationAnalytics {

    public static Reading getLastReading(List<Reading> readings){
        Reading lastReading = null;
        if (readings.size() != 0) {
            int finalIndex = readings.size() - 1;
            lastReading = readings.get(finalIndex);
        }
        return lastReading;

    }

    public static String weatherCondition(int code){
        String weatherCondition = null;
        switch (code) {
            case 100:
                weatherCondition = "Clear";
                break;
            case 200:
                weatherCondition = "Partial Clouds";
                break;
            case 300:
                weatherCondition = "Cloudy";
                break;
            case 400:
                weatherCondition = "Light Showers";
                break;
            case 500:
                weatherCondition = "Heavy Showers";
                break;
            case 600:
                weatherCondition = "Rain";
                break;
            case 700:
                weatherCondition = "Snow";
                break;
            case 800:
                weatherCondition = "Thunder";
                break;
            default:
                weatherCondition = "Invalid Reading";
                break;
        }
        return weatherCondition;
    }

    public static double celciusToFahrenheit (double celcius){
        double fahrenheit = celcius * 9/5 + 32;
        return fahrenheit;
    }

    public static int beaufortConversion (double windSpeed) {
        int beaufort;
        if (windSpeed < 1) {
            beaufort = 0;
        } else if (windSpeed >= 1 && windSpeed < 6) {
            beaufort = 1;
        } else if (windSpeed >= 6 && windSpeed < 12) {
            beaufort = 2;
        } else if (windSpeed >= 12 && windSpeed < 20) {
            beaufort = 3;
        } else if (windSpeed >= 20 && windSpeed < 29) {
            beaufort = 4;
        } else if (windSpeed >= 29 && windSpeed < 39) {
            beaufort = 5;
        } else if (windSpeed >= 39 && windSpeed < 50) {
            beaufort = 6;
        } else if (windSpeed >= 50 && windSpeed < 62) {
            beaufort = 7;
        } else if (windSpeed >= 62 && windSpeed < 75) {
            beaufort = 8;
        } else if (windSpeed >= 75 && windSpeed < 89) {
            beaufort = 9;
        } else if (windSpeed >= 89 && windSpeed < 103) {
            beaufort = 10;
        } else if (windSpeed >= 103 && windSpeed < 118) {
            beaufort = 11;
        } else {
            beaufort = 0;
        }
        return beaufort;
    }

    public static String windDirectionCompass (double windDirection){
        String windDirectionCompass = null;
        if ((windDirection >= 348.75) && (windDirection <= 360) || (windDirection >= 0) && (windDirection < 11.25)) {
            windDirectionCompass = "North";
        } else if (windDirection >= 11.25 && windDirection < 33.75) {
            windDirectionCompass = "North North-East";
        } else if (windDirection >= 33.75 && windDirection < 56.25) {
            windDirectionCompass = "North-East";
        } else if (windDirection >= 56.25 && windDirection < 78.75) {
            windDirectionCompass = "East North-East";
        } else if (windDirection >= 78.75 && windDirection < 101.25) {
            windDirectionCompass = "East";
        } else if (windDirection >= 101.25 && windDirection < 123.75) {
            windDirectionCompass = "East South-East";
        } else if (windDirection >= 123.75 && windDirection < 146.25) {
            windDirectionCompass = "South-East";
        } else if (windDirection >= 146.25 && windDirection < 168.75) {
            windDirectionCompass = "South South-East";
        } else if (windDirection >= 168.75 && windDirection < 191.25) {
            windDirectionCompass = "South";
        } else if (windDirection >= 191.25 && windDirection < 213.75) {
            windDirectionCompass = "South South-West";
        } else if (windDirection >= 213.75 && windDirection < 236.25) {
            windDirectionCompass = "South-West";
        } else if (windDirection >= 236.25 && windDirection < 258.75) {
            windDirectionCompass = "West South-West";
        } else if (windDirection >= 258.75 && windDirection < 281.25) {
            windDirectionCompass = "West";
        } else if (windDirection >= 281.25 && windDirection < 303.75) {
            windDirectionCompass = "West North-West";
        } else if (windDirection >= 303.75 && windDirection < 326.25) {
            windDirectionCompass = "North-West";
        } else if (windDirection >= 326.25 && windDirection < 348.75) {
            windDirectionCompass = "North North-West";
        } else {
            windDirectionCompass = "Error";
        }
        return windDirectionCompass;
    }

    public static double calculateWindChill(double temperature, double windSpeed){
        double windChill = 13.12 + 0.6215*temperature - 11.37*Math.pow(windSpeed, 0.16) + 0.3965*temperature*Math.pow(windSpeed, 0.16);
        return windChill;
    }


}




