package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.ArrayList;

/**
 * This member class is a subclass of the model class of the play framework. It is logged as an entity in the
 * DB of the application.
 *
 * The member class will store an array list of stations created by the member within the application.
 */

@Entity
public class Member extends Model {
  public String firstName;
  public String lastName;
  public String email;
  public String password;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Station> stations = new ArrayList<Station>();

  /**
   * Constructor method for a member object
   *
   * @param firstName The first name of the member
   * @param lastName  The last name of the member
   * @param email     The email address of the member
   * @param password  The password associated with the member
   */

  public Member(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  /**
   * Method to find a member by passing in their email address as a parameter
   *
   * @param email of the member you wish to find
   * @return the member associated with the email address passed in
   */

  public static Member findByEmail(String email) {
    return find("email", email).first();
  }

  /**
   * Method to check the password for the member in order to validate their details during the authentication
   *
   * @param password The password you wish to check against the password of the member
   * @return A boolean value depending on whether the password parameter matches the members password
   */

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

  /**
   * Getters and setters for the member class
   */

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Station> getStations() {
    return stations;
  }

  public void setStations(List<Station> stations) {
    this.stations = stations;
  }
}

