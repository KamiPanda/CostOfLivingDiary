package com.example.costoflivingdiary;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PrefListPriceActivity extends ListActivity {
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			int index = bundle.getInt(MainActivity.ITEM_INDEX);
			CostOfLivingItem item = MainActivity.LIST.get(index);
			ArrayList<String> prefStringList = new ArrayList<String>();
			for(int i = 0; i < item.getPreferenceSize(); i++) {
				//TODO get prices somehow
				prefStringList.add("Price in " + item.getPreferenceList().get(i) + ": ");
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.prefitem, R.id.prefEntry, prefStringList);
			setListAdapter(adapter);
		}
	}
    

}
