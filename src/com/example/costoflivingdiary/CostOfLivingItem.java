package com.example.costoflivingdiary;

import java.util.ArrayList;

public class CostOfLivingItem {

	private String mItem;
	private float mPrice;
	private String mDefaultPreference;
	private ArrayList<String> mPreferenceList = new ArrayList<String>();
	
	public CostOfLivingItem(String item, float price, String defaultPreference) {
		mItem = item;
		mPrice = price;
		mDefaultPreference = defaultPreference;
		mPreferenceList.add(defaultPreference);
	}
	
	public String getItem() {
		return mItem;
	}
	
	public void setItem(String item) {
		mItem = item;
	}
	
	public String getPriceString() {
		if (mPrice < 0) {
			return "Invalid price";
		}
		return Float.toString(mPrice);
	}
	
	public void setPrice(float price) {
		mPrice = price;
	}
	
	public String getDefaultPreference() {
		return mDefaultPreference;
	}
	
	public void setDefaultPreference(String defaultPreference) {
		mDefaultPreference = defaultPreference;
	}
	
	public ArrayList<String> getPreferenceList() {
		return mPreferenceList;
	}
	
	public void addPreference(String preference) {
		if(!mPreferenceList.contains(preference)){
			mPreferenceList.add(preference);
		}
	}
	
	public int getPreferenceSize() {
		return mPreferenceList.size();
	}
}
