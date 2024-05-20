package io.papermc.basedtimber;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {

    private static Material[] logMaterials = {Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.MANGROVE_LOG, Material.CHERRY_LOG};
    private static int maxTreeHeight = 31;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block brokenBlock = event.getBlock();
        for (Material log : logMaterials) {
            if (brokenBlock.getType().equals(log)) {
                event.getPlayer().sendMessage("you just broke a log!");
                for (int i = 1; i < maxTreeHeight; i++) {
                    Block aboveBlock = brokenBlock.getRelative(BlockFace.UP, i);
                    if (!aboveBlock.getType().equals(brokenBlock.getType())) { return; }
                    ItemStack droppedItem = new ItemStack(aboveBlock.getType());
                    aboveBlock.getWorld().dropItemNaturally(aboveBlock.getLocation(), droppedItem);
                    aboveBlock.setType(Material.AIR);
                }
            }
        }
    }

}
