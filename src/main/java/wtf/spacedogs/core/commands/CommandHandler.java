package wtf.spacedogs.core.commands;

import org.bukkit.plugin.java.JavaPlugin;

import wtf.spacedogs.core.commands.basic.CommandFly;
import wtf.spacedogs.core.commands.basic.CommandTele;
import wtf.spacedogs.core.commands.basic.CommandWuff;
import wtf.spacedogs.core.commands.warp.CommandDelwarp;
import wtf.spacedogs.core.commands.warp.CommandSetwarp;
import wtf.spacedogs.core.commands.warp.CommandWarp;

/**
 * <h1>CommandHandler</h1>
 * <p>
 * The CommandHandler only call the command classes for the given commands
 * </p>
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-18
 */

public class CommandHandler {

	private final JavaPlugin jp;

	public CommandHandler(JavaPlugin plugin) {

		jp = plugin;
	}

	/**
	 * This method initiate commands and set their correct executor
	 */
	public void initiateCommands() {
		jp.getCommand("wuff").setExecutor(new CommandWuff(jp));
		jp.getCommand("fly").setExecutor(new CommandFly(jp));
		jp.getCommand("tele").setExecutor(new CommandTele(jp));
		jp.getCommand("warp").setExecutor(new CommandWarp(jp));
		jp.getCommand("setwarp").setExecutor(new CommandSetwarp(jp));
		jp.getCommand("delwarp").setExecutor(new CommandDelwarp(jp));
	}
}