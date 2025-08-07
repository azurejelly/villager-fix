package dev.azuuure.villagerfix;

import dev.azuuure.villagerfix.listeners.GriefPreventionPreVillagerPickupListener;
import dev.azuuure.villagerfix.listeners.WorldGuardPreVillagerPickupListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class VillagerFix extends JavaPlugin {

    private Logger logger;
    private PluginManager pluginManager;

    @Override
    public void onLoad() {
        this.logger = getLogger();
        this.pluginManager = getServer().getPluginManager();
    }

    @Override
    public void onEnable() {
        pluginManager.registerEvents(new GriefPreventionPreVillagerPickupListener(logger), this);
        pluginManager.registerEvents(new WorldGuardPreVillagerPickupListener(logger), this);

        logger.info("VillagerFix version " + getDescription().getVersion() + " has been enabled.");
        logger.info("Made by https://github.com/azurejelly");
    }

    @Override
    public void onDisable() {
        logger.info("VillagerFix version " + getDescription().getVersion() + " is now shutting down.");
        logger.info("Made by https://github.com/azurejelly");
    }
}
