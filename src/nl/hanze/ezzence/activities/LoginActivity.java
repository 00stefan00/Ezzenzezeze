package nl.hanze.ezzence.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import nl.hanze.ezzence.R;
import nl.hanze.ezzence.security.Login;
import org.json.JSONObject;

/**
 * User: johan
 * Date: 11/12/13
 * Time: 11:46 AM
 */
public class LoginActivity extends BaseActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initLayout(R.string.login_header, false, false, false, false);
	}

	public void login(View view) {
		EditText username = (EditText) findViewById(R.id.login_username);
		EditText password = (EditText) findViewById(R.id.login_password);
		JSONObject jsonObject = Login.login(username.getText().toString(), password.getText().toString());
		try{
			jsonObject = processRequest(jsonObject);
			if(jsonObject != null) {
				super_user = jsonObject.getBoolean("super_user");
				login_token = jsonObject.getString("login_token");

				startIntentByButton(view);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loginUsingPin(View view) {

	}
}