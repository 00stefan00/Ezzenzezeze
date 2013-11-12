package nl.hanze.ezzence.activities;

import java.util.ArrayList;

import nl.hanze.ezzence.R;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.graphics.Color;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usages);
		initLayout(R.string.title_activity_usages, true, true, true,
				false);

//		tableinit();
//		addTableRow("08:30", "12:00", "Groningen", "Berlijn", true, 2, "sjors", "m");
//		addTableRow("08:30", "12:00", "Groningen", "Berlijn", true, 3, "abaj", "f");
	}

//	/**
//	 * Initializes the table
//	 */
//	private void tableinit() {
//		tl = (TableLayout) findViewById(R.id.usagesTable);
//
//		TextView departure_time = new TextView(this);
//		departure_time.setText(getResources()
//				.getString(R.string.departure_time));
//		TextView arrival_time = new TextView(this);
//		arrival_time.setText(getResources().getString(R.string.arrival_time));
//		TextView from = new TextView(this);
//		from.setText(getResources().getString(R.string.location_from));
//		TextView destination = new TextView(this);
//		destination.setText(getResources().getString(
//				R.string.location_destination));
//
//		TableRow rowHeader = new TableRow(this);
//
//		rowHeader.addView(departure_time);
//		rowHeader.addView(arrival_time);
//		rowHeader.addView(from);
//		rowHeader.addView(destination);
//
//		tl.addView(rowHeader, new TableLayout.LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		tl.setStretchAllColumns(true);
//	}
//
//	/**
//	 * Adds a table row to a given table
//	 * 
//	 * @param departurev
//	 * @param arrivalv
//	 * @param fromv
//	 * @param destinationv
//	 */
//	private void addTableRow(String departurev, String arrivalv, String fromv,
//			String destinationv, Boolean includeButton, int id, String driverName, String driverGender) {
//		TextView departure_time = new TextView(this);
//		departure_time.setText(departurev);
//		TextView arrival_time = new TextView(this);
//		arrival_time.setText(arrivalv);
//		TextView from = new TextView(this);
//		from.setText(fromv);
//		TextView destination = new TextView(this);
//		destination.setText(destinationv);
//
//		int color=getColor();
//		TableRow datarow = new TableRow(this);
//		
//		ArrayList<View> dataArray = new ArrayList<View>();
//		dataArray.add(departure_time);
//		dataArray.add(arrival_time);
//		dataArray.add(from);
//		dataArray.add(destination);
//		
//		
//		for(int i = 0; i < dataArray.size(); i++){
//			datarow.addView(dataArray.get(i));
//		}	
//		
//		final JSONObject obj = new JSONObject();
//	    try {
//	      obj.put("departureTime", departurev);
//	      obj.put("arrivalTime", arrivalv);
//	      obj.put("departurePlace", fromv);
//	      obj.put("arrivalPlace", destinationv);
//	      obj.put("rideID", id);
//	      obj.put("driverName", driverName);
//	      obj.put("driverGender", driverGender);
//	    } catch (JSONException e) {
//	      e.printStackTrace();
//	    }
//		
//		//if (includeButton) {
//			
//			tableArray.add(dataArray);
//			Button button = new Button(this);
//			button.setId(tableArray.size()-1);
//			button.setOnClickListener(new View.OnClickListener() {
//			    public void onClick(View v) {
//			    	getMoreInformation(obj);
//			      }
//			    });
//			//datarow.addView(button);
//		//}
//
//		datarow.setBackgroundColor(color);
//		tl.addView(datarow, new TableLayout.LayoutParams(
//				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//		
//		TableRow buttonRow = new TableRow(this);
//		buttonRow.setBackgroundColor(color);
//		buttonRow.addView(button);
//		buttonRow.setGravity(Gravity.CENTER_HORIZONTAL);
//		tl.addView(buttonRow, new TableLayout.LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//
//		tl.setStretchAllColumns(true);
//	}
//
//	protected void getMoreInformation(JSONObject obj) {
//		  Intent i = new Intent(UsagesActivity.this, JoinRideActivity.class);
//		  i.putExtra("json", obj.toString());
//		  startActivity(i);
//		}
//
//	/**
//	 * Shows the date picker
//	 * 
//	 * @param v
//	 */
//	public void showDatePickerDialog(View v) {
//		DialogFragment newFragment = new DatePickerFragment();
//		newFragment.show(getFragmentManager(), "datePicker");
//
//		if (DatePickerFragment.year == 2014) {
//			tl.removeAllViewsInLayout();
//			tableinit();
//		}
//		if (DatePickerFragment.year == 2013) {
//			tl.removeAllViewsInLayout();
//			tableinit();
//		}
//		tl.invalidate();
//		tl.refreshDrawableState();
//	}
	
	private int getColor()
	{
		isGray=!isGray;
		if(isGray) {
			return Color.LTGRAY;
		}
		return Color.WHITE;
	}
}
