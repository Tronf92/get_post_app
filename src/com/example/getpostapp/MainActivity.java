package com.example.getpostapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_main);
		makePost("http://192.168.1.112:80/adrian.bsd/android/httpd/www/testpost.php");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void makePost(String url){
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		String responseText;
		try{
			List <NameValuePair> data = new ArrayList<NameValuePair>();
			data.add(new BasicNameValuePair("data","true"));
			data.add(new BasicNameValuePair("data2","true"));
			data.add(new BasicNameValuePair("data3","true"));
			httppost.setEntity(new UrlEncodedFormEntity(data));
			HttpResponse  response = httpclient.execute(httppost);
			
			responseText = EntityUtils.toString(response.getEntity());
			Toast.makeText(this, responseText,Toast.LENGTH_SHORT).show();
		}
		catch (ClientProtocolException e){
			Log.d("myapp",e.getMessage().toString()+ "client protocol exception");
		}
		catch (IOException e){
			Log.d("myapp",e.getMessage().toString() + "io exception");
		}
	}
}
