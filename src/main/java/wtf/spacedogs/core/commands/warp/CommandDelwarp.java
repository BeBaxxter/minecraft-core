package wtf.spacedogs.core.commands.warp;

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
 * CommandDelwarp
 *
 * command, remove a warp with the given name
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-19
 */

public class CommandDelwarp implements CommandExecutor {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final JavaPlugin jp;

	/**
	 * mapping jp to plugin to get access to server information
	 */
	public CommandDelwarp(JavaPlugin plugin) {
		jp = plugin;
	}

	/**
	 * checks if the given warp exists and remove them from warps.db
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
		
		if(args.length != 1) {
			return false;
		}
			
		if(wrp.removeWarp(args[0]) == 0) {
			p.sendMessage(langConfig.getString("Core.command.warp.removed"));
		} else if(wrp.removeWarp(args[0]) == 1) {
			p.sendMessage(langConfig.getString("Core.command.warp.notexists"));
			p.sendMessage("--------------------------------------");
			wrp.showAllWarps(p);
			p.sendMessage("--------------------------------------");	
		} else {
			p.sendMessage("Core.plugin.error");
		}

		return true;
	}
}
