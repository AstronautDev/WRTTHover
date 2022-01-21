package com.astronaut.wrtthover.util;

import com.astronaut.wrtthover.WRTTHover;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class Language {

    private static File langFile;
    private static FileConfiguration langConfig;

    private static WRTTHover plugin;
    public Language(WRTTHover plugin) {
        this.plugin = plugin;

        createConfig();
    }

    private static void createConfig() {
        langFile = new File(plugin.getDataFolder(), "language.yml");
        if(!langFile.exists()) {
            try {
                langFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        langConfig = new YamlConfiguration();
        try {
            langConfig.load(langFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        if(!langFile.exists()) {
            createConfig();
        } else {
            try {
                langConfig.save(langFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static FileConfiguration getConfig() {
        return langConfig;
    }

    public static String translateNode(String configNode) {
        String fullNode = getConfig().getString(configNode);
        return ChatColor.translateAlternateColorCodes('&', fullNode);
    }

}
