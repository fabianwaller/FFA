package de.rewex.ffa.listeners;

import de.rewex.ffa.Main;
import de.rewex.ffa.manager.InventoryHandler;
import de.rewex.ffa.manager.LocationManager;
import de.rewex.ffa.manager.RangManager;
import de.rewex.ffa.manager.ScoreAPI;
import de.rewex.mysql.players.stats.FFAStatsAPI;
import de.rewex.mysql.players.stats.PlayersAPI;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class KillListeners implements Listener {

    public static HashMap<String, String> fight = new HashMap();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        e.getDrops().clear();
        e.setDroppedExp(0);

        if((p.getKiller() instanceof Player)) {
            Player k = p.getKiller();
            e.setDeathMessage(Main.prefix + RangManager.getName(p) + " §7wurde von " + RangManager.getName(k) + " §7getötet");
            k.sendMessage(Main.prefix + "§7Du hast " + RangManager.getName(p) + " §7getötet §8[§a+ §b5 Coins§8]");
            p.sendMessage(Main.prefix + "§7Du wurdest von " + RangManager.getName(k) + " §7getötet §7§c" + getHealth(k) + " §4❤");

            int level = k.getLevel();
            level++;
            k.setLevel(level);
            //k.setHealth(20.0D);
            InventoryHandler.update(k);
            k.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,20*5,3));

            FFAStatsAPI.addKills(k.getUniqueId().toString(), Integer.valueOf(1));
            PlayersAPI.addCoins(k.getUniqueId().toString(), Integer.valueOf(5));
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 12.0F, 12.0F);

            int maxkillstreak = FFAStatsAPI.getKillstreak(k.getUniqueId().toString()).intValue();
            if(k.getLevel()>maxkillstreak) {
                FFAStatsAPI.setKillstreak(k.getUniqueId().toString(), Integer.valueOf(k.getLevel()));
            }
            ScoreAPI.updateScoreboard(k);

        } else {
            e.setDeathMessage(Main.prefix + RangManager.getName(p) + " §7ist gestorben");
        }

        FFAStatsAPI.addDeaths(p.getUniqueId().toString(), Integer.valueOf(1));
        ScoreAPI.updateScoreboard(p);
        fight.remove(p.getName());
        Respawn(p, 10);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if ((e.getEntity() instanceof Player)) {
            final Player p = (Player)e.getEntity();
            Player t = (Player)e.getDamager();
            fight.put(p.getName(), t.getName());


            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                public void run() {
                    fight.remove(p.getName());
                }
            }, 20L*10);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        InventoryHandler.setFFA(e.getPlayer());
        e.setRespawnLocation(LocationManager.getLocation("spawn"));
    }

    public void Respawn(final Player p, int Time) {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            public void run() {
                ((CraftPlayer)p).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
            }
        }, Time);
    }

    @EventHandler
    public void onKillstreak(PlayerLevelChangeEvent e) {
        Player k = e.getPlayer();

        if ((k.getLevel() == 3) || (k.getLevel() == 5) || (k.getLevel() == 10) || (k.getLevel() == 15)
                || (k.getLevel() == 20) || (k.getLevel() == 25) || (k.getLevel() == 30) || (k.getLevel() == 35)
                || (k.getLevel() == 36) || (k.getLevel() == 37) || (k.getLevel() == 38) || (k.getLevel() == 39)
                || (k.getLevel() == 40) || (k.getLevel() == 45) || (k.getLevel() == 50)) {
            Bukkit.broadcastMessage(Main.prefix + RangManager.getName(k) + " §7hat einen §9" + k.getLevel() + "§7-er §9Killstreak");
            k.sendMessage(Main.prefix + "§9" + k.getLevel() + "er Killstreak §8● §8[§a+ §b" + k.getLevel() + " Coins§8]");
            PlayersAPI.addCoins(k.getUniqueId().toString(), k.getLevel());
            k.playSound(k.getLocation(), Sound.LEVEL_UP, 12.0F, 12.0F);
        }
    }

    public int getHealth(Player p) {
        return (int)StrictMath.ceil(p.getHealth());
    }

}
