package com.bl4ck5pyda.bl4ck5pyda.android_test;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motivaimagine.motivaimagine_us_trial.rest_client.user.models.city;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gpaez on 11/19/2017.
 */

public class city_adapter extends RecyclerView.Adapter<city_adapter.CityViewHolder> implements ItemClickListener {

    public List<city> items;
    public final Context context;





    public static class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item

        public TextView city_name;
        public TextView city_temp;
        public TextView city_desc;

        public ItemClickListener listener;
        CardView cardView;

        public CityViewHolder(View v, ItemClickListener listener) {
            super(v);
            city_name=(TextView) v.findViewById(R.id.name_city);
            city_temp=(TextView) v.findViewById(R.id.txt_temp);
            city_desc=(TextView) v.findViewById(R.id.txt_condicion);
            this.listener=listener;
            cardView=(CardView) itemView.findViewById(R.id.card_city);
            v.setOnClickListener(this);

        }


        public void onClick(View v) {

            listener.onItemClick(v, getAdapterPosition());

        }
    }

    public city_adapter(Context context,ArrayList<city> items) {

        this.context = context;
        this.items = items;

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public CityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_city, viewGroup, false);
        return new CityViewHolder(v,this);


    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        city currentItem = items.get(position);
        holder.city_name.setText(currentItem.getName());
        holder.city_temp.setText(currentItem.getTemperature()+"°C");
        String currentDesc="";

        if(!TextUtils.isEmpty(currentItem.getHumidity())) {
            currentDesc = currentItem.getHumidity();
        }
        if(!TextUtils.isEmpty(currentItem.getWind_speed())) {
            currentDesc+="\n"+currentItem.getWind_speed();
        }
        if(!TextUtils.isEmpty(currentItem.getWeather_desc())) {
            currentDesc+="\n"+currentItem.getWeather_desc();
        }
if(isTablet(context)){
    holder.city_desc.setText(currentDesc);
}

    }








    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position) {

        City_details fragmentDemo = City_details.newInstance(items.get(position));

        pushFragment(fragmentDemo,context);
    }


    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    public void pushFragment(Fragment newFragment, Context context){

        FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, newFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
