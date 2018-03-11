package com.chitingwang.listviewcustomadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ettbe on 2/21/2018.
 */

public class ImageTextArrayAdapter extends ArrayAdapter<Planet> {
    public final static String TAG = "ImageTextArrayAdapter";
    int layoutResourceId;
    Context context;
    private List<Planet> data;
    private LayoutInflater inflater;

    public ImageTextArrayAdapter(Context context, int layoutResourceId, List<Planet> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    static class PlanetHolder {
        ImageView imgLogo;
        ImageView costLogo;
        TextView txtName;
        TextView txtType;
        TextView txtAddress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PlanetHolder holder = null;
        if (null == convertView) {
            Log.d(TAG, "getView: rowView null: position make new holder" + position);
            convertView = inflater.inflate(layoutResourceId, parent, false);
            holder = new PlanetHolder();
            holder.imgLogo = (ImageView)convertView.findViewById(R.id.planet_logo);
            holder.costLogo = (ImageView)convertView.findViewById(R.id.cost_logo);
            holder.txtName = (TextView)convertView.findViewById(R.id.planet_name);
            holder.txtType = (TextView)convertView.findViewById(R.id.planet_type);
            holder.txtAddress = (TextView)convertView.findViewById(R.id.restaurant_address);
            // Tags can be used to store data within a view
            convertView.setTag(holder);
        }
        else {
            Log.d(TAG, "getView: rowView !null - reuse holder: position " + position);
            holder = (PlanetHolder)convertView.getTag();
        }
        // Display the information for that item
        Planet planet = data.get(position);
        holder.txtName.setText(planet.name);
        holder.txtType.setText(planet.foodtype);
        holder.txtAddress.setText(planet.address);
        holder.imgLogo.setImageResource(planet.logo);
        holder.costLogo.setImageResource(planet.cost);

        return convertView;
    }




}
