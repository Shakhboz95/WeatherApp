package weather.uz.weatherapp.main_activity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weather.uz.weatherapp.URL;
import weather.uz.weatherapp.model.City;
import weather.uz.weatherapp.model.weatherInfo;
import weather.uz.weatherapp.network.RetrofitC;
import weather.uz.weatherapp.network.RetrofitInterface;

public class ModelInteractor implements MainContract.Model {

    List<City> allCity = new ArrayList<>();
    List<City.weather> weatherList = new ArrayList<>();
    City cityItem = null;
    City.weather weatherItem = null;

    @Override
    public void getCityList(final OnFinishedListener onFinishedListener) {
        RetrofitInterface service = RetrofitC.getRetrofit().create(RetrofitInterface.class);

        Call<ResponseBody> call = service.getCities(URL.getGroupCityUrl());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {
                        // Log.e("AllData", response.body().string());
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray list = jsonObject.getJSONArray("list");
                        for (int i = 0; i < list.length(); i++) {

                            JSONObject listData = list.getJSONObject(i);
                            JSONArray weatherL = listData.getJSONArray("weather");
                            for (int j = 0; j < weatherL.length(); j++) {
                                JSONObject weather = weatherL.getJSONObject(j);
                                weatherItem = new City.weather(weather.getString("icon"),
                                        weather.getString("description"));
                                weatherList.add(weatherItem);
                            }
                            JSONObject main = listData.getJSONObject("main");

                            cityItem = new City(listData.getString("name"), listData.getInt("id"), weatherList, main.getInt("temp"));
                            allCity.add(cityItem);
                            onFinishedListener.onFinished(allCity);

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // onFinishedListener.onFinished(response.body().getCities());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

    List<weatherInfo.weather> weathers= new ArrayList<>();
    List<weatherInfo.main> temps= new ArrayList<>();
    weatherInfo info = null;
    weatherInfo.weather WItem = null;
    weatherInfo.main temper = null;

    @Override
    public void getDetails(final DetailsFinished detailsFinished, int id) {
        RetrofitInterface service = RetrofitC.getRetrofit().create(RetrofitInterface.class);

        Call<ResponseBody> call = service.getCities(URL.getWeatherDetails(id));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {

                        JSONObject jsonObject = new JSONObject(response.body().string());

                        JSONArray weatherL = jsonObject.getJSONArray("weather");
                        for (int i=0;i<weatherL.length();i++){
                            JSONObject weatherObject=weatherL.getJSONObject(i);
                            WItem=new weatherInfo.weather(weatherObject.getString("description"),
                                    weatherObject.getString("icon"));
                            weathers.add(WItem);
                        }
                        JSONObject main = jsonObject.getJSONObject("main");
                        temper = new weatherInfo.main(main.getInt("temp"),main.getInt("temp_min"),
                                main.getInt("temp_max"),main.getInt("pressure"),main.getInt("humidity"));
                        temps.add(temper);
                        JSONObject windObj=jsonObject.getJSONObject("wind");
                        info = new weatherInfo(weathers,temps,windObj.getDouble("speed"));
                   //     Log.e("main",main.getInt("temp")+" " +main.getInt("temp_min")+ " "+main.getInt("temp_max")+" "+ main.getInt("pressure")+" "+main.getInt("humidity"));
                        detailsFinished.onFinished(info);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            detailsFinished.onFailure(t);
            }

        });
    }
}