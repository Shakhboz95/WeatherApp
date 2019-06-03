package weather.uz.weatherapp.network;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import weather.uz.weatherapp.URL;

public class RetrofitC {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
