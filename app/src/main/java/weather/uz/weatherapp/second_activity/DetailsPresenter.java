package weather.uz.weatherapp.second_activity;


import weather.uz.weatherapp.main_activity.MainContract;
import weather.uz.weatherapp.model.weatherInfo;

public class DetailsPresenter implements Contract.detailsPresenter,MainContract.Model.DetailsFinished {


    private Contract.detailsView view;
    private int id;
    private MainContract.Model detail;

    public DetailsPresenter(Contract.detailsView view, int id,MainContract.Model model) {
        this.view = view;
        this.id=id;
        detail=model;
    }

    @Override
    public void getDetails() {
        detail.getDetails(this,id);
    }

    @Override
    public void onDestroy() {
        view=null;
        id=0;
    }


    @Override
    public void onFinished(weatherInfo info) {
        if(info!=null){
            view.showDetails(info);
        view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        view.hideProgress();
    }
}
