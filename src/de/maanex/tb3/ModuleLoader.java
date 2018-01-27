package de.maanex.tb3;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;


public class ModuleLoader {

	private ModuleLoader() {
	}

	//

	public synchronized static void loadModules() {
		new BufferedReader(new InputStreamReader(SettingManager.class.getResourceAsStream(Cls.MODULES))) //
				.lines() //
				.filter(s -> !s.equals("") && !s.startsWith("#")) //
				.forEach(s -> { //
					if (s.contains("#")) s = s.split("#")[0];
					String classname = s.split(" ")[0];
					s = s.replaceFirst(classname + " ", "");
					String[] agrs = s.split(" ");
					loadModule(classname, agrs);
				});
	}

	private synchronized static void loadModule(String classname, String[] args) {
		try {
			Class<?> c = Class.forName(classname);
			if (!c.getSuperclass().equals(BotModule.class)) return;
			Constructor<?> struct = c.getConstructor(String[].class);
			BotModule mod = (BotModule) struct.newInstance((Object) args);
			TudeBot.addModule(mod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
