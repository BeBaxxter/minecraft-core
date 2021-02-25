package wtf.spacedogs.core.commands;

import wtf.spacedogs.core.config.ConfigHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * PlayerHelper
 *
 * provides helper methods to create, load, read and manage the player object
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-12
 */

public class PlayerHelper {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final JavaPlugin jp;

	/**
	 * instance of the language-config that is used to get the translations
	 */
	private final FileConfiguration langConfig;


	/**
	 * mapping jp to plugin to get access to server information
	 */
	public PlayerHelper(JavaPlugin plugin) {

		jp = plugin;
		ConfigHelper ch = new ConfigHelper(jp);
		langConfig = ch.getConfigFile("translation.yml");
	}

	/**
	 * This method checks if the given sender is a player
	 * 
	 * @param sender of the written command (player or server)
	 * @return player object
	 */
	public Player getPlayerInstance(CommandSender sender) {

		Player player = null;
		if (sender instanceof Player) {

			player = (Player) sender;
		}

		return player;
	}

	/**
	 * check if the given sender is a player
	 *
	 * @param sender of the written command (player or server)
	 *
	 * @return false if sender is not a player
	 * @return true if sender is a player
	 */
	public boolean isSenderPlayer(CommandSender sender) {

		Player p = getPlayerInstance(sender);
		if (p == null) {
			sender.sendMessage(langConfig.getString("Core.command.noPlayer"));
			return false;
		}
		return true;
	}
}
