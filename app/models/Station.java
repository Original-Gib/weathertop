package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import play.db.jpa.Model;

/**
 * Station class is a subclass of the model class of the play framework.
 * <p>
 * The station class stores an array list of the readings so that the users inputted readings
 * can be grouped and stored based on their location
 */

@Entity
public class Station extends Model {
  public String name;
  public double latitude;
  public double longitude;
  public HashMap<Integer, String> conditionMap;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<Reading>();

  /**
   * Constructor method for the station class
   *
   * @param name      A string passed as the name of the station
   * @param latitude  A double passed as the lat value
   * @param longitude A double passed as the long value
   */

  public Station(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * Getters and setters
   */

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public List<Reading> getReadings() {
    return readings;
  }

  public void setReadings(List<Reading> readings) {
    this.readings = readings;
  }

  /**
   * Method to fill the Hashmap of the station with the weather code - weather condition key value pairs
   */

  private void fillConditionMap() {
    conditionMap.put(100, "Clear");
    conditionMap.put(200, "Partial Clouds");
    conditionMap.put(300, "Cloudy");
    conditionMap.put(400, "Light Showers");
    conditionMap.put(500, "Heavy Showers");
    conditionMap.put(600, "Rain");
    conditionMap.put(700, "Thunder");
    conditionMap.put(800, "Snow");
  }

  /**
   * Method to get the latest weather condition based on the latest weather code in the station
   *
   * @return A string from the Hashmap of the weathercondition based on the latest weather code in the station
   */

  public String weatherCondition() {
    conditionMap = new HashMap<Integer, String>();
    fillConditionMap();
    int code = latestWeatherCode();
    String output = conditionMap.get(code);
    return output;
  }

  /**
   * Method for getting the latest reading within the station.
   *
   * @return A Reading which is the latest reading in the station's arraylist, if there are no reading null
   * value is returned.
   */

  public Reading latestReading() {
    if (readings.size() != 0) {
      int index = readings.size() - 1;
      Reading latestReading = readings.get(index);
      return latestReading;
    } else {
      Reading reading = null;
      return reading;
    }
  }

  /**
   * A method for getting the latest weathercode from the most recent reading
   *
   * @return An integer value associated with the latest weather code. If there are no readings then a default value of 0 is returned
   */

  public int latestWeatherCode() {
    if (readings.size() != 0) {
      int index = readings.size() - 1;
      int latestWeatherCode = readings.get(index).code;
      return latestWeatherCode;
    } else {
      int fallback = 0;
      return fallback;
    }
  }

  /**
   * Method for calculating the fahrenheit value of the latest temperature reading.
   *
   * @return A double value for the fahrenheit conversion. If there are no readings a default value of 0 is returned
   */

  public double celciusToFahrenheit() {
    if (readings.size() != 0) {
      int index = readings.size() - 1;
      Reading reading = readings.get(index);
      double fahrenheit = reading.temperature * 9 / 5 + 32;
      return fahrenheit;
    } else {
      double fallBack = 0.0;
      return fallBack;
    }
  }

  /**
   * A method to return the latest temperature reading
   *
   * @return A double value for the latest temperature reading. If no readings are present a default value of 0 is returned
   */

  public double latestTemperature() {
    if (readings.size() != 0) {
      int index = readings.size() - 1;
      Reading reading = readings.get(index);
      double temperature = reading.temperature;
      return temperature;
    } else {
      double fallBack = 0.0;
      return fallBack;
    }
  }

  /**
   * A method for converting the wind speed into a beaufort value.
   *
   * @return An integer value for the beaufort value. If there are no readings then a default value of 0 is returned
   */
  public int beaufortConversion() {
    if (readings.size() != 0) {
      int index = readings.size() - 1;
      Reading reading = readings.get(index);
      double windSpeed = reading.windSpeed;
      int beaufort;
      if (windSpeed < 1) {
        beaufort = 0;
      } else if (windSpeed >= 1 && windSpeed < 6) {
        beaufort = 1;
      } else if (windSpeed >= 6 && windSpeed < 12) {
        beaufort = 2;
      } else if (windSpeed >= 12 && windSpeed < 20) {
        beaufort = 3;
      } else if (windSpeed >= 20 && windSpeed < 29) {
        beaufort = 4;
      } else if (windSpeed >= 29 && windSpeed < 39) {
        beaufort = 5;
      } else if (windSpeed >= 39 && windSpeed < 50) {
        beaufort = 6;
      } else if (windSpeed >= 50 && windSpeed < 62) {
        beaufort = 7;
      } else if (windSpeed >= 62 && windSpeed < 75) {
        beaufort = 8;
      } else if (windSpeed >= 75 && windSpeed < 89) {
        beaufort = 9;
      } else if (windSpeed >= 89 && windSpeed < 103) {
        beaufort = 10;
      } else if (windSpeed >= 103 && windSpeed < 118) {
        beaufort = 11;
      } else {
        beaufort = 0;
      }
      return beaufort;
    } else {
      int fallBack = 0;
      return fallBack;
    }
  }

  /**
   * A method for converting the wind direction into the associated compass direction
   *
   * @return A string of the cardinal direction of the latest wind direction reading. If there are no readings a null value is returned.
   */

  public String windDirectionCompass() {
    if (readings.size() != 0) {
      int index = readings.size() - 1;
      Reading reading = readings.get(index);
      double windDirection = reading.windDirection;
      String windDirectionCompass = null;
      if ((windDirection >= 348.75) && (windDirection <= 360) || (windDirection >= 0) && (windDirection < 11.25)) {
        windDirectionCompass = "North";
      } else if (windDirection >= 11.25 && windDirection < 33.75) {
        windDirectionCompass = "North North-East";
      } else if (windDirection >= 33.75 && windDirection < 56.25) {
        windDirectionCompass = "North-East";
      } else if (windDirection >= 56.25 && windDirection < 78.75) {
        windDirectionCompass = "East North-East";
      } else if (windDirection >= 78.75 && windDirection < 101.25) {
        windDirectionCompass = "East";
      } else if (windDirection >= 101.25 && windDirection < 123.75) {
        windDirectionCompass = "East South-East";
      } else if (windDirection >= 123.75 && windDirection < 146.25) {
        windDirectionCompass = "South-East";
      } else if (windDirection >= 146.25 && windDirection < 168.75) {
        windDirectionCompass = "South South-East";
      } else if (windDirection >= 168.75 && windDirection < 191.25) {
        windDirectionCompass = "South";
      } else if (windDirection >= 191.25 && windDirection < 213.75) {
        windDirectionCompass = "South South-West";
      } else if (windDirection >= 213.75 && windDirection < 236.25) {
        windDirectionCompass = "South-West";
      } else if (windDirection >= 236.25 && windDirection < 258.75) {
        windDirectionCompass = "West South-West";
      } else if (windDirection >= 258.75 && windDirection < 281.25) {
        windDirectionCompass = "West";
      } else if (windDirection >= 281.25 && windDirection < 303.75) {
        windDirectionCompass = "West North-West";
      } else if (windDirection >= 303.75 && windDirection < 326.25) {
        windDirectionCompass = "North-West";
      } else if (windDirection >= 326.25 && windDirection < 348.75) {
        windDirectionCompass = "North North-West";
      } else {
        windDirectionCompass = "Error";
      }
      return windDirectionCompass;
    } else {
      return null;
    }
  }

  /**
   * A method for calculating the wind chill based on the latest temperature and the latets wind speed
   *
   * @return A double value for the wind chill calculation. If there are no readings a default value of 0.0 is returned
   */

  public double calculateWindChill() {
    if (readings.size() != 0) {
      int index = readings.size() - 1;
      Reading reading = readings.get(index);
      double windChill = 13.12 + 0.6215 * reading.temperature - 11.37 * Math.pow(reading.windSpeed, 0.16) + 0.3965 * reading.temperature * Math.pow(reading.windSpeed, 0.16);
      return windChill;
    } else {
      double fallBack = 0.0;
      return fallBack;
    }
  }

  /**
   * A method for getting the latest pressure reading
   *
   * @return An integer value for the last pressure reading. If there are no readings a default value of 0 is returned
   */

  public int lastReadingPressure() {
    if (readings.size() != 0) {
      int index = readings.size() - 1;
      Reading reading = readings.get(index);
      int pressure = reading.pressure;
      return pressure;
    } else {
      int fallBack = 0;
      return fallBack;
    }
  }

  /**
   * A method for calculating the minimum temperature captured in the readings
   *
   * @return A double value for the minimum reading. If there are no readings a default value of 0 is returned
   */

  public double minTemperature() {
    if (readings.size() != 0) {
      double minTemperature = readings.get(0).temperature;
      for (int i = 0; i < readings.size(); i++) {
        if (readings.get(i).temperature < minTemperature) {
          minTemperature = readings.get(i).temperature;
        }
      }
      return minTemperature;
    } else {
      double fallBack = 0.0;
      return fallBack;
    }
  }

  /**
   * A method for calculating the maximum temperature captured in the readings
   *
   * @return A double value for the maximum reading. If there are no readings a default value of 0 is returned
   */

  public double maxTemperature() {
    if (readings.size() != 0) {
      double maxTemperature = readings.get(0).temperature;
      for (int i = 0; i < readings.size(); i++) {
        if (readings.get(i).temperature > maxTemperature) {
          maxTemperature = readings.get(i).temperature;
        }
      }
      return maxTemperature;
    } else {
      double fallBack = 0;
      return fallBack;
    }
  }

  /**
   * A method for calculating the minimum wind speed captured in the readings
   *
   * @return A double value for the minimum reading. If there are no readings a default value of 0 is returned
   */

  public double minWindSpeed() {
    if (readings.size() != 0) {
      double minWindSpeed = readings.get(0).windSpeed;
      for (int i = 0; i < readings.size(); i++) {
        if (readings.get(i).windSpeed < minWindSpeed) {
          minWindSpeed = readings.get(i).windSpeed;
        }
      }
      return minWindSpeed;
    } else {
      double fallBack = 0;
      return fallBack;
    }
  }

  /**
   * A method for calculating the maximum wind speed captured in the readings
   *
   * @return A double value for the maximum reading. If there are no readings a default value of 0 is returned
   */
  public double maxWindSpeed() {
    if (readings.size() != 0) {
      double maxWindSpeed = readings.get(0).windSpeed;
      for (int i = 0; i < readings.size(); i++) {
        if (readings.get(i).windSpeed > maxWindSpeed) {
          maxWindSpeed = readings.get(i).windSpeed;
        }
      }
      return maxWindSpeed;
    } else {
      double fallBack = 0;
      return fallBack;
    }
  }

  /**
   * A method for calculating the maximum pressure captured in the readings
   *
   * @return An integer value for the maximum reading. If there are no readings a default value of 0 is returned
   */
  public int maxPressure() {
    if (readings.size() != 0) {
      int maxPressure = readings.get(0).pressure;
      for (int i = 0; i < readings.size(); i++) {
        if (readings.get(i).pressure > maxPressure) {
          maxPressure = readings.get(i).pressure;
        }
      }
      return maxPressure;
    } else {
      int fallBack = 0;
      return fallBack;
    }
  }

  /**
   * A method for calculating the minimum pressure captured in the readings
   *
   * @return An integer value for the minimum reading. If there are no readings a default value of 0 is returned
   */

  public int minPressure() {
    if (readings.size() != 0) {
      int minPressure = readings.get(0).pressure;
      for (int i = 0; i < readings.size(); i++) {
        if (readings.get(i).pressure < minPressure) {
          minPressure = readings.get(i).pressure;
        }
      }
      return minPressure;
    } else {
      int fallBack = 0;
      return fallBack;
    }
  }

  /**
   * A method for scanning over the lastest three temperature readings to determine if there is an upward trend
   *
   * @return A boolean value based on if there is an upward trend
   */

  public boolean upwardTrendTemperature() {
    int index = readings.size() - 1;
    double temperatureOne = readings.get(index).temperature;
    double temperatureTwo = readings.get(index - 1).temperature;
    double temperatureThree = readings.get(index - 2).temperature;
    if ((temperatureTwo < temperatureOne) && (temperatureThree < temperatureTwo)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * A method for scanning over the lastest three temperature readings to determine if there is an downward trend
   *
   * @return A boolean value based on if there is an downward trend
   */

  public boolean downwardTrendTemperature() {
    int index = readings.size() - 1;
    double temperatureOne = readings.get(index).temperature;
    double temperatureTwo = readings.get(index - 1).temperature;
    double temperatureThree = readings.get(index - 2).temperature;
    if ((temperatureTwo > temperatureOne) && (temperatureThree > temperatureTwo)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * A method for scanning over the lastest three wind speed readings to determine if there is an upward trend
   *
   * @return A boolean value based on if there is an upward trend
   */

  public boolean upwardTrendWind() {
    int index = readings.size() - 1;
    double windSpeedOne = readings.get(index).windSpeed;
    double windSpeedTwo = readings.get(index - 1).windSpeed;
    double windSpeedThree = readings.get(index - 2).windSpeed;
    if ((windSpeedTwo < windSpeedOne) && (windSpeedThree < windSpeedTwo)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * A method for scanning over the lastest three wind speed readings to determine if there is an downward trend
   *
   * @return A boolean value based on if there is an downward trend
   */
  public boolean downwardTrendWind() {
    int index = readings.size() - 1;
    double windSpeedOne = readings.get(index).windSpeed;
    double windSpeedTwo = readings.get(index - 1).windSpeed;
    double windSpeedThree = readings.get(index - 2).windSpeed;
    if ((windSpeedTwo > windSpeedOne) && (windSpeedThree > windSpeedTwo)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * A method for scanning over the lastest three pressure readings to determine if there is an upward trend
   *
   * @return A boolean value based on if there is an upward trend
   */

  public boolean upwardTrendPressure() {
    int index = readings.size() - 1;
    double pressureOne = readings.get(index).pressure;
    double pressureTwo = readings.get(index - 1).pressure;
    double pressureThree = readings.get(index - 2).pressure;
    if ((pressureTwo < pressureOne) && (pressureThree < pressureTwo)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * A method for scanning over the lastest three pressure readings to determine if there is an downward trend
   *
   * @return A boolean value based on if there is an downward trend
   */
  public boolean downwardTrendPressure() {
    int index = readings.size() - 1;
    double pressureOne = readings.get(index).pressure;
    double pressureTwo = readings.get(index - 1).pressure;
    double pressureThree = readings.get(index - 2).pressure;
    if ((pressureTwo > pressureOne) && (pressureThree > pressureTwo)) {
      return true;
    } else {
      return false;
    }
  }

}
