package wtf.spacedogs.core.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ConfigHelper
 *
 * Provides helper methods to create, load, read and manage config files
 *
 * @author Lucas Carmohn
 * @version 1.0
 * @since 2020-06-12
 */

public class ConfigHelper {

	/**
	 * instance of the javaplugin to register on events
	 */
	private final JavaPlugin jp;

	/**
	 * mapping jp to plugin to get access to server information
	 */
	public ConfigHelper(JavaPlugin plugin) {

		jp = plugin;
	}

	/**
	 * get configfile object from plugins folder with the given name
	 * 
	 * @param configname, name of the config that you want to load
	 * @return config_object that contains the configfile if found one
	 * @return null if config with the name not exists
	 */
	public FileConfiguration getConfigFile(String configname) {

		File configFile = new File(jp.getDataFolder(), configname);

		if (configFile.exists()) {
			return YamlConfiguration.loadConfiguration(configFile);
		} else {
			jp.getLogger().info("no " + configname + " file was found inside the pluginfolder.");
			return null;
		}
	}

	/**
	 * get txt-config object from plugins folder with the given name
	 *
	 * @param filename, name of the config that you want to load
	 * @return textfile if found one
	 * @return null if textfile with the name not exists
	 */
	public File getTextFile(String filename) {

		File txtFile = new File(jp.getDataFolder(), filename);

		if (txtFile.exists()) {
			return txtFile;
		} else {
			jp.getLogger().info("no " + filename + " file was found inside the pluginfolder.");
			return null;
		}

	}

	/**
	 * load/reload configfile from pluginfolder if one exists
	 * if no file exists, method create a new file
	 *
	 * @param configname, name of the config that you want to load
	 */
	public void loadConfigFile(String configname) {

		File configFile = new File(jp.getDataFolder(), configname);
		YamlConfiguration.loadConfiguration(configFile);

		if (!configFile.exists()) {
			jp.getLogger().info("No " + configname + " was found. Creating a new from default");

			try {
				createNewConfigFile(configFile, configname);
			} catch (UnsupportedEncodingException e) {
				jp.getLogger().info("No default " + configname + " was found in resources");
			}
		}
	}

	/**
	 * load/reload textfile from pluginfolder if one exists
	 * if no file exists, method create a new file
	 *
	 * @param filename, name of the textfile that you want to load
	 */
	public void loadTxtFile(String filename) {

		File txtFile = new File(jp.getDataFolder(), filename);

		if (!txtFile.exists()) {
			jp.getLogger().info("No " + filename + " was found. Creating default...");
			createNewTxtFile(txtFile);
		}
	}

	/**
	 * Create the default data folder inside the plugin folder if no folder exists
	 */
	public void createDataFolder() {
		File dataFolder;
		try {
			dataFolder = new File(jp.getDataFolder() + File.separator + "data");
			if (!dataFolder.exists()) {
				dataFolder.mkdirs();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * create a new config file based on the default in resourcefolder by the given configname
	 *
	 * @param configname, name of the config you want to create
	 * @param configfile file-object for saving the created config
	 */
	private void createNewConfigFile(File configfile, String configname) throws UnsupportedEncodingException {

		Reader defConfigStream = new InputStreamReader(this.getClass().getResourceAsStream("/" + configname));

		YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		try {
			defConfig.save(configfile);
		} catch (IOException e) {
			jp.getLogger().info("Error, not able to save " + configname);
		}
	}

	/**
	 * create a new textfile file based on the default in resourcefolder
	 *
	 * @param configfile file-object for saving the created textfile
	 */
	private void createNewTxtFile(File txtFile) {

		if (!txtFile.exists()) {
			txtFile.getParentFile().mkdirs();
			try {
				txtFile.createNewFile();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
