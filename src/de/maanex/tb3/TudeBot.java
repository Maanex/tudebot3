package de.maanex.tb3;


import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;


public class TudeBot {

	private TudeBot() {
	}

	//

	public static IDiscordClient	client;
	public static IGuild			guild;

	//

	public static void main(String[] args) {
		client = new ClientBuilder().withToken(SettingManager.readPreference(SettingManager.CLIENT_SECRET)).login();
		ThreadingLib.newThread(() -> {
			try {
				while (!client.isReady())
					Thread.sleep(500);

				client.changePlayingText(SettingManager.readPreference(SettingManager.PLAYING_TEXT));
				guild = client.getGuildByID(SettingManager.readLongPreference(SettingManager.SERVER_ID));

				ModuleLoader.loadModules();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void addModule(BotModule mod) {

	}

}
