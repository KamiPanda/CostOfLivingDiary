package com.example.costoflivingdiary;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddItemActivity extends Activity{

	private Button mSubmit;
	private Button mCancel;
	//TODO possibly add these back in once we figure out how we want to do countries (here or as preferences)
	//private AutoCompleteTextView mCountryList;
	//private EditText mCountry;
	private Spinner mItemList;
	private EditText mPrice;
	
	private static final String[] COUNTRIES = new String[] { "Afghanistan", "Aland Islands", "Albania", "Alderney", "Algeria", "Andorra", "Angola",
		"Antigua And Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh",
		"Barbados", "Belarus", "Belgium", "Belize", "Bermuda", "Bhutan", "Bolivia", "Bosnia And Herzegovina", "Botswana", "Brazil",
		"British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
		"Chad", "Chile", "China", "Colombia", "Congo", "Costa Rica", "Cote Divoire", "Croatia", "Cuba", "Curacao", "Cyprus", "Czech Republic",
		"Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
		"Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "Gabon", "Gambia",
		"Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-bissau",
		"Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle Of Man", "Israel",
		"Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
		"Liberia", "Libya", "Lithuania", "Luxembourg", "Macao", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
		"Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro",
		"Morocco", "Mozambique", "Myanmar", "Namibia", "Nepal", "Netherlands", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria",
		"Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palestinian Territory", "Panama", "Papua New Guinea", "Paraguay", "Peru",
		"Philippines", "Poland", "Portugal", "Puerto Rico", "Qatar", "Republic Of Congo", "Reunion", "Romania", "Russia", "Rwanda", "Saint Kitts And Nevis",
		"Saint Lucia", "Samoa", "Saudi Arabia", "Senegal", "Serbia", "Seychelles","Singapore","Slovakia", "Slovenia", "Somalia", "South Africa",
		"South Korea", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan",
		"Tajikistan", "Tanzania", "Thailand", "Timor-leste", "Togo", "Tonga", "Trinidad And Tobago", "Tunisia", "Turkey", "Turkmenistan",
		"Turks And Caicos Islands", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Us Virgin Islands",
		"Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcolitem);
		
		//FIXME need to add view for country list here, or add country preferences
		//mCountryList = (AutoCompleteTextView) findViewById(R.id.???);
		//ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this, android.R.layout.???, COUNTRIES);
		//countryAdapter.setDropDownViewResource(android.R.layout.???);
		//mCountryList.setAdapter(countryAdapter);
		//mCountry = (EditText) findViewById(R.id.??);
		
		/* fill spinner */
		mItemList = (Spinner) findViewById(R.id.itemDropDown);
		List<String> list = new ArrayList<String>();
		//TODO fill this list with actual stuff
		String country = "United States";
		String item = queryNumbeo(country);
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
            	//TODO need to implement preferences
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

	private String queryNumbeo(String country) {
		String baseURL = "www.numbeo.com";
		//need an actual API key
		String query = baseURL + "/api/country_prices?api_key=test111&country=" + country;
		try {
		HttpClient httpclient = new DefaultHttpClient();
	    HttpResponse response = httpclient.execute(new HttpGet(query));
	    StatusLine statusLine = response.getStatusLine();
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        out.close();
	        String responseString = out.toString();
	        //parse out response
	    } else{
	        //Closes the connection.
	        response.getEntity().getContent().close();
	        throw new IOException(statusLine.getReasonPhrase());
	    }
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return null;
	}
}
