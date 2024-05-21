package io.papermc.basedtimber;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.tr7zw.changeme.nbtapi.NBTBlock;
import de.tr7zw.changeme.nbtapi.NBTCompound;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (!isLog(block)) { return; }
        NBTBlock nbtBlock = new NBTBlock(block);
        NBTCompound c = nbtBlock.getData();
        c.setBoolean("ignoreLog", true);
    }

    private boolean isLog(Block block) {
        for (Material log : Main.logMaterials) {
            if (log.equals(block.getType())) { return true; }
        }
        return false;
    }   
}