package weather.uz.weatherapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface RetrofitInterface {
    @GET("")
    Call<ResponseBody> getCities(@Url String url);

}

