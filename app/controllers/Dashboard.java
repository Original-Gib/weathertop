package controllers;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Controller {

  /**
   * A method which renders the dashboard on the web app, it passess in the logged in member and the station
   */
  public static void index() {

    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render("dashboard.html", member, stations);
  }

  /**
   * A method used to create a new station.
   *
   * @param name      A string to be passed into the station constructor
   * @param latitude  A double to be passed into the station constructor
   * @param longitude A double to be passed into the station constuctor
   */

  public static void addStation(String name, double latitude, double longitude) {

    Logger.info("Adding new station: " + name);
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name, latitude, longitude);
    member.stations.add(station);
    member.save();
    redirect("/dashboard");
  }

  /**
   * A method to delete the station passed in as a parameter
   *
   * @param id A long value passed in to identify the station which needs to be deleted
   */
  public static void deleteStation(long id) {
    Logger.info("Deleting Station: " + id);
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    station.delete();
    redirect("/dashboard");
  }

}
