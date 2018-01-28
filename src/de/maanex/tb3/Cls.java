package de.maanex.tb3;


import de.maanex.tb3.manager.SettingManager;


public class Cls {

	private Cls() {
	}

	public static final String	SETTINGS	= "/settings.prop";
	public static final String	MODULES		= "/modules.prop";

	public static final String LANG() {
		return "/" + SettingManager.readSetting(SettingManager.LANG) + ".lang";
	};

}
