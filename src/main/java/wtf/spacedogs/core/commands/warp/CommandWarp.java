package wtf.spacedogs.core.commands.warp;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import wtf.spacedogs.core.api.IWarp;
import wtf.spacedogs.core.commands.PlayerHelper;
import wtf.spacedogs.core.config.ConfigHelper;

/**
 * CommandWarp
 *
 * command, that warp a player to the given warpname
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-19
 */

public class CommandWarp implements CommandExecutor {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final JavaPlugin jp;

	/**
	 * mapping jp to plugin to get access to server information
	 */
	public CommandWarp(JavaPlugin plugin) {
		jp = plugin;
	}

	/**
	 * teleport the player to the written warp
	 *
	 * @param sender, sender of the command (server or player)
	 * @param command, the command that was send
	 * @param label, label of the command
	 * @param args, arguments that are written behind the command (like warp name)
	 *
	 * @return false if the arguments of the plugin are larger than 1
	 * @return true if the command is executed correctly
	 */

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		ConfigHelper ch = new ConfigHelper(jp);
		FileConfiguration langConfig = ch.getConfigFile("translation.yml");
		IWarp wrp = new IWarp("warps.db", jp);

		PlayerHelper pl = new PlayerHelper(jp);
		Player p = pl.getPlayerInstance(sender);

		//if player dont enter a warpname
		if (args.length != 1) {
			p.sendMessage(langConfig.getString("Core.command.warp.notexists"));
			p.sendMessage("--------------------------------------");
			wrp.showAllWarps(p);
			p.sendMessage("--------------------------------------");
			return true;
		}

		Location wLocation = wrp.getWarp(args[0]);

		if (wLocation != null) {
			p.teleport(wLocation);
			p.sendMessage("Core.command.warp.teleported" + args[0]);

		} else {
			p.sendMessage(langConfig.getString("Bitte gieb einen richtigen Namen ein du Hurensohn"));
			p.sendMessage("--------------------------------------");
			wrp.showAllWarps(p);
			p.sendMessage("--------------------------------------");
		}
		return true;
	}
}
