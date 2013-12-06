package com.example.costoflivingdiary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;
//
//public class QueryNumbeo extends AsyncTask <Void, Void, String> {
//	
//	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
//       InputStream in = entity.getContent();
//         StringBuffer out = new StringBuffer();
//         int n = 1;
//         while (n>0) {
//             byte[] b = new byte[4096];
//             n =  in.read(b);
//             if (n>0) out.append(new String(b, 0, n));
//         }
//         return out.toString();
//    }
//	
//	@Override
//	protected String doInBackground(Void... params) {
//		 HttpClient httpClient = new DefaultHttpClient();
//		 HttpContext localContext = new BasicHttpContext();
//         HttpGet httpGet = new HttpGet("http://www.numbeo.com/api/country_prices?api_key=umd_edu_640&country=Argentina");
//         String text = null;
//         try {
//               HttpResponse response = httpClient.execute(httpGet, localContext);
//               HttpEntity entity = response.getEntity();
//               text = getASCIIContentFromEntity(entity);
//         } catch (Exception e) {
//        	 return e.getLocalizedMessage();
//         }
//         return text;
//	}	
//	
//	protected void onPostExecute(String results) {
//		if (results!=null) {
//			EditText et = (EditText)findViewById(R.id.my_edit);
//			et.setText(results);
//		}
//		Button b = (Button)findViewById(R.id.my_button);
//		b.setClickable(true);
//	}
//}
class QueryNumbeo extends AsyncTask<String, String, String>{
	
	String res;

    @Override
    protected String doInBackground(String... uri) {
    	HttpClient httpClient = new DefaultHttpClient();
		 HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet("http://www.numbeo.com/api/country_prices?api_key=umd_edu_640&country=Argentina");
        String text = null;
        try {
              HttpResponse response = httpClient.execute(httpGet, localContext);
              HttpEntity entity = response.getEntity();
              text = getASCIIContentFromEntity(entity);
        } catch (Exception e) {
       	 return e.getLocalizedMessage();
        }
        return text;
    }
    
    protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	       InputStream in = entity.getContent();
	         StringBuffer out = new StringBuffer();
	         int n = 1;
	         while (n>0) {
	             byte[] b = new byte[4096];
	             n =  in.read(b);
	             if (n>0) out.append(new String(b, 0, n));
	         }
	         return out.toString();
	    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String s = doInBackground(result);
        res = s;
        System.out.println("ROLL: " + s);
    }
}

