package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Reading extends Model{
    public int code;
    public double temperature;
    public double windSpeed;
    public double windDirection;
    public int pressure;


    public Reading(int code, double temperature, double windSpeed, double windDirection, int pressure) {
        addCode(code);
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        addWindDirection(windDirection);
        this.pressure = pressure;
    }

    private void addCode (int code) {
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


        private void addWindDirection(double windDirection){
            if ((windDirection >= 0) && (windDirection <= 360)){
                this.windDirection = windDirection;
        } else {
                this.windDirection = 0;
            }
    }
}
