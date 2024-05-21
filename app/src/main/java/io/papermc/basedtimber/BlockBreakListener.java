package io.papermc.basedtimber;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.changeme.nbtapi.NBTBlock;
import de.tr7zw.changeme.nbtapi.NBTCompound;

@SuppressWarnings("deprecation")
public class BlockBreakListener implements Listener {

    private static int maxTreeHeight = 31;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        if (!hasTimberEnabled(player)) { return; }

        Block brokenBlock = event.getBlock();
        for (Material log : Main.logMaterials) {
            if (brokenBlock.getType().equals(log)) {
                for (int i = 1; i < maxTreeHeight; i++) {
                    Block aboveBlock = brokenBlock.getRelative(BlockFace.UP, i);
                    if (!canTimber(aboveBlock)) { return; }
                    if (!aboveBlock.getType().equals(brokenBlock.getType())) { return; }
                    dropItem(aboveBlock);
                    aboveBlock.setType(Material.AIR);
                }
            }
        }
    }

    private boolean canTimber(Block block) {

        NBTBlock nbtBlock = new NBTBlock(block);

        NBTCompound c = nbtBlock.getData();

        boolean ignore = c.getBoolean("ignoreLog");
        
        return !ignore;
    }

    private boolean hasTimberEnabled(Player player) {
        String displayname = player.getDisplayName();
        for (String s : Main.timberEnabledPlayers) {
            if (s.equals(displayname)) {
                return true;
            }
        }
        return false;
    }

    private void dropItem(Block aboveBlock) {
        ItemStack droppedItem = new ItemStack(aboveBlock.getType());
        aboveBlock.getWorld().dropItemNaturally(aboveBlock.getLocation(), droppedItem);
    }

}
