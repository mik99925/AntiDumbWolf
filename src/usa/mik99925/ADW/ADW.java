package usa.mik99925.ADW;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

public class ADW extends JavaPlugin {
	private static final Logger log = Logger.getLogger("Minecraft");
	private final ADWEntityListener entityListener = new ADWEntityListener(this);

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener,
				Event.Priority.Normal, this);
		log.info("Anti-Dumb-Wolf Loaded.");
	}

	public void onDisable() {
		log.info("Anti-Dumb-Wolf Disabled.");
	}
}