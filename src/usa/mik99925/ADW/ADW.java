package usa.mik99925.ADW;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.entity.Entity;
import java.util.HashMap;
import java.util.ArrayList;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ADW extends JavaPlugin {
	public static Logger log = Logger.getLogger("Minecraft");
	private final ADWEntityListener entityListener = new ADWEntityListener(this);
	public final HashMap<World, ArrayList<Entity>> ADWWorlds = new HashMap<World, ArrayList<Entity>>();

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener,
				Event.Priority.Normal, this);
		log.info("Anti-Dumb-Wolf Loaded.");
		addDefaults(getServer());
	}

	public void onDisable() {
		log.info("Anti-Dumb-Wolf Unloaded.");
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (label.equalsIgnoreCase("ADW")) {
			toggleADW((Player) sender);
			return true;
		}
		return false;
	}

	public void addDefaults(Server instance) {
		for (int i = 0; i < instance.getWorlds().size(); i++) {
			if(!ADWWorlds.containsKey(instance.getWorlds().get(i))){
				ADWWorlds.put(instance.getWorlds().get(i), null);
			}
		}
		return;
	}

	public void toggleADW(Player player) {
		if (player.isOp()) {
			if (enabled(player.getWorld())) {
				this.ADWWorlds.remove(player.getWorld());
				player.sendMessage("Anti-Dumb-Wolf disabled on "
						+ player.getWorld().getName());
			} else {
				this.ADWWorlds.put(player.getWorld(), null);
				player.sendMessage("Anti-Dumb-Wolf enabled on "
						+ player.getWorld().getName());
			}
		} else
			player.sendMessage("You don't have permission to do this.");
	}

	public boolean enabled(World world) {
		return this.ADWWorlds.containsKey(world);
	}
}