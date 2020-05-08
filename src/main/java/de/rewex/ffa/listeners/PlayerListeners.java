package de.rewex.ffa.listeners;

import de.rewex.ffa.Main;
import de.rewex.ffa.commands.BuildCmd;
import de.rewex.ffa.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {

            Location ploc = e.getEntity().getLocation();
            Location spawn = Main.getInstance().mapswitcher.getSpawn();
            int dist = (int) ploc.distance(spawn);

            if(dist <= Main.getInstance().mapswitcher.getProtection()) {
                e.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        Location ploc = e.getPlayer().getLocation();
        int dist = (int) Main.getInstance().mapswitcher.getSpawn().distance(ploc);

        if(dist <= Main.getInstance().mapswitcher.getProtection()) {
            e.setCancelled(true);
        }

    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(!BuildCmd.build.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            p.setFoodLevel(20);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerAchievementAwardedEvent(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

}
