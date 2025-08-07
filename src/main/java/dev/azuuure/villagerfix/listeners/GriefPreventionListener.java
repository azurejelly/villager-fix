package dev.azuuure.villagerfix.listeners;

import com.imjustdoom.villagerinabucket.event.PreVillagerPickupEvent;
import com.imjustdoom.villagerinabucket.event.PreVillagerPlaceEvent;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GriefPreventionListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPreVillagerPickupEvent(PreVillagerPickupEvent event) {
        GriefPrevention plugin = (GriefPrevention) Bukkit.getPluginManager().getPlugin("GriefPrevention");
        if (plugin == null) {
            // this is weird!
            return;
        }

        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Location villager = event.getLocation();

        if (plugin.allowBuild(player, loc) != null || plugin.allowBuild(player, villager) != null) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPreVillagerPlaceEvent(PreVillagerPlaceEvent event) {
        GriefPrevention plugin = (GriefPrevention) Bukkit.getPluginManager().getPlugin("GriefPrevention");
        if (plugin == null) {
            // this is weird!
            return;
        }

        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Location villager = event.getLocation();

        if (plugin.allowBuild(player, loc) != null || plugin.allowBuild(player, villager) != null) {
            event.setCancelled(true);
        }
    }
}
