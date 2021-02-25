package wtf.spacedogs.core.commands.basic;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import wtf.spacedogs.core.commands.PlayerHelper;
import wtf.spacedogs.core.config.ConfigHelper;

/**
 * CommandTele
 *
 * command, teleport a player to another player or to coordinates
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-18
 */

public class CommandTele implements CommandExecutor {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final JavaPlugin jp;

	/**
	 * mapping jp to plugin to get access to server information
	 */
	public CommandTele(JavaPlugin plugin) {
		jp = plugin;
	}

	/**
	 * teleport a player to another player or coordinates
	 *
	 * @param sender, sender of the command (server or player)
	 * @param command, the command that was send
	 * @param label, label of the command
	 * @param args, arguments that are written behind the command (like warp name)
	 *
	 */

	//TODO: add return and refactor the command
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		ConfigHelper ch = new ConfigHelper(jp);
		FileConfiguration langConfig = ch.getConfigFile("translation.yml");

		PlayerHelper pl = new PlayerHelper(jp);
		Player p = pl.getPlayerInstance(sender);

		if (args.length == 1) {

			Player targetPlayer = jp.getServer().getPlayer(args[0]);

			//if we dont check this the plugin get a error
			if(targetPlayer == null){
				p.sendMessage(langConfig.getString("Core.command.tele.dontFindPlayer"));
				return true;
			}
			else {
				if (targetPlayer.isOnline()) {
					Location targetLocation = targetPlayer.getLocation();
					p.sendMessage(langConfig.getString("Core.command.tele.teleportToTarget"));
					p.teleport(targetLocation);

				} else {
					p.sendMessage(args[0] + langConfig.getString("Core.command.tele.playerOff"));
				}
				return true;
			}
		}
		else if (args.length == 2) {

			Player p1 = jp.getServer().getPlayer(args[0]);
			Player p2 = jp.getServer().getPlayer(args[1]);

			//if we dont check this the plugin get a error
			if (p1 == null | p2 == null) {
				p.sendMessage(langConfig.getString("Core.command.tele.dontFindPlayer"));
				return true;

			} else {
				if (p1.isOnline() && p2.isOnline()) {

					p1.teleport(p2.getLocation());
					p1.sendMessage(langConfig.getString("Core.command.tele.teleportToPlayer ") + p2.getDisplayName());
					return true;

				} else {
					p.sendMessage(args[0] + langConfig.getString("Core.command.tele.playerOff"));
				}
			}
		}
		return false;
	}
}
