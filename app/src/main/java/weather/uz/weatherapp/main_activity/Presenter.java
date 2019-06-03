package weather.uz.weatherapp.main_activity;

import java.util.List;

import weather.uz.weatherapp.model.City;

public class Presenter implements MainContract.presenter, MainContract.Model.OnFinishedListener{

    private MainContract.MainView view;
    private MainContract.Model getNoticeIntractor;

    public Presenter(MainContract.MainView view, MainContract.Model getNoticeIntractor) {
        this.view = view;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {
        view =null;
    }

    @Override
    public void requestDataFromServer() {
        getNoticeIntractor.getCityList(this);
    }

    @Override
    public void onFinished(List<City> cityList) {
        if(view != null){
            view.setData(cityList);
            view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(view != null){
            view.onResponseFailure(t);
            view.hideProgress();
        }
    }
}
