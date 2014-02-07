package com.examsystem.m_jaribu;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class TakeTest extends Activity {
	InputStream is = null;
	String result = null;
	String line = null;
	Button examchoicebtn;
	String[] countyname, subjectname,classname,countyid;
	CharSequence countyselect;
	CharSequence subjectselect;
	CharSequence classselect;
	Spinner subjectSpinner, countySpinner,classSpinner;
	EditText houseNumber, tenantName, rent;
	String numberHouse, nameTenant, doah, res, ress;
	//long countyid,subjectid,classid;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taketest);
		
		subjectSpinner = (Spinner) findViewById(R.id.spinnerSubject);
		countySpinner = (Spinner) findViewById(R.id.spinnerCounty);
		classSpinner = (Spinner) findViewById(R.id.spinnerClass);
		examchoicebtn = (Button) findViewById(R.id.buttonExam);
		examchoicebtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

		    	
				examSelect();
				
				// TODO Auto-generated method stub
				
			}
		});
		

		final List<String> list2 = new ArrayList<String>();
		
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2/mjaribu/readcounty.php");
			HttpResponse response = httpclient.execute(httppost);
			Log.e("Fail 1", "3");

			HttpEntity entity = response.getEntity();
			Log.e("Fail 1", "4");

			is = entity.getContent();
			Log.e("Pass 1", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(getApplicationContext(), "Invalid IP Address",
					Toast.LENGTH_LONG).show();
			// finish();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}

		try {
			JSONArray JA = new JSONArray(result);
			JSONObject json = null;
			// id = new String[JA.length()];
			countyname = new String[JA.length()];
			countyid= new String[JA.length()];
			

			for (int i = 0; i < JA.length(); i++) {
				json = JA.getJSONObject(i);
				// id[i] = json.getString("national_id");
				countyname[i] = json.getString("county_name");
				countyid[i] = json.getString("county_id");
			}
			// Toast.makeText(getApplicationContext(), "welcome",
			// Toast.LENGTH_LONG).show();

			for (int i = 0; i < countyname.length; i++) {
				// list1.add(id[i]);
				list2.add(countyname[i]);
			}

			spinner_fn();

		} catch (Exception e) {

			Log.e("Fail 3", e.toString());
			// login.this.finish();

		}
		
		idNOtenant();	
	}

	private void spinner_fn() {
		// TODO Auto-generated method stub

		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_spinner_item,
				countyname);
		dataAdapter2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		countySpinner.setAdapter(dataAdapter2);

		countySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				//countyid = arg3; 
			countyselect= countySpinner.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), countyselect, Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

	}

	private void idNOtenant() {


		final List<String> list1 = new ArrayList<String>();
		// String response1;
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2/mjaribu/readsubject.php");
			HttpResponse response = httpclient.execute(httppost);
			Log.e("Fail 1", "3");

			HttpEntity entity = response.getEntity();
			Log.e("Fail 1", "4");

			is = entity.getContent();
			Log.e("Pass 1", "connection success ");

		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(getApplicationContext(), "Invalid IP Address",
					Toast.LENGTH_LONG).show();
			// finish();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}

		try {
			JSONArray JA = new JSONArray(result);
			JSONObject json = null;
			subjectname = new String[JA.length()];
			// countyname = new String[JA.length()];

			for (int i = 0; i < JA.length(); i++) {
				json = JA.getJSONObject(i);
				subjectname[i] = json.getString("subject_name");
				// countyname[i] = json.getString("countyname");
			}
			// Toast.makeText(getApplicationContext(), "welcome",
			// Toast.LENGTH_LONG).show();

			for (int i = 0; i < subjectname.length; i++) {
				list1.add(subjectname[i]);
				// list1.add(countyname[i]);
			}

			spinner_fn2();

		} catch (Exception e) {

			Log.e("Fail 3", e.toString());
			// login.this.finish();

		}
		callClass();
	}

	private void spinner_fn2() {
		// TODO Auto-generated method stub

		ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_spinner_item,
				subjectname);
		dataAdapter3
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		subjectSpinner.setAdapter(dataAdapter3);

		subjectSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub

				subjectselect = subjectSpinner.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), subjectselect, Toast.LENGTH_SHORT).show();

				//subjectid=arg3;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

	}
	private void callClass() {


		final List<String> list3 = new ArrayList<String>();
		// String response1;
		try {

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://10.0.2.2/mjaribu/readclass.php");
			HttpResponse response = httpclient.execute(httppost);
			Log.e("Fail 1", "3");

			HttpEntity entity = response.getEntity();
			Log.e("Fail 1", "4");

			is = entity.getContent();
			Log.e("Pass 1", "connection success ");

		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(getApplicationContext(), "Invalid IP Address",
					Toast.LENGTH_LONG).show();
			// finish();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}

		try {
			JSONArray JA = new JSONArray(result);
			JSONObject json = null;
			classname = new String[JA.length()];
			// countyname = new String[JA.length()];

			for (int i = 0; i < JA.length(); i++) {
				json = JA.getJSONObject(i);
				classname[i] = json.getString("class_name");
				// countyname[i] = json.getString("countyname");
			}
			// Toast.makeText(getApplicationContext(), "welcome",
			// Toast.LENGTH_LONG).show();

			for (int i = 0; i < classname.length; i++) {
				list3.add(classname[i]);
				// list1.add(countyname[i]);
			}

			spinner_fn3();

		} catch (Exception e) {

			Log.e("Fail 3", e.toString());
			// login.this.finish();

		}

	}

	private void spinner_fn3() {
		// TODO Auto-generated method stub

		ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_spinner_item,
				classname);
		dataAdapter3
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		classSpinner.setAdapter(dataAdapter3);

		classSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				//classid=arg3;
				classselect = classSpinner.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), classselect, Toast.LENGTH_SHORT).show();


			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

	}
	public void examSelect(){
		Toast.makeText(getApplicationContext(), countyselect, Toast.LENGTH_SHORT).show();
		Toast.makeText(getApplicationContext(), subjectselect, Toast.LENGTH_SHORT).show();

		Toast.makeText(getApplicationContext(), classselect, Toast.LENGTH_SHORT).show();


		
	}

}