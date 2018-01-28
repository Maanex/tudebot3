package de.maanex.tb3.manager;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import de.maanex.tb3.Cls;


public class SettingManager {

	public static final String	CLIENT_SECRET	= "CLIENT_SECRET";
	public static final String	SERVER_ID		= "SERVER_ID";
	public static final String	PLAYING_TEXT	= "PLAYING_TEXT";
	public static final String	LANG			= "LANG";

	//

	private SettingManager() {
	}

	private static HashMap<String, String> settings = new HashMap<>();

	static {
		new BufferedReader(new InputStreamReader(SettingManager.class.getResourceAsStream(Cls.SETTINGS))) //
				.lines() // For all lines
				.filter(s -> !s.equals("") && !s.startsWith("#")) // Deselect empty and comment lines
				.forEach(s -> {// Add in HashMap
					if (s.contains("#")) s = s.split("#")[0];
					settings.put(s.split("=")[0], s.split("=")[1]);
				});
	}

	//

	public static String readSetting(String key) {
		return settings.get(key);
	}

	public static int readIntSetting(String key) {
		return Integer.parseInt(settings.get(key));
	}

	public static long readLongSetting(String key) {
		return Long.parseLong(settings.get(key));
	}

}
