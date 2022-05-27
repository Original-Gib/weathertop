package controllers;

import java.util.List;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

public class StationCtrl extends Controller {

  /**
   * A method for rendering the station.html page
   *
   * @param id A long value passed in to identify the station which needs to be loaded within the station.html page
   */
  public static void index(long id) {
    Station station = Station.findById(id);
    Logger.info("Rendering station: " + id);
    render("station.html", station);
  }

  /**
   * A method to create a new reading within the station.
   *
   * @param id            A long value of the stations ID
   * @param code          An int value of the code of the reading
   * @param temperature   A double value for the temperature of the reading
   * @param windSpeed     A double value for the wind speed of the reading
   * @param windDirection A double value of the wind direction of the reading
   * @param pressure      An int value of the pressure of the reading
   */
  public static void addReading(Long id, int code, double temperature, double windSpeed, double windDirection, int pressure) {
    Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/stations/" + id);
  }

  /**
   * A method to delete a reading.
   *
   * @param id A long value passed in to identify the reading which needs to be deleted
   */
  public static void deleteReading(long id, long readingid) {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info("Deleting reading: " + readingid + " from station: " + id);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    render("station.html", station);
  }

}
