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


      Logger.info("Rendering station: " + id);

      render("station.html", station);
  }


  public static void addReading(Long id, int code, double temperature, double windSpeed, double windDirection, int pressure) {
    Reading reading = new Reading(code, temperature, windSpeed, windDirection, pressure);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/stations/" + id);
  }

  public static void deleteReading(long id, long readingid){
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);

    Logger.info("Deleting reading: " + readingid + " from station: " + id);

    station.readings.remove(reading);
    station.save();
    reading.delete();

    render("station.html", station);
  }

}
