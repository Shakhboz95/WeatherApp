package weather.uz.weatherapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import weather.uz.weatherapp.R;
import weather.uz.weatherapp.main_activity.RecyclerItemClick;
import weather.uz.weatherapp.model.City;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {
    private List<City> list;
    private RecyclerItemClick recyclerItemClick;
  public CityListAdapter(RecyclerItemClick recyclerItemClick) {
              this.list = new ArrayList<>();
      this.recyclerItemClick=recyclerItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View row=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(row);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
    viewHolder.cityName.setText(list.get(i).getCityName());
    viewHolder.desc.setText(list.get(i).getWeathers().get(i).getCondition());
    viewHolder.temp.setText(list.get(i).getTemp()+" ºC");
        Picasso.get().load("http://openweathermap.org/img/w/"+list.get(i).getWeathers().get(i).getWeatherIcon()+".png").into(viewHolder.image);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            recyclerItemClick.onItemClick(list.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView temp,cityName,desc;
        private ImageView image;
        public ViewHolder( View itemView) {
            super(itemView);
            temp=itemView.findViewById(R.id.city_temp);
            cityName=itemView.findViewById(R.id.city_name);
            image=itemView.findViewById(R.id.city_icon);
            desc=itemView.findViewById(R.id.weather_desc);
        }
    }

    public void setData(@NonNull List<City> data) {
        this.list = data;
        notifyDataSetChanged();
    }

}
