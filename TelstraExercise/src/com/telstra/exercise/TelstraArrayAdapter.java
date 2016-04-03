package com.telstra.exercise;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TelstraArrayAdapter extends ArrayAdapter {

	ArrayList<String> data = new ArrayList<String>();
	Context cont;
	public ImageLoader imageLoader;

	private static class ViewHolder {
		TextView tittle;
		TextView discription;
		ImageView img;

	}

	public TelstraArrayAdapter(Context context, ArrayList<String> data2) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.telstra_layout, data2);
		cont = context;
		data = data2;
		imageLoader = new ImageLoader(context.getApplicationContext());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.telstra_layout, parent,
					false);
			viewHolder.tittle = (TextView) convertView
					.findViewById(R.id.tittle_view);
			viewHolder.discription = (TextView) convertView
					.findViewById(R.id.dec_view);
			viewHolder.img = (ImageView) convertView
					.findViewById(R.id.image_view);
			convertView.setTag(viewHolder);
			viewHolder.tittle.setText(data.get(position * 3 + 1));
			viewHolder.discription.setText(data.get(position * 3 + 2));
			imageLoader.DisplayImage("https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg",
					viewHolder.img);
			System.out.println("images is*****" + data.get(position * 3 + 3));
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// 04-03 00:39:08.414: I/System.out(1702): images
		// is*****http://fyimusic.ca/wp-content/uploads/2008/06/hockey-night-in-canada.thumbnail.jpg

		return convertView;
	}

}
