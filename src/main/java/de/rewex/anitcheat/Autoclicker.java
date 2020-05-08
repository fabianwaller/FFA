package de.rewex.anitcheat;

import de.rewex.ffa.Main;
import de.rewex.ffa.manager.RangManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;

public class Autoclicker implements Listener {

    public HashMap<String, Integer> cps = new HashMap<>();
    public BukkitRunnable seconds;

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if(!cps.containsKey(e.getPlayer().getName())) {
            cps.put(e.getPlayer().getName(), 0);
        } else {
            int hits = cps.get(e.getPlayer().getName());
            cps.put(e.getPlayer().getName(), hits+1);
        }

        if(seconds == null) {
            seconds = new BukkitRunnable() {
                @Override
                public void run() {
                    for(String player : cps.keySet()) {
                        Player p = Bukkit.getPlayer(player);

                        if(cps.get(player) > 18) {
                            Bukkit.getOnlinePlayers().forEach(team -> {
                               if(team.hasPermission("server.admin")) {
                                   p.sendMessage(Anitcheat.prefix + RangManager.getName(p) + " §7Click Detection: §c" + cps.get(p.getName()) + "§7cps");
                               }
                            });
                            cps.put(p.getName(), 0);
                        } else {
                            cps.put(p.getName(), 0);
                        }
                    }
                }
            };

            seconds.runTaskTimer(Main.getInstance(),0,20L);
        }
    }

}
