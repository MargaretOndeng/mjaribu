package com.examsystem.m_jaribu;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private  EditText usernametxt,passwrdtxt;
	private Button loginbtn,registerbtn;

	public String name,pass,res;
    /** Called when the activity is first created. */
    @Override
    //method
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerbtn=(Button) findViewById(R.id.button2);
        registerbtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

		    	
				registerNext();
				
				// TODO Auto-generated method stub
				
			}
		});
        usernametxt=(EditText) findViewById(R.id.editText1);
        passwrdtxt=(EditText) findViewById(R.id.editpass2);  
        loginbtn=(Button) findViewById(R.id.button1);
        loginbtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				loginNext();
				// TODO Auto-generated method stub
				
			}
		});
        
        
    }
    //method2
  
    private void loginNext(){
		name = usernametxt.getText().toString();
		pass = passwrdtxt.getText().toString();
	
    	ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    	postParameters.add(new BasicNameValuePair("username", name.trim()));
    	postParameters.add(new BasicNameValuePair("password", pass.trim()));
    	
    	String response = null;
    	try {
    		response = CustomHttpClient.executeHttpPost("http://10.0.2.2/mjaribu/androidlogin.php", postParameters);
    	    res = response.toString();
    	    //Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
    	    res= res.replaceAll("\\s+","");         	              	 
    	    if (res.toString().equals("N")) {
    	    	Toast.makeText(getApplicationContext(), "Invalid Login details", Toast.LENGTH_LONG).show();
    	    	
			} else {
				
				nextIntent();
			}
    	}
    	catch (Exception e) {
    		Toast.makeText(getApplicationContext(), "Sorry! Could not connect to the server.", Toast.LENGTH_LONG).show();
    	}
    	}
    public void registerNext(){
    	Intent z=new Intent();
    	z.setClass(MainActivity.this, Register.class);
    	
    	startActivity(z);
    	
    }
    public void nextIntent(){
    	
    	
//    	Intent i=new Intent(this, Dashboard.class);
//    	pass = passwrdtxt.getText().toString();
//    	//Toast.makeText(getApplicationContext(), "its"+pass, Toast.LENGTH_LONG).show();
//    	i.putExtra("usname",pass);
//    	startActivity(i);
    	
    	Intent y=new Intent();
    	y.setClass(MainActivity.this, Dashboard.class);
    	
    	startActivity(y);
    	
    	
}}