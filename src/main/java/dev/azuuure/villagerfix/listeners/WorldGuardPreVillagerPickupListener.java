package dev.azuuure.villagerfix.listeners;

import com.imjustdoom.villagerinabucket.event.PreVillagerPickupEvent;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.logging.Logger;

public class WorldGuardPreVillagerPickupListener implements Listener {

    private final Logger logger;

    public WorldGuardPreVillagerPickupListener(Logger logger) {
        this.logger = logger;
    }

    @EventHandler(ignoreCancelled = true)
    public void onWorldGuardPreVillagerPickupEvent(PreVillagerPickupEvent event) {
        LocalPlayer player = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
        Location location = BukkitAdapter.adapt(event.getPlayer().getLocation());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();

        if (!query.testState(location, player, Flags.BLOCK_PLACE)) {
            event.setCancelled(true);
            logger.info("Player " + player.getName() + " attempted to pick up a villager" +
                    "inside a WorldGuard protected zone");
        }
    }
}
