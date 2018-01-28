package de.maanex.tb3.modules;


import java.time.ZoneId;
import java.util.HashMap;
import java.util.Random;

import de.maanex.tb3.BotModule;
import de.maanex.tb3.ThreadingLib;
import de.maanex.tb3.manager.LangManager;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
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

	private HashMap<IMessage, IUser> quotesBy = new HashMap<>();

	@EventSubscriber
	public void onMessage(MessageReceivedEvent e) {
		if (!e.getChannel().getName().equalsIgnoreCase(getArgs()[0])) return;
		if (e.getAuthor().isBot()) return;
		if (e.getMessage().getMentions().isEmpty()) {
			e.getMessage().delete();
			IMessage m = e.getChannel().sendMessage(LangManager.getText(LangManager.QUOTES_INVALID));
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

		IMessage quote = e.getMessage().getChannel().sendMessage(em);
		quotesBy.put(quote, e.getAuthor());
	}

	@SuppressWarnings("deprecation")
	@EventSubscriber
	public void onReaction(ReactionAddEvent e) {
		if (!e.getChannel().getName().equalsIgnoreCase(getArgs()[0])) return;
		if (e.getUser().isBot()) return;
		if (e.getReaction().isCustomEmoji() || !e.getReaction().getUnicodeEmoji().getHtmlDecimal().equals("&#10060;")) return;

		if (quotesBy.containsKey(e.getMessage())) {
			if (e.getUser().equals(quotesBy.get(e.getMessage()))) {
				if (System.currentTimeMillis() - e.getMessage().getCreationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() < 100 * 60 * 5) {
					e.getMessage().delete();
					return;
				}
			}
		}
		e.getMessage().removeReaction(e.getUser(), e.getReaction().getEmoji());
	}
}
