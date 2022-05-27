package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 * About class is a subclass of the Play Framework controller class which renders the About page
 */

public class About extends Controller {
  public static void index() {
    Logger.info("Rendering about");
    render("about.html");
  }
}
