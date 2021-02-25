package wtf.spacedogs.core;

import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.spacedogs.core.api.CustomeException;
import wtf.spacedogs.core.commands.CommandHandler;
import wtf.spacedogs.core.config.ConfigManager;
import wtf.spacedogs.core.events.EventListener;

/**
 * Core Class
 *
 * Main file of the plugin. First call after starting the plugin.
 * Handling the complete setup and lifecycle.
 * 
 * @author Baxxter
 * @version 0.9
 */
public class Core extends JavaPlugin {

	/**
	 * instance of the commandHandler that is used to initiate the commands
	 */
	private final CommandHandler commandHandler = new CommandHandler(this);

	/**
	 * instance of the commandHandler that is used to initiate the config
	 */
	private final ConfigManager configManager = new ConfigManager(this);

	/**
	 * Plugin Prefix
	 */
	public String pluginName = "[Core]";

	/**
	 * main method after plugin start, setup the plugin and create the lifecycle
	 *
	 */
	public void onEnable() {
		try {
			setupPlugin();
			createLifecycle();

		} catch (CustomeException e) {
			getLogger().log(Level.SEVERE, pluginName + " Error with enabling the plugin (onEnable), please check the logs:");
			e.printStackTrace();
		}

	}

	/**
	 *
	 * main method after shutting down the plugin, will close all active services
	 *
	 */
	public void onDisable() {
	}

	/**
	 * setting up the plugin:
	 * configManager --> load all configs and create the missing ones<br>
	 * commandHandler --> initiate all available commands
	 */
	private void setupPlugin() {
		try {
			configManager.initiateConfigs();
			commandHandler.initiateCommands();
		} catch (CustomeException e) {
			getLogger().log(Level.SEVERE, pluginName + " Error with setting up the plugin (setupPlugin), please check the logs:");
			e.printStackTrace();
		}
	}

	/**
	 * create the Lifecycle of the plugin,
	 * that means all services that are running until the plugin is shutting down
	 */
	private void createLifecycle() {
		try {

			new EventListener(this);

		} catch (CustomeException e) {
			getLogger().log(Level.SEVERE, pluginName + " Error while creating the Lifecycle (createLifecycle), please check the logs:");
			e.printStackTrace();
		}
	}
}
