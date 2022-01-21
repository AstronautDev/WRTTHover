package com.astronaut.wrtthover.listeners;

import com.astronaut.wrtthover.WRTTHover;
import com.astronaut.wrtthover.util.HoverUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HoverListener implements Listener {

    WRTTHover plugin;
    public HoverListener(WRTTHover plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.hasPermission("wrtthover.faehover")) {
            if (HoverUtil.isHovering(p)) {
                if (HoverUtil.getDistanceFromGround(p) > plugin.getConfig().getInt("hoverLimit")) {
                    Location blockBelow = new Location(p.getWorld(), p.getLocation().getBlockX(), p.getLocation().getBlockY() - 1, p.getLocation().getBlockZ());
                    blockBelow.setPitch(p.getLocation().getPitch());
                    blockBelow.setYaw(p.getLocation().getYaw());
                    e.setTo(blockBelow);
                }
            } else {
                p.setFlying(false);
            }
        } else {
            e.setCancelled(false);
        }
    }



}
