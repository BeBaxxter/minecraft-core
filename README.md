# What is my plugin?

Minecraft core is an open source template to create a minecraft plugin.

# Which features are included?

1. A EventListener that hear and trigger events (like player join)
2. A CommandHandler that will execute commands (like my examples).
3. A Config to create a default commands output (config.yml).
4. A Config to translate your plugin (translation.yml), default is EN.
5. A Custome Exception class (for developer only).

--> This sounds not so much, but enough for starting a new plugin.

# Can i use your code for new plugins?

Absolutely. Unfortunately I cannot implement all requirements and wishes. Therefore you may use my code to customize the plugin to your wishes. But if you have any ideas, please write me.


# Which events are included?

| Name | Usage |
| ------ | ------ |
| onPlayerJoin | write every player a custome welcome message when he join the server |


# Which commands are included?

| Command | Permission | Description |
| ------ | ------ | ------ |
| /wuff | core.player.wuff| player barks and get a nice cookie |
| /fly | core.player.fly | set current player flymode to on/off |
| /tele | core.player.tele | teleport one player to another player or coordinate |
