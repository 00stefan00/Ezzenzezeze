package nl.hanze.ezzence.activities;

import java.util.ArrayList;
import java.util.Map;

import nl.hanze.ezzence.R;
import nl.hanze.ezzence.config.Config;
import nl.hanze.ezzence.network.RESTRequest;
import nl.hanze.ezzence.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.graphics.Color;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author getConnected 2
 */

public class UsagesActivity extends BaseActivity {
	TableLayout tl;
	ArrayList<ArrayList<View>> tableArray = new ArrayList<ArrayList<View>>();
	boolean isGray = false;

	String type = "gas";
	TextView weekavg;
	TextView monthavg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usages);
		initLayout(R.string.title_activity_usages, true, true, true, true);

		weekavg = (TextView) findViewById(R.id.weekavg);
		monthavg = (TextView) findViewById(R.id.monthavg);

		buttonOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				execute(v);
			}
		});

		tableinit();
		// addTableRow("08:30", "12:00", "Groningen", "Berlijn", true, 2,
		// "sjors", "m");
		// addTableRow("08:30", "12:00", "Groningen", "Berlijn", true, 3,
		// "abaj", "f");
	}

	protected JSONObject request() {
		RESTRequest restRequest = new RESTRequest(Config.API_URL);

		restRequest.putString(Config.KEY_NAME, Config.GEOGRAPHY_NAME);
		restRequest.putString(Config.KEY_METHOD, "getBasicNeeds");
		restRequest.putString("login_token", login_token);
		restRequest.putString("type", type);

		JSONObject jsonObject = null;
		try {
			JSONParser jsonParser = JSONParser.getInstance();
			String response = restRequest.execute().get();
			Log.i("DEBUG", response);
			jsonObject = jsonParser.getObjectFromRequest(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	protected void execute(View v) {
		JSONObject json = request();
		try {
			json = processRequest(json);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (json != null) {
			try {
				weekavg.setText(json.get("week_average").toString());
				monthavg.setText(json.get("month_average").toString());

				for (int i = 0; i < ((JSONArray) json.get("days")).length(); i++) {
					addTableRow(((JSONObject) json.getJSONArray("days").get(i))
							.get("insert_date").toString(), ((JSONObject) json
							.getJSONArray("days").get(i)).get("value")
							.toString());
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initializes the table
	 */
	private void tableinit() {
		tl = (TableLayout) findViewById(R.id.usagesTable);

		TextView date = new TextView(this);
		date.setText(getResources().getString(R.string.tableDate));
		TextView tableValue = new TextView(this);
		tableValue.setText(getResources().getString(R.string.tableValue));

		TableRow rowHeader = new TableRow(this);

		rowHeader.addView(date);
		rowHeader.addView(tableValue);

		tl.addView(rowHeader, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tl.setStretchAllColumns(true);
	}

	private void addTableRow(String datestring, String valuestring) {
		TextView date = new TextView(this);
		date.setText(datestring);
		TextView value = new TextView(this);
		date.setText(valuestring);

		int color = getColor();
		TableRow datarow = new TableRow(this);

		ArrayList<View> dataArray = new ArrayList<View>();
		dataArray.add(date);
		dataArray.add(value);

		for (int i = 0; i < dataArray.size(); i++) {
			datarow.addView(dataArray.get(i));
		}

		tableArray.add(dataArray);

		datarow.setBackgroundColor(color);
		tl.addView(datarow, new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		tl.setStretchAllColumns(true);
	}

	//
	// protected void getMoreInformation(JSONObject obj) {
	// Intent i = new Intent(UsagesActivity.this, JoinRideActivity.class);
	// i.putExtra("json", obj.toString());
	// startActivity(i);
	// }
	//
	// /**
	// * Shows the date picker
	// *
	// * @param v
	// */
	// public void showDatePickerDialog(View v) {
	// DialogFragment newFragment = new DatePickerFragment();
	// newFragment.show(getFragmentManager(), "datePicker");
	//
	// if (DatePickerFragment.year == 2014) {
	// tl.removeAllViewsInLayout();
	// tableinit();
	// }
	// if (DatePickerFragment.year == 2013) {
	// tl.removeAllViewsInLayout();
	// tableinit();
	// }
	// tl.invalidate();
	// tl.refreshDrawableState();
	// }

	private int getColor() {
		isGray = !isGray;
		if (isGray) {
			return Color.LTGRAY;
		}
		return Color.WHITE;
	}
}
