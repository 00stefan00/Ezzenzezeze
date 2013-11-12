package nl.hanze.ezzence.activities;

import java.util.ArrayList;

import nl.hanze.ezzence.R;
import nl.hanze.ezzence.config.Config;
import nl.hanze.ezzence.network.RESTRequest;
import nl.hanze.ezzence.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

public class UsagesActivity extends BaseActivity {
	TableLayout tl;
	ArrayList<ArrayList<View>> tableArray = new ArrayList<ArrayList<View>>();
	boolean isGray = false;

	String type = "gas";
	TextView weekavg, monthavg, typefield;
	Button gas, water, electra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}
	
	private void init() {
		setContentView(R.layout.activity_usages);
		initLayout(R.string.title_activity_usages, true, true, true, false);

		weekavg = (TextView) findViewById(R.id.weekavg);
		monthavg = (TextView) findViewById(R.id.monthavg);
		typefield = (TextView) findViewById(R.id.textView1);
		
		findViewById(R.id.gass).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setgas();
				init();
			}
		});
		findViewById(R.id.water).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setwater();
				init();
			}
		});
		findViewById(R.id.electricity).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setelectricity();
				init();
			}
		});
		
		typefield.setText(type);

		tableinit();
		execute();
	}

	public void setwater(){
		type = "water";
	}
	public void setelectricity(){
		type = "electricity";
	}
	public void setgas(){
		type = "gas";
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

	protected void execute() {
		tl.removeAllViewsInLayout();
		tableinit();
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
					String date = ((JSONObject) json.getJSONArray("days")
							.get(i)).get("insert_date").toString();
					String value = ((JSONObject) json.getJSONArray("days").get(
							i)).get("value").toString();
					Log.i("DEBUG", date + "" + value);
					addTableRow(date, value);
				}

			} catch (JSONException e) {
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
		value.setText(valuestring);

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
