package weather.uz.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityList {

    @SerializedName("cnt")
    public int count;

    @SerializedName("list")
    private List<City> cities=null;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
