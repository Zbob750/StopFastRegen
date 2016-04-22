package com.destroystokyo.stopfastregen;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class StopFastRegen extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        if (!this.getServer().getVersion().contains("Paper")) {
            this.getLogger().severe("This plugin requires the Paper server implementation");
            this.getLogger().severe("http://paper.emc.gs");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityRegainHealth(final EntityRegainHealthEvent event) {
        if (event.isFastRegen() && event.getEntity() instanceof Player && !event.getEntity().hasPermission("stopfastregen.bypass")) {
            event.setCancelled(true);
        }
    }
}