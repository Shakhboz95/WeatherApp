package weather.uz.weatherapp.second_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import weather.uz.weatherapp.R;
import weather.uz.weatherapp.main_activity.ModelInteractor;
import weather.uz.weatherapp.model.weatherInfo;

public class DetailsActivity extends AppCompatActivity implements Contract.detailsView {

    private ProgressBar progressBar;
    private TextView temp, desc,pressure,humidity,wind,tempMin,tempMax;
    private ImageView img;
    private DetailsPresenter presenter;
    private int id;
    private String cityName;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
        showProgress();
        presenter=new DetailsPresenter(this,id,new ModelInteractor());
        presenter.getDetails();
    }

    private void init() {
id=getIntent().getIntExtra("id",0);
        cityName=getIntent().getStringExtra("city");
        Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
            progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
            progressBar.setIndeterminate(true);

            RelativeLayout relativeLayout = new RelativeLayout(this);
            relativeLayout.setGravity(Gravity.CENTER);
            relativeLayout.addView(progressBar);

            RelativeLayout.LayoutParams params = new
                    RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            progressBar.setVisibility(View.INVISIBLE);

            this.addContentView(relativeLayout, params);

            getSupportActionBar().setTitle(cityName);
        temp=findViewById(R.id.temp);
        desc=findViewById(R.id.condition);
        pressure=findViewById(R.id.pressure);
        humidity=findViewById(R.id.humidity);
        wind=findViewById(R.id.wind);
        tempMin=findViewById(R.id.temp_min);
        tempMax=findViewById(R.id.temp_max);
            img=findViewById(R.id.w_icon);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showDetails(weatherInfo info) {
            temp.setText(info.getMainList().get(0).getTemp()+"ºC");
           tempMin.setText(info.getMainList().get(0).getTemp_min()+" ºC");
          tempMax.setText(info.getMainList().get(0).getTemp_max()+" ºC");
           humidity.setText(info.getMainList().get(0).getHumidity()+"%");
           pressure.setText(info.getMainList().get(0).getPressure()+" hPa");
            desc.setText(info.getWeatherList().get(0).getCondition());
           wind.setText(info.getWind()+" m/s");
        Picasso.get().load("http://openweathermap.org/img/w/"+info.getWeatherList().get(0).getWeatherIcon()+".png").into(img);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
