package com.astronaut.wrtthover.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class HoverUtil {

    public static ArrayList<UUID> playersHovering = new ArrayList<UUID>();

    public static void toggleHover(Player p) {
        if(!playersHovering.contains(p.getUniqueId())) {
            playersHovering.add(p.getUniqueId());
            p.setAllowFlight(true);
            p.setFlying(true);
        } else {
            playersHovering.remove(p.getUniqueId());
            p.setAllowFlight(false);
            p.setFlying(false);
        }
    }

    public static boolean isHovering(Player p) {
        return playersHovering.contains(p.getUniqueId());
    }

    public static int getDistanceFromGround(Player p) {
        Entity e = (Entity) p;
        Location loc = e.getLocation().clone();
        double y = loc.getBlockY();
        int distance = 0;
        for(double i = y; i >= 0; i--) {
            loc.setY(i);
            if(loc.getBlock().getType().isSolid() || loc.getBlock().getType().equals(Material.WATER)) break;
            distance++;
        }
        return distance;
    }

}
