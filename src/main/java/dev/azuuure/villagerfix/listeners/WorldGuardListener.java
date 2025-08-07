package dev.azuuure.villagerfix.listeners;

import com.imjustdoom.villagerinabucket.event.PreVillagerPickupEvent;
import com.imjustdoom.villagerinabucket.event.PreVillagerPlaceEvent;
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

public class WorldGuardListener implements Listener {

    private final Logger logger;
    private final RegionContainer container;

    public WorldGuardListener(Logger logger) {
        this.logger = logger;
        this.container = WorldGuard.getInstance().getPlatform().getRegionContainer();
    }

    @EventHandler(ignoreCancelled = true)
    public void onWorldGuardPreVillagerPickupEvent(PreVillagerPickupEvent event) {
        LocalPlayer player = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
        RegionQuery query = container.createQuery();

        Location loc = BukkitAdapter.adapt(event.getPlayer().getLocation());
        Location villager = BukkitAdapter.adapt(event.getLocation());

        if (!query.testState(loc, player, Flags.BLOCK_PLACE) || !query.testState(villager, player, Flags.BLOCK_PLACE)) {
            event.setCancelled(true);
            logger.info("Player " + player.getName() + " attempted to pick up a villager" +
                    "inside a WorldGuard protected zone");
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPreVillagerPlaceEvent(PreVillagerPlaceEvent event) {
        LocalPlayer player = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
        RegionQuery query = container.createQuery();

        Location loc = BukkitAdapter.adapt(event.getPlayer().getLocation());
        Location villager = BukkitAdapter.adapt(event.getLocation());

        if (!query.testState(loc, player, Flags.BLOCK_PLACE) || !query.testState(villager, player, Flags.BLOCK_PLACE)) {
            event.setCancelled(true);
            logger.info("Player " + player.getName() + " attempted to place a villager" +
                    "inside a WorldGuard protected zone");
        }
    }
}
