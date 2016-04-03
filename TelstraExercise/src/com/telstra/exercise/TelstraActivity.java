package com.telstra.exercise;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TelstraActivity extends Activity {

	ListView lst;
	ArrayList<String> data = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_telstra);
		System.out.println(" stage 11111111");

		lst = (ListView) findViewById(R.id.list_views);
		
		AsyncTaskRunner as = new AsyncTaskRunner();
		as.execute("");
		
		}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		System.out.println(" stage 22222222222222");

	}

	public class AsyncTaskRunner extends AsyncTask<String, Void, String> {
		ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(TelstraActivity.this);
			pd.setMessage("Loading...");
			pd.show();

		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			NetworkProcess ns = new NetworkProcess();
			data = ns.HTTP_Request();
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			
			TelstraArrayAdapter itemsAdapter = 
				    new TelstraArrayAdapter(TelstraActivity.this, data);
		
			lst.setAdapter(itemsAdapter);
			
		}
	}
}
