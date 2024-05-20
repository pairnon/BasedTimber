package io.papermc.basedtimber;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class CommandRegisterLog implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;
        ItemStack itemStackInHand = player.getInventory().getItemInMainHand();
        for ( Material m : BlockBreakListener.logMaterials ) {
            if (itemStackInHand.getType().equals(m)) {
                player.sendMessage(Component.text("Registered logs", NamedTextColor.GREEN));
            }
        }
        
        return true;
    }

}
