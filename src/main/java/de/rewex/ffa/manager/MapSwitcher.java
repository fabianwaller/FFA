package de.rewex.ffa.manager;

import de.rewex.ffa.Main;
import de.rewex.ffa.manager.utils.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;

public class MapSwitcher {

    private Location spawn;
    private String map;
    private int protection;

    int seconds = 15 * TimeUnit.getUnit("min").getToSecond();
    int currentseconds = seconds;
    int mapcount = 2;
    int current = 1;

    int taskid;

    public MapSwitcher() {
        this.spawn = LocationManager.getLocation("spawn1");
        this.map = LocationManager.getMapname("spawn1");
        this.protection = LocationManager.getProtectionRadius("spawn1");
    }

    public void startCounter() {
        taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                switch(currentseconds) {

                    case 120: case 60: case 30: case 15:
                        Bukkit.broadcastMessage(Main.prefix + "Mapwechsel in §d" + currentseconds + " §7Sekunden");
                        break;
                    case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Main.prefix + "Mapwechsel in §d" + currentseconds + " §7Sekunden");
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.playSound(all.getLocation(), Sound.NOTE_BASS, 3.0F, 2.0F);
                        });
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Main.prefix + "Mapwechsel in §deiner §7Sekunde");
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.playSound(all.getLocation(), Sound.NOTE_BASS, 3.0F, 2.0F);
                        });
                        break;

                    case 0:
                        current++;
                        if(current > mapcount) {
                            current = 1;
                        }
                        spawn = LocationManager.getLocation("spawn" + current);
                        map = LocationManager.getMapname("spawn" + current);
                        protection = LocationManager.getProtectionRadius("spawn" + current);
                        Bukkit.createWorld(new WorldCreator(LocationManager.getMapname("spawn" + current)));

                        Bukkit.getOnlinePlayers().forEach(all -> {
                            LocationManager.telLocation(all, "spawn" + current);
                            ScoreAPI.setScoreboard(all);
                        });

                        Bukkit.getScheduler().cancelTask(taskid);
                        currentseconds = seconds;
                        startCounter();
                        break;

                    default:
                        break;

                }
                currentseconds--;

            }
        },20,20L);
    }

    public Location getSpawn() {
        return this.spawn;
    }

    public String getMap() {
        return map;
    }

    public int getProtection() {
        return protection;
    }
}
