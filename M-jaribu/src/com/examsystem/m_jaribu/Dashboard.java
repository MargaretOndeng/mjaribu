package com.examsystem.m_jaribu;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Dashboard extends Activity {
	private  EditText usernametxt,passwrdtxt;
	private Button testbtn,resultsbtn,profilebtn;

	public String name,pass,res;
    /** Called when the activity is first created. */
    @Override
    //method
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        
        testbtn=(Button) findViewById(R.id.buttontest);
        testbtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

		    	
				takeTest();
				
				// TODO Auto-generated method stub
				
			}
		});
        
        resultsbtn=(Button) findViewById(R.id.buttonresults);
        resultsbtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

		    	
				viewResults();
				
				// TODO Auto-generated method stub
				
			}
		});
        
        
        
     
        
        profilebtn=(Button) findViewById(R.id.buttonprofile);
        profilebtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

		    	
				editProfile();
				
				// TODO Auto-generated method stub
				
			}
		});
        }
    
    
    public void takeTest(){
    	
    	Intent x=new Intent();
    	x.setClass(Dashboard.this, TakeTest.class);
    	
    	startActivity(x);
    }
    public void viewResults(){
    	Intent y=new Intent();
    	y.setClass(Dashboard.this, Results.class);
    	
    	startActivity(y);
    }
    public void editProfile(){
    	Intent z=new Intent();
    	z.setClass(Dashboard.this, Profile.class);
    	
    	startActivity(z);
    }
    
    
    
    }
