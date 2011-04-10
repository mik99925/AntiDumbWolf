package usa.mik99925.ADW;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ADWEntityListener extends EntityListener {
	public static ADW plugin;

	public ADWEntityListener(ADW instance) {
		plugin = instance;
	}

	public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
		Entity defender = event.getEntity();
		DamageCause type = event.getCause();
		if (plugin.enabled(defender.getWorld())) {
			if (defender instanceof Wolf && type != DamageCause.VOID) {
				event.setCancelled(true);
				return;
			}
		}
	}
	
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		return;
	}
	
	public void onEntityDamageByProjectile(EntityDamageByProjectileEvent event) {
		return;
	}

	public void onEntityDamage(EntityDamageEvent event) {
		if (event.isCancelled()) {
			return;
		}

		if (event instanceof EntityDamageByBlockEvent) {
			this.onEntityDamageByBlock((EntityDamageByBlockEvent) event);
			return;
		}
		else if (event instanceof EntityDamageByEntityEvent) {
			this.onEntityDamageByEntity((EntityDamageByEntityEvent) event);
			return;
		}
		else if (event instanceof EntityDamageByProjectileEvent) {
			this.onEntityDamageByProjectile((EntityDamageByProjectileEvent) event);
			return;
		}
		Entity defender = event.getEntity();

		if (defender instanceof Wolf && plugin.enabled(defender.getWorld())) {
			event.setCancelled(true);
			return;
		}
	}
}
