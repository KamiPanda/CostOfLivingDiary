package com.example.costoflivingdiary;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddPreferenceActivity extends Activity{
	
	private Button mSubmit;
	private Button mCancel;
	private Spinner mCountriesList;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addpref);
		mCountriesList = (Spinner) findViewById(R.id.countryPicker);
		List<String> list = new ArrayList<String>();
		//Todo fill this list with actual stuff
		list.add("list 1");
		list.add("list 2");
		list.add("list 3");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCountriesList.setAdapter(dataAdapter);
		
		mSubmit = (Button) findViewById(R.id.prefSubmit);
		mSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String country = String.valueOf(mCountriesList.getSelectedItem());
            	//Todo need to implement preferences
            	PreferenceItem item = new PreferenceItem(country, false);
            	MainActivity.PREF_LIST.add(item);
            	onBackPressed();
            }
		});
		
		mCancel = (Button) findViewById(R.id.prefCancel);
		
		mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onBackPressed();
            }
		});
	}
}
