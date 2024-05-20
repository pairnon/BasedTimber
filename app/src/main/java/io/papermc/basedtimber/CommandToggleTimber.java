package io.papermc.basedtimber;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

@SuppressWarnings("deprecation")
public class CommandToggleTimber implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) { return false; }

        Player player = (Player) sender;

        String displayname = player.getDisplayName();

        for (String s : Main.timberEnabledPlayers) {
            if (s.equals(displayname)) {
                Main.timberEnabledPlayers.remove(s);
                player.sendMessage(Component.text("Disabled timber mode.", NamedTextColor.GOLD));
                return true;
            }
        }
        
        Main.timberEnabledPlayers.add(displayname);
        player.sendMessage(Component.text("Enabled timber mode.", NamedTextColor.GOLD));

        return true;
    }
}
