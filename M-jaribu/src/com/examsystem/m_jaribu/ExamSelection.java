package com.examsystem.m_jaribu;





import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import android.CustomHttpClient;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExamSelection extends Activity {
	private EditText passwrdtxt;
	final Context context =this;
	String pass,res,candidate;
	//private CharSequence name;
	private Button Vote;
	private TextView txtv;
	private CharSequence text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examselection);
        //Vote=(Button) findViewById(R.id.buttonvote);
//        Bundle extras=getIntent().getExtras();
//        
//       
//        if(extras !=null){
//        	
//        	text=extras.getCharSequence("usname");
//        	
//        	
//        }
      
//       
       
        ArrayList<SearchResults> searchResults = GetSearchResults();
//       
      final ListView lv1 = (ListView) findViewById(R.id.ListView01);
      lv1.setAdapter(new MyCustomBaseAdapter(this, searchResults));
//       
//        lv1.setOnItemClickListener(new OnItemClickListener() {
//        
//         
//		public void onItemClick(AdapterView<?> a, View v, final int position,
//				long id) {
//			ZeecodersActivity zr =new ZeecodersActivity();
//			 pass= zr.pass;
//        	// loginNext();
//		      
//				Object o = lv1.getItemAtPosition(position);
//		          SearchResults fullObject = (SearchResults)o;
//			 candidate=fullObject.getName().toString();
//			 AlertDialog.Builder builder = new AlertDialog.Builder(context);
//				builder.setTitle("Cast Vote");
//				//candidate=fullObject.getName().toString();
//				builder.setMessage("Are you sure you want to vote for"+ " " + candidate )
//				       .setCancelable(false)
//				       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//				           public void onClick(DialogInterface dialog, int id) {
//				        	   
//			
//	        
//	         // candidate=fullObject.getName().toString();
//	          //Toast.makeText(Presidents.this, "You have chosen: " + " " + candidate, Toast.LENGTH_LONG).show();
//	       
//	      	 ArrayList<NameValuePair> getParameters = new ArrayList<NameValuePair>();
//	      	//System.out.println(candidate.trim());
//	      	getParameters.add(new BasicNameValuePair("candidateid",candidate.trim()) );
//	      	
//
//	      	//Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG).show();
//	      	
//	      	getParameters.add(new BasicNameValuePair("password", text.toString().trim()));
//	      	String response = null;
//	      	try {
//	      		response = CustomHttpClient.executeHttpPost("http://10.0.2.2/zeecoders/prezstatus2.php", getParameters);
//	      	    res = response.toString();
//	      	  
//	      	    res= res.replaceAll("\\s+","");         	              	 
//	      	    if (res.toString().equals("y")) {
//	      	    	//Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
//	      	    	
//	  			} else {
//	  				String jf=response.toString();
//	  				Toast.makeText(getApplicationContext(), jf, Toast.LENGTH_LONG).show();
//	  				
//	  			}
//	      	} 
//	      	catch (Exception e) {
//	      		
//	      		Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
//	      	}
//				          
//				           finish();
//				           }
//				       })
//				       .setNegativeButton("No", new DialogInterface.OnClickListener() {
//				           public void onClick(DialogInterface dialog, int id) {
//				                dialog.cancel();
//				           }
//				       });
//				AlertDialog alert = builder.create();
//				alert.setIcon(R.drawable.icon);
//				alert.show();
//	          
//		} 
//        });
    }
  
   
    private ArrayList<SearchResults> GetSearchResults(){
     //ArrayList<SearchResults> results = new ArrayList<SearchResults>();
     
     
     JSONArray jArray;
		String result = null;
		InputStream is = null;
		StringBuilder sb=null;
		HttpClient httpclient;
		HttpPost httppost;
		HttpResponse response;
		HttpEntity entity;
	
		ArrayList<NameValuePair> nameValuePairs;
		ArrayList<SearchResults> results1 = new ArrayList<SearchResults>();
		try{
		    httpclient = new DefaultHttpClient();
		    nameValuePairs = new ArrayList<NameValuePair>(2);
		    httppost = new HttpPost("http://10.0.2.2/mjaribu/readexam.php");
		    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    response = httpclient.execute(httppost);
		   	entity = response.getEntity();
		   	is = entity.getContent();
		   //Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
		   }catch(Exception e){
		       Log.e("log_tag", "Error in http connection"+e.toString());
		  }
		//convert response to string
		try{
		    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		     sb = new StringBuilder();
		     sb.append(reader.readLine() + "\n");
		     String line="0";
		     while ((line = reader.readLine()) != null) {
		                    sb.append(line + "\n");
		      }
		      is.close();
		      result=sb.toString();
		      
		      }catch(Exception e){
		            Log.e("log_tag", "Error converting result "+e.toString());
		      }
		//parsing data
		      SearchResults sr11;
		
 		String name="";
		
		try{
		    jArray = new JSONArray(result);
		    JSONObject json_data=null;
		    for(int i=0;i<jArray.length();i++){
		           json_data = jArray.getJSONObject(i);
		          
		           name=json_data.getString("exam_name");
		           
		           
		         	sr11= new SearchResults();
		           
		           sr11.setName(name);
		           
		           results1.add(sr11);

		       }
		    
		  //  Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
		    
		    }
		    catch(JSONException e1){
		    	String ee1=e1.toString();
		  	  Toast.makeText(getBaseContext(), ee1 ,Toast.LENGTH_LONG).show();
		    } catch (ParseException e1) {
		    	String ee2=e1.toString();
		    	Toast.makeText(getBaseContext(), ee2 ,Toast.LENGTH_LONG).show();
					//e1.printStackTrace();
		    }
			
     
     return results1;
    }
   
    public void nextIntent(){
//   	Intent i=new Intent(Presidents.this, Posts.class);
////    	//i.putExtra("usname");
//   	startActivity(i);
////    	
//    	
}
}
