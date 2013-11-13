package nl.hanze.ezzence.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import nl.hanze.ezzence.R;
import nl.hanze.ezzence.config.Config;
import nl.hanze.ezzence.network.RESTRequest;
import nl.hanze.ezzence.utils.JSONParser;
import org.json.JSONObject;

public class TemperaturesActivity extends BaseActivity {

	private TextView tempIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temperatures);
		initLayout(R.string.title_activity_temperatures, true, true, true, true);
		buttonOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				execute(v);
			}
		});
		this.tempIndicator = (TextView) findViewById(R.id.temp_indicator);
		this.setTempIndicator();
		Log.d("current temperature", getCurrentTemperature());
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.setTempIndicator();
	}

	protected void onRestart() {
		super.onRestart();
		this.setTempIndicator();
	}

	protected void execute(View v) {
		EditText temperature = (EditText) findViewById(R.id.editText1);
		setTemperature(temperature.getText().toString());
	}

	protected void setTemperature(String temperature) {
		if(super_user) {
			RESTRequest restRequest = new RESTRequest(Config.API_URL);
			restRequest.putString(Config.KEY_NAME, Config.GEOGRAPHY_NAME);
			restRequest.putString(Config.KEY_METHOD, "changeTempSetting");
			restRequest.putString("login_token", login_token);
			restRequest.putString("value", temperature);
			try {
				String response = restRequest.execute().get();
				Log.d("response from temp", response);
				setTempIndicator();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void setTempIndicator() {
		tempIndicator.setText(getCurrentTemperature() + " C*");
	}

	protected String getCurrentTemperature() {
		RESTRequest restRequest = new RESTRequest(Config.API_URL);
		restRequest.putString(Config.KEY_NAME, Config.GEOGRAPHY_NAME);
		restRequest.putString(Config.KEY_METHOD, "getCurrentTemp");
		restRequest.putString("login_token", login_token);
		String temp = "";
		try {
			JSONParser jsonParser = JSONParser.getInstance();
			String response = restRequest.execute().get();
			Log.d("response from temp", response);
			JSONObject jsonObject = jsonParser.getObjectFromRequest(response);
			jsonObject = processRequest(jsonObject);
			if(jsonObject == null) return null;
			temp = jsonObject.getString("temp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("temp", temp);
		return temp;
	}
}
