package io.papermc.basedtimber;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import de.tr7zw.changeme.nbtapi.NBTBlock;
import de.tr7zw.changeme.nbtapi.NBTCompound;

@SuppressWarnings("deprecation")
public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        if (!hasTimberEnabled(player)) { return; }

        Block brokenBlock = event.getBlock();
        for (Material log : Main.LOG_MATERIALS) {
            if (brokenBlock.getType().equals(log)) {
                for (int i = 1; i < Main.MAX_TREE_HEIGHT; i++) {
                    Block aboveBlock = brokenBlock.getRelative(BlockFace.UP, i);
                    if (!canTimber(aboveBlock)) { return; }
                    if (!aboveBlock.getType().equals(brokenBlock.getType())) { return; }
                    
                    ItemStack heldItem = player.getInventory().getItemInMainHand();
                    if (heldItem == null || !isAxe(heldItem))  { return; }

                    if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                        if (!updateToolDamage(heldItem)) { return; }
                    }

                    dropItem(aboveBlock);
                    aboveBlock.setType(Material.AIR);
                }
            }
        }
    }

    private boolean updateToolDamage(ItemStack itemStack) {
        Damageable d = (Damageable) itemStack.getItemMeta();
        int currentDamage = d.getDamage();
        int maxDamage = itemStack.getType().getMaxDurability();
        d.setDamage(currentDamage + 1);
        if (d.getDamage() >= maxDamage) {
            itemStack.setType(Material.AIR);
            return false;
        }
        itemStack.setItemMeta(d);
        return true;
    }

    private boolean isAxe(ItemStack itemStack) {
        for (Material m : Main.AXE_MATERIALS) {
            if (itemStack.getType().equals(m)) { return true; }
        }
        return false;
    }

    private boolean canTimber(Block block) {

        NBTBlock nbtBlock = new NBTBlock(block);

        NBTCompound c = nbtBlock.getData();

        boolean ignore = c.getBoolean(Main.IGNORE_KEY);
        
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
