package io.papermc.basedtimber;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static final String IGNORE_KEY = "ignoreLog";
    public static final int MAX_TREE_HEIGHT = 31;
    public static final Material[] LOG_MATERIALS = {Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.MANGROVE_LOG, Material.CHERRY_LOG};
    public static final Material[] AXE_MATERIALS = {Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE};
    
    public static ArrayList<String> timberEnabledPlayers;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);

        this.getCommand("toggletimber").setExecutor(new CommandToggleTimber());

        timberEnabledPlayers = new ArrayList<String>();
    }
}