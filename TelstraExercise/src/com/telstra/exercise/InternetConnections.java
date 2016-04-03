package com.telstra.exercise;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetConnections {

	private static InternetConnections instance = null;

	public boolean isInternetAlive = false;
	private boolean isNetworkMet = false;

	// private static ConnectivityManager mgr;
	// private static NetworkInfo netInfo;
	private Context context;

	private InternetConnections() {

	}// End of private constructor to hide constructor call

	public static synchronized InternetConnections getInstance(Context context) {
		
		

		if (instance == null) {

			instance = new InternetConnections();

		}

		instance.context = context;
		// Check Net work availability
		instance.isInternetAlive = instance.isNetworkAvailable();
		return instance;
	}

	public boolean isNetworkAvailable() {
		Context context = this.context;
		isNetworkMet = false;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			// boitealerte(this.getString(R.string.alert),
			// "getSystemService rend null");

		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						isNetworkMet = true;
						return true;
					}
				}
				showToast();
			}
		}
		return false;
	}

	private void showToast() {
		// TODO Auto-generated method stub
		if (isNetworkMet != true) {
			Toast.makeText(context, "Please check your Internet connection...",
					Toast.LENGTH_SHORT).show();
			
		}

	}

}
