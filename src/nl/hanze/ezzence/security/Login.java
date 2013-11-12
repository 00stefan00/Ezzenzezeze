package nl.hanze.ezzence.security;

import android.util.Log;
import nl.hanze.ezzence.config.Config;
import nl.hanze.ezzence.network.RESTRequest;
import org.json.JSONObject;

/**
 * User: johan
 * Date: 11/12/13
 * Time: 12:28 PM
 */
public class Login {

	public void createPinEntry(int pin) {

	}

	public static JSONObject login(String username, String password) {
		RESTRequest restRequest = new RESTRequest(Config.API_URL);

		restRequest.putString(Config.KEY_NAME, Config.USER_NAME);
		restRequest.putString(Config.KEY_METHOD, "login");
		restRequest.putString("username", username);
		restRequest.putString("password", password);

		Log.d("url", restRequest.toString());
		JSONObject jsonObject = null;
		try {
//			JSONParser jsonParser = JSONParser.getInstance();
			String response = restRequest.execute().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
