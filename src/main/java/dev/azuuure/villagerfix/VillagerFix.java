package dev.azuuure.villagerfix;

import dev.azuuure.villagerfix.listeners.GriefPreventionListener;
import dev.azuuure.villagerfix.listeners.WorldGuardListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class VillagerFix extends JavaPlugin {

    private Logger logger;
    private PluginManager pluginManager;
    private int hooks;

    @Override
    public void onLoad() {
        this.hooks = 0;
        this.logger = getLogger();
        this.pluginManager = getServer().getPluginManager();
    }

    @Override
    public void onEnable() {
        if (!pluginManager.isPluginEnabled("VillagerInABukkit")) {
            logger.severe("Failed to find VillagerInABukkit. Disabling VillagerFix.");
            pluginManager.disablePlugin(this);
            return;
        }

        if (pluginManager.isPluginEnabled("GriefPrevention")) {
            hooks++;
            pluginManager.registerEvents(new GriefPreventionListener(logger), this);
            logger.info("Registered GriefPrevention event listeners");
        }

        if (pluginManager.isPluginEnabled("WorldGuard")) {
            hooks++;
            pluginManager.registerEvents(new WorldGuardListener(logger), this);
            logger.info("Registered WorldGuard event listeners");
        }

        if (hooks == 0) {
            logger.severe("No compatible protection plugins were found. Disabling VillagerFix.");
            pluginManager.disablePlugin(this);
            return;
        }

        logger.info("Hooked into " + hooks + " plugin(s).");
        logger.info("VillagerFix version " + getDescription().getVersion() + " has been enabled.");
        logger.info("Made by https://github.com/azurejelly");
    }

    @Override
    public void onDisable() {
        logger.info("VillagerFix version " + getDescription().getVersion() + " is now shutting down.");
        logger.info("Made by https://github.com/azurejelly");
    }
}
