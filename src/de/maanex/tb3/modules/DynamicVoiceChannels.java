package de.maanex.tb3.modules;


import java.util.EnumSet;

import de.maanex.tb3.BotModule;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelJoinEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelLeaveEvent;
import sx.blah.discord.handle.impl.events.guild.voice.user.UserVoiceChannelMoveEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.Permissions;


public class DynamicVoiceChannels extends BotModule {

	public DynamicVoiceChannels(String[] args) {
		super("Dynamic Voice Channel", args);
	}

	@EventSubscriber
	public void onChannelJoin(UserVoiceChannelJoinEvent e) {
		if (e.getVoiceChannel().getName().equalsIgnoreCase(getArgs()[1])) joinVoice(e.getUser(), e.getVoiceChannel());
	}

	@EventSubscriber
	public void onChannelLeave(UserVoiceChannelLeaveEvent e) {
		if (e.getVoiceChannel().getCategory().getName().equalsIgnoreCase(getArgs()[0])) leaveVoice(e.getUser(), e.getVoiceChannel());
	}

	@EventSubscriber
	public void onChannelMove(UserVoiceChannelMoveEvent e) {
		if (e.getNewChannel().getName().equalsIgnoreCase(getArgs()[1])) joinVoice(e.getUser(), e.getVoiceChannel());
		if (e.getOldChannel().getCategory().getName().equalsIgnoreCase(getArgs()[0])) leaveVoice(e.getUser(), e.getVoiceChannel());
	}

	private void joinVoice(IUser user, IChannel channel) {
		if (channel.getUsersHere().size() == 1) {
			IVoiceChannel n = channel.getCategory().createVoiceChannel(getArgs()[1]);

			channel.overrideRolePermissions(channel.getGuild().getEveryoneRole(), EnumSet.of(Permissions.MANAGE_CHANNEL), EnumSet.of(Permissions.VOICE_MOVE_MEMBERS));
			n.overrideRolePermissions(channel.getGuild().getEveryoneRole(), EnumSet.of(Permissions.VOICE_CONNECT), EnumSet.of(Permissions.VOICE_MOVE_MEMBERS));
		}
	}

	private void leaveVoice(IUser user, IChannel channel) {
		if (channel.getUsersHere().isEmpty()) channel.delete();
	}

}
