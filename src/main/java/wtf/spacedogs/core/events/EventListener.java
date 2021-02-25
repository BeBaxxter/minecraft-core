package wtf.spacedogs.core.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import wtf.spacedogs.core.Core;
import wtf.spacedogs.core.config.ConfigHelper;

/**
 * EventListener
 *
 * Listener that reacts on several server events.
 * For example: listener hears if a player joins the server and send him a welcome message.
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-14
 */
public class EventListener implements Listener {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final Core jp;

	/**
	 * instance of the language-config that is used to get the translations
	 */
	private final FileConfiguration langConfig;

	/**
	 * mapping jp to plugin and mapping the serverEvents on him
	 * create a instance of confighelper that read the translationfile and write the result in langconfig
	 * @param plugin used to get relevant information from server, like the current player
	 */
	public EventListener(Core plugin) {
		jp = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		ConfigHelper ch = new ConfigHelper(jp);
		langConfig = ch.getConfigFile("translation.yml");
	}

	/**
	 * eventhandler that execute a action if the player joins the server
	 * @param event , inform the plugin if a player join the server
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.sendMessage(langConfig.getString("Core.events.playerJoin"));
	}

}
