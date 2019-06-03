package weather.uz.weatherapp.main_activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import weather.uz.weatherapp.R;
import weather.uz.weatherapp.adapter.CityListAdapter;
import weather.uz.weatherapp.model.City;
import weather.uz.weatherapp.second_activity.DetailsActivity;

public class MainActivity extends AppCompatActivity implements MainContract.MainView,RecyclerItemClick{

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Presenter presenter;
    private CityListAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initProgressBar();
        showProgress();
        adapter=new CityListAdapter( MainActivity.this);
        recyclerView.setAdapter(adapter);
        presenter=new Presenter(this, new ModelInteractor());
        presenter.requestDataFromServer();

    }

    private void init() {

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
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
    public void setData(List<City> cityList) {
        if (cityList!=null){
            adapter.setData(cityList);
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onItemClick(Object obj) {
        City city=(City)obj;
        Intent intent=new Intent(this, DetailsActivity.class);
        intent.putExtra("id",city.getCityId());
        intent.putExtra("city",city.getCityName());
        startActivity(intent);
    }
}
