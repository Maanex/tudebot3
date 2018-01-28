package de.maanex.tb3.modules;


import java.util.HashMap;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import de.maanex.tb3.BotModule;
import de.maanex.tb3.ThreadingLib;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IReaction;


public class Memes extends BotModule {

	public Memes(String[] args) {
		super("Memes", args);
	}

	private static final HashMap<Emoji, Integer> emojis = new HashMap<>();

	private static final int	REACTION_ADD_DELAY	= 100;	// 0 is not possible due to rate limits
	private static final int	BOUNDS_FACTOR		= 3;

	static {
		emojis.put(EmojiManager.getForAlias("fire"), 2);
		emojis.put(EmojiManager.getForAlias("arrow_up"), 1);
		emojis.put(EmojiManager.getForAlias("arrow_down"), -1);
		emojis.put(EmojiManager.getForAlias("poop"), -2);
	}

	@EventSubscriber
	public void onMessage(MessageReceivedEvent e) {
		if (!e.getChannel().getName().equalsIgnoreCase(getArgs()[0])) return;
		if (e.getAuthor().isBot()) return;
		if (e.getMessage().getAttachments().isEmpty()) return;

		ThreadingLib.newThread(() -> {
			e.getMessage().addReaction(EmojiManager.getForAlias("star"));
			for (Emoji emo : emojis.keySet()) {
				ThreadingLib.sleep(REACTION_ADD_DELAY);
				e.getMessage().addReaction(emo);
			}
		});
	}

	@SuppressWarnings("deprecation")
	@EventSubscriber
	public void onReaction(ReactionAddEvent e) {
		if (!e.getChannel().getName().equalsIgnoreCase(getArgs()[0])) return;
		if (e.getAuthor().isBot()) return;
		if (e.getUser().isBot()) return;

		if (!e.getReaction().isCustomEmoji() && e.getReaction().getUnicodeEmoji().getHtmlDecimal().equals(EmojiManager.getForAlias("star").getHtmlDecimal())) {
			e.getUser().getOrCreatePMChannel().sendMessage(e.getMessage().getAttachments().get(0).getUrl());
			return;
		}

		int memeRating = 0, bounds = e.getMessage().getGuild().getTotalMemberCount() / BOUNDS_FACTOR;
		for (Emoji emo : emojis.keySet()) {
			IReaction r = e.getMessage().getReactionByUnicode(emo);
			if (r != null) memeRating += emojis.get(emo) * r.getCount();
		}

		if (memeRating < -bounds) // Meme is too bad for this world
			e.getMessage().delete();
		if (memeRating > bounds && memeRating <= bounds + 2 && !e.getChannel().getPinnedMessages().contains(e.getMessage())) // This Meme is on fire
			e.getChannel().pin(e.getMessage());
	}
}
