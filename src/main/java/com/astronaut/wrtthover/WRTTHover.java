package com.astronaut.wrtthover;

import com.astronaut.wrtthover.commands.HoverCommand;
import com.astronaut.wrtthover.listeners.HoverListener;
import com.astronaut.wrtthover.util.Language;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class WRTTHover extends JavaPlugin {

    CommandSender console = getServer().getConsoleSender();
    public String prefix = ChatColor.translateAlternateColorCodes('&', "&f[&cWRTTHover&f]&r");

    HoverCommand hoverCommand;
    HoverListener hoverListener;

    Language language;

    public void onEnable() {
        console.sendMessage(prefix + " WRTTHover Initialised on Version " + getDescription().getVersion());

        this.hoverCommand = new HoverCommand(this);
        this.hoverListener = new HoverListener(this);
        this.language = new Language(this);

        Language.getConfig().addDefault("plugin.prefix", "&f[&cWRTTHover&f]&r");
        Language.getConfig().addDefault("hover.enable", "&fHover &aEnabled&f.");
        Language.getConfig().addDefault("hover.disable", "&fHover &cDisabled&f.");
        Language.getConfig().addDefault("hover.noperms", "&cYou don't have permission to use hover!");
        Language.getConfig().addDefault("hover.limit", "&aFlight Height:");
        Language.getConfig().options().copyDefaults(true);
        Language.saveConfig();

        saveDefaultConfig();

        prefix = ChatColor.translateAlternateColorCodes('&', Language.getConfig().getString("plugin.prefix"));
    }

}
