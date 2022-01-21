package com.astronaut.wrtthover.commands;

import com.astronaut.wrtthover.WRTTHover;
import com.astronaut.wrtthover.util.HoverUtil;
import com.astronaut.wrtthover.util.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HoverCommand implements CommandExecutor {

    WRTTHover plugin;
    public HoverCommand(WRTTHover plugin) {
        this.plugin = plugin;

        plugin.getCommand("Hover").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(s.equalsIgnoreCase("Hover")) {
                if (p.hasPermission("wrtthover.faehover")) {
                    HoverUtil.toggleHover(p);
                    if (HoverUtil.isHovering(p)) {
                        p.sendMessage(plugin.prefix + " " + Language.translateNode("hover.enable"));
                        p.sendMessage(plugin.prefix + " " + Language.translateNode("hover.limit") + " " + plugin.getConfig().getInt("hoverLimit") + " Blocks");
                    } else {
                        p.sendMessage(plugin.prefix + " " + Language.translateNode("hover.disable"));
                    }
                } else {
                    p.sendMessage(plugin.prefix + " " + Language.translateNode("hover.noperms"));
                }
            }
        }

        return false;
    }
}
