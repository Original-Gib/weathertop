package controllers;

import jdk.internal.net.http.common.Log;
import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

/**
 * Accounts class is a subclass of the Play Framework controller class which renders the About page
 */

public class Accounts extends Controller {


  /**
   * Index method which renders the account page and passes in the member class
   */
  public static void index() {

    Member member = Accounts.getLoggedInMember();
    Logger.info("Rendering Account info");
    render("account.html", member);
  }

  /**
   * A method for rendering the signup page
   */

  public static void signup() {

    render("signup.html");
  }

  /**
   * A method for rendering the login page
   */

  public static void login() {

    render("login.html");
  }

  /**
   * A method for registering a new member on the app. It calls the member constructor for the Member class
   *
   * @param firstname String passed in for the first name
   * @param lastname  String passed in for the last name
   * @param email     String passed in for the email address
   * @param password  String passed in for the password of the user
   */

  public static void register(String firstname, String lastname, String email, String password) {

    Logger.info("Registering new member: " + email);
    Member member = new Member(firstname, lastname, email, password);
    member.save();
    redirect("/");
  }

  /**
   * Method used to authenticate the user based on the email and password passed in as parameters. If there
   * is a match the member will be logged in and the session will begin for that user.
   * If ther authentication fails then the user is redirected back to the same login page
   *
   * @param email    A string for the email address of the user
   * @param password A string for the password of the user
   */

  public static void authenticate(String email, String password) {

    Logger.info("Attempting to authenticate with: " + email + " + " + password);

    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authenication Successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    } else {
      Logger.info("Authentication Failed");
      redirect("/login");
    }
  }


  /**
   * A method to logout the logged in member, by clearing the session data
   */
  public static void logout() {
    session.clear();
    redirect("/");
  }

  /**
   * A method for getting the currently logged in member
   *
   * @return A Member object of the current logged in member
   */
  public static Member getLoggedInMember() {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }

  /**
   * A method to reset the members details
   *
   * @param firstname A string for the users first name
   * @param lastname  A string for the users last name
   * @param email     A string for the users email address
   * @param password  A string for the users password
   */

  public static void editDetails(String firstname, String lastname, String email, String password) {

    Logger.info("Editing member details: " + email);
    Member member = Accounts.getLoggedInMember();
    member.setFirstName(firstname);
    member.setLastName(lastname);
    member.setEmail(email);
    member.setPassword(password);
    member.save();
    redirect("/account");
  }
}
