package de.maanex.tb3;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


public class SettingManager {

	public static final String	CLIENT_SECRET	= "CLIENT_SECRET";
	public static final String	SERVER_ID		= "SERVER_ID";
	public static final String	PLAYING_TEXT	= "PLAYING_TEXT";

	//

	private SettingManager() {
	}

	private static HashMap<String, String> preferences = new HashMap<>();

	static {
		new BufferedReader(new InputStreamReader(SettingManager.class.getResourceAsStream(Cls.SETTINGS))) //
				.lines() // For all lines
				.filter(s -> !s.equals("") && !s.startsWith("#")) // Deselect empty and comment lines
				.forEach(s -> {// Add in HashMap
					if (s.contains("#")) s = s.split("#")[0];
					preferences.put(s.split("=")[0], s.split("=")[1]);
				});
	}

	//

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
