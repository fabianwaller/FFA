package de.rewex.ffa.commands;

import de.rewex.ffa.Main;
import de.rewex.ffa.listeners.KillListeners;
import de.rewex.ffa.manager.LocationManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCmd implements CommandExecutor {

    private final Main plugin;

    public SpawnCmd(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.noplayer);
            return true;
        }
        Player p = (Player)sender;

        if(!KillListeners.fight.containsKey(p.getName())) {

            Location spawn = LocationManager.getLocation("spawn");

            Location ploc = p.getLocation();
            int dist = (int) spawn.distance(ploc);

            if(dist > 5) {
				/*for(Player all : Bukkit.getOnlinePlayers()) {
					if(ploc.distance(all.getLocation()) < 6) {
						p.sendMessage(Main.prefix + "§cDie Distanz zu deinen Gegnern ist zu gering");
						return true;
					}
				}*/

                LocationManager.telLocation(p, "spawn");
            } else {
                p.sendMessage(Main.prefix + "§cDu bist bereits am Spawn");
            }
        } else {
            p.sendMessage(Main.prefix + "§cDu befindest dich noch im Kampf");
        }
        return true;
    }

}
