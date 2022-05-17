package controllers;

import models.Member;
import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {

    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    render ("dashboard.html", member, stations);
  }

  public static void addStation(String name){

    Logger.info("Adding new station: " + name);
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(name);
    member.stations.add(station);
    member.save();
    redirect("/dashboard");
  }

}
