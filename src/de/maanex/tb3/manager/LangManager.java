package de.maanex.tb3.manager;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import de.maanex.tb3.Cls;


public class LangManager {

	public static final String	QUOTES_FROM		= "QUOTES_FROM";
	public static final String	QUOTES_INVALID	= "QUOTES_INVALID";

	//

	private LangManager() {
	}

	private static HashMap<String, String> lang = new HashMap<>();

	static {
		new BufferedReader(new InputStreamReader(LangManager.class.getResourceAsStream(Cls.LANG()))) //
				.lines() // For all lines
				.filter(s -> !s.equals("") && !s.startsWith("#")) // Deselect empty and comment lines
				.forEach(s -> {// Add in HashMap
					if (s.contains("#")) s = s.split("#")[0];
					lang.put(s.split("=")[0], s.split("=")[1]);
				});
	}

	//

	public static String getText(String key) {
		return lang.get(key);
	}

}
