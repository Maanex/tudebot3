package de.maanex.tb3.modules;


import java.util.Random;

import de.maanex.tb3.BotModule;
import de.maanex.tb3.ThreadingLib;
import de.maanex.tb3.manager.LangManager;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;


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
		if (!e.getChannel().getName().equals(getArgs()[0])) return;
		if (e.getMessage().getMentions().isEmpty()) {
			e.getMessage().delete();
			IMessage m = e.getChannel().sendMessage(LangManager.getText(LangManager.QUOTES_FROM));
			ThreadingLib.threadDelayed(5000, () -> m.delete());
			return;
		}

		IUser author = e.getMessage().getMentions().get(0);
		String content = e.getMessage().getContent().replaceFirst(author.mention() + " ", "");

		e.getMessage().delete();

		EmbedObject em = new EmbedBuilder() //
				.withColor(new Random().nextInt(0xffffff)) //
				.withFooterText(LangManager.getText(LangManager.QUOTES_FROM) + " " + e.getAuthor().getName()) //
				.withFooterIcon(e.getAuthor().getAvatarURL()) //
				.withAuthorName(author.getName()) //
				.withAuthorIcon(author.getAvatarURL()) //
				.withDescription("**" + content + "**\n\n\\_\\_\\_") //
				.build();

		e.getMessage().getChannel().sendMessage(em);
	}
}
