package dev.azuuure.villagerfix.listeners;

import com.imjustdoom.villagerinabucket.event.PreVillagerPickupEvent;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.logging.Logger;

public class GriefPreventionPreVillagerPickupListener implements Listener {

    private final Logger logger;

    public GriefPreventionPreVillagerPickupListener(Logger logger) {
        this.logger = logger;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPreVillagerPickupEvent(PreVillagerPickupEvent event) {
        Player player = event.getPlayer();
        GriefPrevention plugin = (GriefPrevention) Bukkit.getPluginManager().getPlugin("GriefPrevention");

        if (plugin == null) {
            logger.warning("Failed to obtain a GriefPrevention instance." +
                    "Villagers might be picked up inside protected zones.");
        } else if (plugin.allowBuild(player, player.getLocation()) != null) {
            event.setCancelled(true);
            logger.info("Player " + player.getName() + " attempted to pick up a villager" +
                    "inside a GriefPrevention protected zone");
        }
    }
}
