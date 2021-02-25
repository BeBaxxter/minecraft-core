package wtf.spacedogs.core.commands.basic;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import wtf.spacedogs.core.commands.PlayerHelper;
import wtf.spacedogs.core.config.ConfigHelper;

/**
 * CommandFly
 *
 * command, enable/disable the flymode of the player that execute the command
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-18
 */

public class CommandFly implements CommandExecutor {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final JavaPlugin jp;

	/**
	 * mapping jp to plugin to get access to server information
	 */
	public CommandFly(JavaPlugin plugin) {
		jp = plugin;
	}

	/**
	 * set flymode to the current player (on/off)
	 *
	 * @param sender, sender of the command (server or player)
	 * @param command, the command that was send
	 * @param label, label of the command
	 * @param args, arguments that are written behind the command (like warp name)
	 *
	 * @return false if the player dont have rights to execute the command
	 * @return false if the command is executed wrong
	 * @return true if the command is executed correctly
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		ConfigHelper ch = new ConfigHelper(jp);
		FileConfiguration langConfig = ch.getConfigFile("translation.yml");

		PlayerHelper pl = new PlayerHelper(jp);
		Player p = pl.getPlayerInstance(sender);

		if (pl.isSenderPlayer(sender)) {
			if (p.hasPermission("core.player.fly")) {
				if (p.getAllowFlight()) {
					p.setAllowFlight(false);
					p.sendMessage(langConfig.getString("Core.command.fly.disable"));
				} else {
					p.setAllowFlight(true);
					p.sendMessage(langConfig.getString("Core.command.fly.enable"));
				}
				return true;

			} else {
				p.sendMessage(langConfig.getString("Core.command.noRights"));
				return false;
			}
		}
		return false;
	}
}
