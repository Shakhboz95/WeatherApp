package weather.uz.weatherapp.model;


import java.util.List;

public class City {

    private  String cityName;
    private int cityId;
  private  List<weather> weathers;
  private int temp;
    private List<main> mainList;

    public City(String cityName,int cityId, List<weather> weathers, int temp) {
        this.cityName = cityName;
        this.cityId=cityId;
        this.weathers = weathers;
        this.temp=temp;
    }

    public int getTemp() {
        return temp;
    }

    public List<weather> getWeathers() {
        return weathers;
    }



    public List<main> getMainList() {
        return mainList;
    }


    public String getCityName() {
        return cityName;
    }


    public int getCityId() {
        return cityId;
    }


    public static class weather{

    private String WeatherIcon;
    private String condition;


    public weather(String weatherIcon, String condition) {
        WeatherIcon = weatherIcon;
        this.condition = condition;

    }


    public String getWeatherIcon() {
        return WeatherIcon;
    }


    public String getCondition() {
        return condition;
    }

}

public static class main{
        private int temp;

    public main(int temp) {
        this.temp = temp;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}


}
