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

public class AddItemActivity extends Activity{

	private Button mSubmit;
	private Button mCancel;
	private Spinner mItemList;
	private EditText mPrice;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcolitem);
		
		/* fill spinner */
		mItemList = (Spinner) findViewById(R.id.itemDropDown);
		List<String> list = new ArrayList<String>();
		//Todo fill this list with actual stuff
		list.add("list 1");
		list.add("list 2");
		list.add("list 3");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mItemList.setAdapter(dataAdapter);
		
		mPrice = (EditText) findViewById(R.id.priceView);
		
		mSubmit = (Button) findViewById(R.id.itemSubmit);
		mSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String priceString = mPrice.getText().toString();
            	float price;
            	if (priceString == null || priceString.isEmpty()) {
            		price = -1;
            	} else {
            		price = Float.valueOf(priceString);
            	}
            	String itemName = String.valueOf(mItemList.getSelectedItem());
            	//Todo need to implement preferences
            	CostOfLivingItem item = new CostOfLivingItem(itemName, price);
            	MainActivity.LIST.add(item);
            	onBackPressed();
            }
		});
		
		mCancel = (Button) findViewById(R.id.itemCancel);
		
		mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onBackPressed();
            }
		});
	}
}
