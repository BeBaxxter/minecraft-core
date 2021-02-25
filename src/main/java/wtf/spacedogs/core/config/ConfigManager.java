package wtf.spacedogs.core.config;

import wtf.spacedogs.core.Core;

/**
 * ConfigManager
 *
 * managed all configfiles inside the plugin.
 * example: create the default datafolder where the configs are inside
 * example: reload all configfiles
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-12
 */

public class ConfigManager {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final Core jp;

	/**
	 * mapping jp to plugin to get access to server information
	 */
	public ConfigManager(Core plugin) {

		jp = plugin;
	}

	/**
	 * creates a datafolder inside the pluginfolder where all configfiles inside
	 * creates a instance of the confighelper that load all files when the plugin is starting
	 * (executed in the main file)
	 */
	public void initiateConfigs() {

		ConfigHelper ch = new ConfigHelper(jp);
		ch.createDataFolder();
		ch.loadConfigFile("config.yml");
		ch.loadConfigFile("translation.yml");

		//Black_list_functions for Milestone 2
		//ch.loadTxtFile("blacklist.txt");

	}
}
