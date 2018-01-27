package de.maanex.tb3;


import java.util.HashMap;


public class PreferenceManager {

	public static final String	CLIENT_SECRET	= "CLIENT_SECRET";
	public static final String	SERVER_ID		= "SERVER_ID";

	private PreferenceManager() {
	}

	private static HashMap<String, String> preferences = new HashMap<>();

	static {

	}

	public static String readPreference(String key) {
		return preferences.get(key);
	}

	public static int readIntPreference(String key) {
		return Integer.parseInt(preferences.get(key));
	}

	public static long readLongPreference(String key) {
		return Long.parseLong(preferences.get(key));
	}

}
