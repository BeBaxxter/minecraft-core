package wtf.spacedogs.core.commands.basic;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import wtf.spacedogs.core.commands.PlayerHelper;
import wtf.spacedogs.core.config.ConfigHelper;

/**
 * CommandWuff
 *
 * command, give the player a cookie
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-18
 */

public class CommandWuff implements CommandExecutor {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final JavaPlugin jp;

	/**
	 * mapping jp to plugin to get access to server information
	 */
	public CommandWuff(JavaPlugin plugin) {
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
			if (p.hasPermission("core.player.wuff")) {
				ItemStack itemStack = new ItemStack(Material.COOKIE, 1);
				p.getInventory().addItem(itemStack);

				p.sendMessage(langConfig.getString("Core.command.wuff"));
				return true;
			} else {
				p.sendMessage(langConfig.getString("Core.command.noRights"));
				return false;
			}
		}
		return false;
	}
}
