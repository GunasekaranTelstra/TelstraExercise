package com.telstra.exercise;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TelstraActivity extends Activity {

	ListView lst;
	Button refresh;
	TextView page_tittle;
	ArrayList<String> data = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_telstra);
		System.out.println(" stage 11111111");

		lst = (ListView) findViewById(R.id.list_views);
		page_tittle = (TextView) findViewById(R.id.page_tittle);
		
		refresh = (Button) findViewById(R.id.refresh);
		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Refresh_List();
			}
		});
		Refresh_List();
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
			page_tittle.setText(data.get(0));
			TelstraArrayAdapter itemsAdapter = new TelstraArrayAdapter(
					TelstraActivity.this, data);

			lst.setAdapter(itemsAdapter);

		}
	}

	private void Refresh_List() {

		InternetConnections ic = new InternetConnections();
		if (ic.isNetworkAvailable(TelstraActivity.this) == true) {
			AsyncTaskRunner as = new AsyncTaskRunner();
			as.execute("");
		} else {
			Toast.makeText(TelstraActivity.this,
					"Please check your Internet connection...",
					Toast.LENGTH_SHORT).show();
		}

	}
}
