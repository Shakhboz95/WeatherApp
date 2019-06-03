package weather.uz.weatherapp.model;


import java.util.List;

public class weatherInfo {

    private List<weather> weatherList;

    private List<main> mainList;

    private double wind;

    public weatherInfo(List<weather> weatherList, List<main> mainList, double wind) {
        this.weatherList = weatherList;
        this.mainList = mainList;
        this.wind=wind;
    }

    public List<weather> getWeatherList() {
        return weatherList;
    }


    public List<main> getMainList() {
        return mainList;
    }

    public double getWind() {
        return wind;
    }

    public static class main{

        private int temp;
        private int temp_min;
        private int temp_max;
        private int pressure;
        private double humidity;

        public main(int temp, int temp_min, int temp_max, int pressure, double humidity) {
            this.temp = temp;
            this.temp_min = temp_min;
            this.temp_max = temp_max;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public int getTemp() {
            return temp;
        }

        public int getTemp_min() {
            return temp_min;
        }

        public int getTemp_max() {
            return temp_max;
        }

        public int getPressure() {
            return pressure;
        }

        public double getHumidity() {
            return humidity;
        }

    }
    public static class weather{

        public String condition;
        public String WeatherIcon;

        public weather(String condition, String weatherIcon) {
            this.condition = condition;
            WeatherIcon = weatherIcon;
        }

        public String getCondition() {
            return condition;
        }



        public String getWeatherIcon() {
            return WeatherIcon;
        }

    }

}
