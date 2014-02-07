package com.examsystem.m_jaribu;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

public class MyCustomBaseAdapter extends BaseAdapter {
	 private static ArrayList<SearchResults> searchArrayList;
	 
	 private LayoutInflater mInflater;

	 public MyCustomBaseAdapter(Context context, ArrayList<SearchResults> results) {
	  searchArrayList = results;
	  mInflater = LayoutInflater.from(context);
	 }

	 public int getCount() {
	  return searchArrayList.size();
	 }

	 public Object getItem(int position) {
	  return searchArrayList.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.rowview, null);
	   holder = new ViewHolder();
	   holder.txtName = (TextView) convertView.findViewById(R.id.name);
//	   holder.rone = (RadioButton) convertView.findViewById(R.id.radio_one);
//	   holder.rtwo = (RadioButton) convertView.findViewById(R.id.radio_two);
//	   holder.rthree = (RadioButton) convertView.findViewById(R.id.radio_three);
//	   holder.rfour = (RadioButton) convertView.findViewById(R.id.radio_four);

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.txtName.setText(searchArrayList.get(position).getName());
//	  holder.rone.setText(searchArrayList.get(position).getRone());
//	  holder.rtwo.setText(searchArrayList.get(position).getRtwo());
//	  holder.rthree.setText(searchArrayList.get(position).getRthree());
//	  holder.rfour.setText(searchArrayList.get(position).getRfour());

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView txtName;
//	 RadioButton  rone;
//	 RadioButton  rtwo;
//	 RadioButton  rthree;
//	 RadioButton  rfour;
	 
	 }
	}
