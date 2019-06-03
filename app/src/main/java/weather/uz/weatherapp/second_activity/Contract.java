package weather.uz.weatherapp.second_activity;


import weather.uz.weatherapp.model.weatherInfo;

public interface Contract {


    interface detailsPresenter{

        void getDetails();
        void onDestroy();
    }

    interface detailsView {

        void showProgress();
        void hideProgress();
        void showDetails(weatherInfo info);
    }

}
