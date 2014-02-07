package com.examsystem.m_jaribu;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	private  EditText fnametxt,lnametxt,unametxt,passtxt,pass2txt,mailtxt,phonetxt,countytxt;
	private Button registerbtn;
	String fname,lname,pass1,pass2,uname,mail,phone,county,res;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        fnametxt=(EditText) findViewById(R.id.editfname);
        lnametxt=(EditText) findViewById(R.id.editlname);
        unametxt=(EditText) findViewById(R.id.editusername);
        passtxt=(EditText) findViewById(R.id.editpass1);
        pass2txt=(EditText) findViewById(R.id.editpass2);
        mailtxt=(EditText) findViewById(R.id.editmail);
        phonetxt=(EditText) findViewById(R.id.editphone);
        countytxt=(EditText) findViewById(R.id.editcounty);
        
        registerbtn=(Button) findViewById(R.id.buttonregister);
        registerbtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				register();
				
			}
		});

}
	 public void register(){

			fname = fnametxt.getText().toString();
			lname = lnametxt.getText().toString();
			pass1 = passtxt.getText().toString();
			pass2 = pass2txt.getText().toString();
			uname =unametxt.getText().toString();
			mail = mailtxt.getText().toString();
			phone = phonetxt.getText().toString();
			county = countytxt.getText().toString();
			
	    	 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    	postParameters.add(new BasicNameValuePair("student_fname", fname.trim()));
	    	postParameters.add(new BasicNameValuePair("student_fname", lname.trim()));
	    	postParameters.add(new BasicNameValuePair("password", pass1.trim()));
//	    	postParameters.add(new BasicNameValuePair("username", pass2.trim()));
	    	postParameters.add(new BasicNameValuePair("username", uname.trim()));
	    	postParameters.add(new BasicNameValuePair("email", mail.trim()));
	    	postParameters.add(new BasicNameValuePair("mobile", phone.trim()));
	    	//postParameters.add(new BasicNameValuePair("password", county.trim()));
	    	
	    	
	    	
	    	String response = null;
	    	try {
	    		response = CustomHttpClient.executeHttpPost("http://10.0.2.2/mjaribu/register.php", postParameters);
	    	    res = response.toString();
	    	  
	    	    res= res.replaceAll("\\s+","");         	              	 
	    	    if (res.toString().equals("N")) {
	    	    	Toast.makeText(getApplicationContext(), "Invalid Login details", Toast.LENGTH_LONG).show();
	    	    	
				} else {
					Toast.makeText(getApplicationContext(), "succesfully registered", Toast.LENGTH_LONG).show();
					
				}
	    	} 
	    	catch (Exception e) {
	    		//Toast.makeText(getApplicationContext(), "Sorry! Could not connect to the server.", Toast.LENGTH_LONG).show();
	    		Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
	    	}
	    }
}
