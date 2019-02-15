# TudeBot v3.0

This is the 3rd version of TudeBot for Discord.
You can test it live here: http://discord.tude.ga/

**Please notice that this bot was made for personal use. You can still use it for your server but I cannot guarantee that everything is working perfectly outside the tested environment**

**Please also notice that current versions of this bot are closed source again. You can ask for having a look though.**

## Features
### Done
- Quotes Channel
- Memes rating
- Dynamic Voice Channel System

### Planned
- Statistics
- Youtube Video playing
- Youtube livestream playing
- Save and load song playlists
- Voice control for music, etc (everyone can dream)

## Dependencies
* [Discord4j](https://discord4j.com/) (v2.9.3)
* [JSON-java](https://github.com/stleary/JSON-java)

## Setup
1. Clone repository in the IDE of your choice
2. Add d4j & json.org dependencies
3. Go in `cls` and edit `settings.prop` with your values *(do not use " for string values)*
4. Done

## Files
### settings.prop
* **CLIENT_TOKEN:** Your Discord Bot User Secret
* **SERVER_ID:** Deprecated. Just leave this one empty.
* **PLAYING_TEXT:** Change what the bot is playing (In Discord)
* **LANG:** Change the language in which the bot replies

### modules.prop
A # marks a comment.
To add a new module use this syntax:
`package.Class [Arg1] [Arg2] [...]`

### ?.lang
A language file. Filename (before .lang) marks the language, simply write this as **LANG** in **settings.prop**
