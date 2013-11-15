package com.example.costoflivingdiary;

public class CostOfLivingItem {

	private String mItem;
	private float mPrice;
	private String mDefaultPreference;
	private String mPreferencePrice;
	
	public CostOfLivingItem(String item, float price, String defaultPreference) {
		mItem = item;
		mPrice = price;
		mDefaultPreference = defaultPreference;
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
}
