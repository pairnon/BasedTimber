package io.papermc.basedtimber;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Material[] logMaterials = {Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.MANGROVE_LOG, Material.CHERRY_LOG};
    public static ArrayList<String> timberEnabledPlayers;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

        this.getCommand("toggletimber").setExecutor(new CommandToggleTimber());

        timberEnabledPlayers = new ArrayList<String>();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    }

}