package io.papermc.basedtimber;

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

        NBTBlock nbtBlock = new NBTBlock(block);

        NBTCompound c = nbtBlock.getData();

        boolean ignore = true;

        c.setBoolean("ignoreLog", ignore);

    }
    
}
