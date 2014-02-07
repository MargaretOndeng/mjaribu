package com.examsystem.m_jaribu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends Activity {
	private  EditText usernametxt,passwrdtxt;
	private Button testbtn,resultsbtn,profilebtn;

	public String name,pass,res;
    /** Called when the activity is first created. */
    @Override
    //method
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);}
    }