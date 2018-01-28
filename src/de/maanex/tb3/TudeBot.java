package de.maanex.tb3;


import java.util.ArrayList;
import java.util.List;

import de.maanex.tb3.manager.SettingManager;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;


public class TudeBot {

	private TudeBot() {
	}

	//

	public static IDiscordClient	client;
	public static IGuild			guild;

	private static List<BotModule> mods = new ArrayList<>();

	//

	public static void main(String[] args) {
		client = new ClientBuilder().withToken(SettingManager.readSetting(SettingManager.CLIENT_SECRET)).login();
		ThreadingLib.newThread(() -> {
			while (!client.isReady())
				ThreadingLib.sleep(500);

			client.changePlayingText(SettingManager.readSetting(SettingManager.PLAYING_TEXT));
			guild = client.getGuildByID(SettingManager.readLongSetting(SettingManager.SERVER_ID));

			ModuleLoader.loadModules();
			mods.forEach(m -> client.getDispatcher().registerListener(m));
		});
	}

	public static void addModule(BotModule mod) {
		mods.add(mod);
	}

}
