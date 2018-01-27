package de.maanex.tb3;


public abstract class BotModule {

	private String		name;
	private String[]	args;

	protected BotModule(String name, String[] args) {
		this.name = name;
		this.args = args;

		TudeBot.client.getDispatcher().registerListener(this);
	}

	public String getName() {
		return this.name;
	}

	public String[] getArgs() {
		return this.args;
	}

}
