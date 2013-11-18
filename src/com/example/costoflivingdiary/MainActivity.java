package com.example.costoflivingdiary;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	final static String ITEM_INDEX = "ITEM_INDEX";
	final static ArrayList<CostOfLivingItem> LIST = new ArrayList<CostOfLivingItem>();
	private boolean mFooterAdded = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        if(!mFooterAdded) {
        	mFooterAdded = true;
        	LayoutInflater inflater = getActivity().getLayoutInflater();		
        	View view = inflater.inflate(R.layout.itemfooter, null);
            view.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AddItemActivity.class);
                    startActivity(intent);
                }
            });
        	getListView().addFooterView(view);
        }
        addItems();
	}

	public void addItems() {
		setListAdapter(new ItemAdapter(getActivity(),
				R.layout.colitem, LIST));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
    public void onResume(){
    	super.onResume();
        addItems();
    }
	
	private Activity getActivity(){
		return this;
	}

	private class ItemAdapter extends ArrayAdapter<CostOfLivingItem> {
    	private ArrayList<CostOfLivingItem> items;
    	
        public ItemAdapter(Context context, int textViewResourceId,
                ArrayList<CostOfLivingItem> objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
        }
        
        public View getView(int position, View convertView, ViewGroup parent){
            View v = convertView;

            if(v == null){
                LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
                v = vi.inflate(R.layout.colitem, null);
            }

            final CostOfLivingItem item = items.get(position);
            final int pos = position;
            
            v.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                	Bundle bundle = new Bundle();
                	bundle.putInt(ITEM_INDEX, pos);
                	Intent intent = new Intent(getActivity(), PrefListPriceActivity.class);
                	intent.putExtras(bundle);
                	startActivity(intent);
                }
            });

            if (item != null){
                TextView itemView = (TextView) v.findViewById(R.id.item);
                TextView price = (TextView) v.findViewById(R.id.price);  
                TextView pref = (TextView) v.findViewById(R.id.pref); 
                
                itemView.setText("Item: " + item.getItem());
                price.setText("Price: " + item.getPriceString());
                // Todo need to add in price from database
                pref.setText(item.getDefaultPreference() + ": ");

            }       
            return v;           
        }           
    }
}
