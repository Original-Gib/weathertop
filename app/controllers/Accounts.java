package controllers;

import jdk.internal.net.http.common.Log;
import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {

    public static void signup(){

        render("signup.html");
    }

    public static void login(){

        render("login.html");
    }

    public static void register(String firstname, String lastname, String email, String password) {

        Logger.info("Registering new member: " + email);
        Member member = new Member(firstname, lastname, email, password);
        member.save();
        redirect("/");
    }

    public  static void authenticate(String email, String password){

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

    public static void logout() {
        session.clear();
        redirect ("/");
    }

    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }
}
