package controllers;

import java.util.List;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class StationCtrl extends Controller {

  public static void index(long id) {

    Station station = Station.findById(id);

    if (station.readings.size() != 0) {
      Reading lastReading = StationAnalytics.getLastReading(station.readings);
      String weatherCondition = StationAnalytics.weatherCondition(lastReading.code);
      double fahrenheit = StationAnalytics.celciusToFahrenheit(lastReading.temperature);
      int beaufort = StationAnalytics.beaufortConversion(lastReading.windSpeed);
      String windDirection = StationAnalytics.windDirectionCompass(lastReading.windDirection);
      double windChill = StationAnalytics.calculateWindChill(lastReading.temperature, lastReading.windSpeed);
      double latitude = station.getLatitude();
      double longitude = station.getLongitude();
      Member member = Accounts.getLoggedInMember();


      Logger.info("Rendering station: " + id);

      render("station.html", station, lastReading, weatherCondition, fahrenheit, beaufort, windDirection, windChill, latitude, longitude, member);
    } else {
      render("station.html", station);
    }
  }

  public static void addReading(Long id, int code, double temperature, double windSpeed, double windDirection, int pressure) {
    Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/stations/" + id);
  }
}
