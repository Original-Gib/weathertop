package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is a subclass of the model class of the play framework.
 *
 * The reading objects are passed into a station object and stored within an arraylist
 */

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public double windDirection;
  public int pressure;
  public String formattedTime;

  /**
   * Constructor for a reading object
   *
   * @param code          The weather code for the reading passed in as an integer between 100 - 800
   * @param temperature   A temperature reading passed in with a data type of double
   * @param windSpeed     The wind speed for the reading passed in as a double
   * @param windDirection The direction of the wind between 0 - 360
   * @param pressure      Integer value passed in as the pressure input for the reading
   */

  public Reading(int code, double temperature, double windSpeed, double windDirection, int pressure) {
    addCode(code);
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    addWindDirection(windDirection);
    this.pressure = pressure;
    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    this.formattedTime = currentTime.format(formatter);
  }

  /**
   * Getters and setters for the reading fields
   */

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public double getWindDirection() {
    return windDirection;
  }

  public void setWindDirection(double windDirection) {
    this.windDirection = windDirection;
  }

  public int getPressure() {
    return pressure;
  }

  public void setPressure(int pressure) {
    this.pressure = pressure;
  }

  public String getFormattedTime() {
    return formattedTime;
  }

  public void setFormattedTime(String formattedTime) {
    this.formattedTime = formattedTime;
  }

  /**
   * Private method for adding the code to the reading constructor. Switch statement is used to keep the
   * method neat, and check if the parameter is better 100 - 800. If the value is not between those numbers
   * at a 100 interval value the method will return a default value of 100.
   *
   * @param code integer value passed in for the code
   */

  private void addCode(int code) {
    switch (code) {
      case 100:
      case 200:
      case 300:
      case 400:
      case 500:
      case 600:
      case 700:
      case 800:
        this.code = code;
        break;
      default:
        this.code = 100;
    }
  }

  /**
   * Private method used in the constructor of the reading to ensure that the reading is between 0 and 360
   * If the value is outside of these ranges it will default to 0
   *
   * @param windDirection Double value passed in to be added as the wind direction
   */

  private void addWindDirection(double windDirection) {
    if ((windDirection >= 0) && (windDirection <= 360)) {
      this.windDirection = windDirection;
    } else {
      this.windDirection = 0;
    }
  }

}
