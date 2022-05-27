package controllers;

import play.Logger;
import play.mvc.Controller;

public class Start extends Controller {

  /**
   * A method to render the start.html page
   */
  public static void index() {
    Logger.info("Rendering Start");
    render("start.html");
  }
}
