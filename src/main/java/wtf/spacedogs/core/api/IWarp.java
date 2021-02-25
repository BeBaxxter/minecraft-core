package wtf.spacedogs.core.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Sets, reads and removes objects of type org.bukkit.Location.Location
 * as HashMap to and from a file.
 * 
 * @author DeBukkIt (main development and functions)
 * @author Baxxter (Implement more functions + translations)
 * @version 1.0.1
 *
 */

public class IWarp {

	HashMap<String, String> map;

	String warpFileLocation;

	/**
	 * creates a folder names data inside the plugin folder to store the warp database inside
	 * @param warpFileName name of the file that stores the warps
	 * @param plugin the instance of the current plugin
	 */
	public IWarp(String warpFileName, JavaPlugin plugin) {
		this.warpFileLocation = plugin.getDataFolder() + File.separator + "data/" + warpFileName;
	}

	/**
	 * Returns the WarpPoint with the specified name. If there is no WarpPoint with
	 * that name, null will be returned
	 * 
	 * @param name of the warp-point
	 * @return WarpPoint, if available; else null
	 */
	public Location getWarp(String name) {
		// Load Map (Update)
		loadMap();

		// Reads the WarpPoint and returns its Location
		try {
			StringTokenizer toker = new StringTokenizer(map.get(name), ";");
			
			return new Location(Bukkit.getWorld(toker.nextToken()), Double.parseDouble(toker.nextToken()),
					Double.parseDouble(toker.nextToken()), Double.parseDouble(toker.nextToken()),
					Float.parseFloat(toker.nextToken()), Float.parseFloat(toker.nextToken()));

		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Removes the WarpPoint with the specified name.<br>
	 * If there is no WarpPoint with that name, nothing will happen.
	 * 
	 * @param name
	 * 	0 : Success
	 * 	1 : WarpPoint with that name already exists
	 * 	2 : Other error (cf. console error log)
	 */
	public int removeWarp(String name) {
		// Load Map (Update)
		loadMap();

		// Remove WarpPoint from Map

		if (map.containsKey(name)) {
			map.remove(name);
		} else {
			return 1;
		}

		// Save Map
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(warpFileLocation));
			oos.writeObject(map);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
		return 0;
	}

	/**
	 * Adds/Saves a new WarpPoint
	 * 
	 * @param name Name of the WarpPoint
	 * @param loc  Location of the WarpPoint
	 * @return Status-Integer<br>
	 *         0 : Success 1 : WarpPoint with that name already exists 2 : Other
	 *         error (cf. console error log)
	 */
	public int addWarp(String name, Location loc) {
		// Load Map (Update)
		loadMap();

		// Create new WarpPoint and write it to Map
		if (!map.containsKey(name)) {
			map.put(name, loc.getWorld().getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";"
					+ loc.getYaw() + ";" + loc.getPitch());
		} else {
			return 1;
		}

		// Save Map
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(warpFileLocation));
			oos.writeObject(map);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}

		return 0;
	}

	/**
	 * Send the player a list of all existing warps
	 * @param p  Current Player
	 *
	 */
	public void showAllWarps(Player p) {

		Object[] warps = getWarpList();

		for (Object o : warps) {
			p.sendRawMessage("- " + (String) o);

		}
	}
	
	/**
	 * Returns a list of all existing WarpPoints
	 * 
	 * @return Array of Objects (all of them Strings)
	 *
	 */
	private Object[] getWarpList() {
		// Load Map
		loadMap();
		return map.keySet().toArray();
	}


	/**
	 * load the current warpFile and creates a new one if no file exists
	 */
	private void loadMap() {
		// create file if not exists
		try {
			File file = new File(warpFileLocation);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("Created new File.");

				// create empty map
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(warpFileLocation));
				oos.writeObject(new HashMap<String, String>());
				oos.flush();
				oos.close();

				System.out.println("Wrote empty HashMap.");
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Load Map
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(warpFileLocation));
			this.map = (HashMap<String, String>) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
