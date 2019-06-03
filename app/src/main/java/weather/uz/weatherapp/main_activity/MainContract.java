package weather.uz.weatherapp.main_activity;

import java.util.List;

import weather.uz.weatherapp.model.City;
import weather.uz.weatherapp.model.weatherInfo;

public interface MainContract {


    interface presenter{

        void onDestroy();
        void requestDataFromServer();
    }

    interface MainView {

        void showProgress();
        void hideProgress();
        void setData(List<City> cityList);
        void onResponseFailure(Throwable throwable);
    }

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<City> cityList);
            void onFailure(Throwable t);
        }
        void getCityList(OnFinishedListener onFinishedListener);

        interface DetailsFinished{
            void onFinished(weatherInfo info);
            void onFailure(Throwable t);
        }
        void getDetails(DetailsFinished detailsFinished,int id);

    }
}
