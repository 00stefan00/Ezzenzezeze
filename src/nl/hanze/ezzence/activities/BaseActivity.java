package nl.hanze.ezzence.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public abstract class BaseActivity extends Activity {

	protected TextView txtHeading;
	protected Button buttonBack;
	protected Button buttonOk;
	protected Button buttonHome;

	protected static final String activityPackage = "com.app.getconnected.activities";

	protected static boolean loggedIn = false;

	/**
	 * Simulates the go back method on a phone
	 */
	protected void goBack() {
		super.onBackPressed();
	}

	/**
	 * @param view
	 */
	public void startIntentByButton(View view) {
		Button button = (Button) view;
		if (!button.getTag().equals("")) {
			try {
				Intent intent = new Intent(getApplicationContext(),
						Class.forName(BaseActivity.activityPackage + "."
								+ button.getTag().toString()));
				startActivityForResult(intent, 1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
