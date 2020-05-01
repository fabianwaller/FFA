package de.rewex.ffa.listeners;

import de.rewex.ffa.Main;
import de.rewex.ffa.manager.InventoryHandler;
import de.rewex.ffa.manager.RangManager;
import de.rewex.ffa.manager.utils.TitleAPI;
import de.rewex.mysql.players.stats.FFAStatsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(Main.prefix + "§7» " + RangManager.getName(p) +  " §7hat das Spiel betreten");

        FFAStatsAPI.createPlayer(p.getUniqueId().toString());
        InventoryHandler.setFFA(p);



        //Main.getInstance().updateMotd();

        TitleAPI.sendTitle(p, 5, 50, 5, "§d§lFFA", "§fClassic");
        //ScoreAPI.setScoreboard(p);
        TitleAPI.sendTabTitle(p, "\n   §9§lRewex.de §8× §7Dein Minigames Netzwerk   "
                        + "\n§7Derzeitiger Server §8× §dFFA #1"
                        + "\n ",
                "\n §cHacker§7? §c/report"
                        + "\n ");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(Main.prefix + "§7« " + RangManager.getName(e.getPlayer()) +  " §7hat das Spiel verlassen");

        if(KillListeners.fight.containsKey(e.getPlayer().getName())) {
            Bukkit.broadcastMessage(Main.prefix + RangManager.getName(e.getPlayer()) + " §7hat das Spiel während dem Kampf verlassen");

            Player k = Bukkit.getPlayer(KillListeners.fight.get(e.getPlayer().getName()));

            Bukkit.broadcastMessage(Main.prefix + RangManager.getName(e.getPlayer()) + " §7wurde von " + RangManager.getName(k) + " §7getötet");
            k.sendMessage(Main.prefix + "§7Du hast " + RangManager.getName(e.getPlayer()) + " §7getötet §8[§a+ §b5 Coins§8]");

            int level = k.getLevel();
            level++;
            k.setLevel(level);
            k.setHealth(20.0D);


            FFAStatsAPI.addKills(k.getUniqueId().toString(), Integer.valueOf(1));
            //PlayersAPI.addCoins(k.getUniqueId().toString(), Integer.valueOf(5));

            int maxkillstreak = FFAStatsAPI.getKillstreak(k.getUniqueId().toString()).intValue();

            if(k.getLevel()>maxkillstreak) {
                FFAStatsAPI.setKillstreak(e.getPlayer().getUniqueId().toString(), Integer.valueOf(k.getLevel()));
            }

        }
    }

    @EventHandler
    public void onLeave(PlayerKickEvent e) {
        e.setLeaveMessage(Main.prefix + "§7« " + RangManager.getName(e.getPlayer()) +  " §7hat das Spiel verlassen");
    }



}
