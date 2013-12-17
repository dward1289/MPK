package com.DevonaWard.mpk;


import java.net.URL;
import java.util.ArrayList;

import com.loopj.android.image.SmartImageView;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PokemonAdapter extends BaseAdapter {
    private static ArrayList<theItems> PokemonArrayList;
    Bitmap bmp;
    URL url;
    
    private LayoutInflater mInflater;
 
    public PokemonAdapter(Context context, ArrayList<theItems> results) {
        PokemonArrayList = results;
        mInflater = LayoutInflater.from(context);
    }
 
    public int getCount() {
        return PokemonArrayList.size();
    }
 
    public Object getItem(int position) {
        return PokemonArrayList.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.PokemonName);
            holder.txtType = (TextView) convertView.findViewById(R.id.PokemonType);
            holder.txtDate = (TextView) convertView.findViewById(R.id.DateCaught);
            holder.txtLevel = (TextView) convertView.findViewById(R.id.PokemonLevel);
            holder.pic = (SmartImageView) convertView.findViewById(R.id.my_image);
             
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        holder.txtName.setText(PokemonArrayList.get(position).getName());
        holder.txtType.setText(PokemonArrayList.get(position).getType());
        holder.txtDate.setText(PokemonArrayList.get(position).getDate());
        holder.txtLevel.setText(PokemonArrayList.get(position).getLevel());
        holder.pic.setImageUrl(PokemonArrayList.get(position).getImage());
        return convertView;
    }
 
    static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtDate;
        TextView txtLevel;
        SmartImageView pic;
    }
}