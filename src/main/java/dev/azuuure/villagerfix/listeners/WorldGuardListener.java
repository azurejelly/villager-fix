package dev.azuuure.villagerfix.listeners;

import com.imjustdoom.villagerinabucket.event.PreVillagerPickupEvent;
import com.imjustdoom.villagerinabucket.event.PreVillagerPlaceEvent;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WorldGuardListener implements Listener {

    private final RegionContainer container;

    public WorldGuardListener() {
        this.container = WorldGuard.getInstance().getPlatform().getRegionContainer();
    }

    @EventHandler(ignoreCancelled = true)
    public void onPreVillagerPickupEvent(PreVillagerPickupEvent event) {
        LocalPlayer player = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
        testAgainst(event, player,
                BukkitAdapter.adapt(event.getPlayer().getLocation()),
                BukkitAdapter.adapt(event.getLocation())
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onPreVillagerPlaceEvent(PreVillagerPlaceEvent event) {
        LocalPlayer player = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
        testAgainst(event, player,
                BukkitAdapter.adapt(event.getPlayer().getLocation()),
                BukkitAdapter.adapt(event.getLocation())
        );
    }

    private void testAgainst(Cancellable event, LocalPlayer player, Location... locations) {
        RegionQuery query = container.createQuery();
        for (Location loc : locations) {
            StateFlag.State state = query.queryState(loc, player, Flags.BLOCK_PLACE);

            if (state != StateFlag.State.DENY) {
                continue;
            }

            event.setCancelled(true);
            break;
        }
    }
}
