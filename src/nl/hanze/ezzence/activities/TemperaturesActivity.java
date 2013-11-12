package nl.hanze.ezzence.activities;

import nl.hanze.ezzence.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TemperaturesActivity extends BaseActivity {

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
	}

	

	protected void execute(View v) {
		// TODO Auto-generated method stub
		
	}
}
