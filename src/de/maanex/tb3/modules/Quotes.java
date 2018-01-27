package de.maanex.tb3.modules;


import de.maanex.tb3.BotModule;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;


/**
 * 
 * @author Maanex
 *
 */
public class Quotes extends BotModule {

	public Quotes(String[] args) {
		super("Quotes", args);
	}

	@EventSubscriber
	public void onMessage(MessageEvent e) {

	}
}
