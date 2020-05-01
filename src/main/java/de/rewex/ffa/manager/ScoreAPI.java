package de.rewex.ffa.manager;

import de.rewex.ffa.Main;
import de.rewex.mysql.players.gamepass.GamepassManager;
import de.rewex.mysql.players.stats.FFAStatsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreAPI {

    @SuppressWarnings({ "deprecation", "unused" })
    public static void setScoreboard(Player p) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.getObjective("aaa");
        if(obj == null) {
            obj = sb.registerNewObjective("aaa", "bbb");
        }

        obj.setDisplayName("  §9Rewex.de  ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int kills = FFAStatsAPI.getKills(p.getUniqueId().toString());
        int deaths = FFAStatsAPI.getDeaths(p.getUniqueId().toString());
        int killstreak = FFAStatsAPI.getKillstreak(p.getUniqueId().toString());

        obj.getScore("§1").setScore(12);
        obj.getScore("§8•§7● Map").setScore(11);
        obj.getScore("§dAsteroid").setScore(10);
        obj.getScore("§d").setScore(9);
        obj.getScore("§8•§7● Kills").setScore(8);
        obj.getScore(updateTeam(sb, "Kills", "§8➜ §d" + kills, "", ChatColor.AQUA)).setScore(7);
        obj.getScore("§3").setScore(6);
        obj.getScore("§8•§7● Deaths").setScore(5);
        obj.getScore(updateTeam(sb, "Deaths", "§8➜ §d" + deaths, "", ChatColor.GREEN)).setScore(4);
        obj.getScore("§4").setScore(3);
        obj.getScore("§8•§7● Killstreak").setScore(2);
        obj.getScore(updateTeam(sb, "Killstreak", "§8➜ §d" + killstreak, "", ChatColor.DARK_GREEN)).setScore(1);
        obj.getScore("§5").setScore(0);

        Team admin = getTeam(sb, "000Admin", "§4Admin §7| §4", "");
        Team mod = getTeam(sb, "001Mod", "§9Mod §7| §9", "");
        Team sup = getTeam(sb, "002Sup", "§bSup §7| §b", "");
        Team dev = getTeam(sb, "003Dev", "§dDev §7| §d", "");
        Team builder = getTeam(sb, "004Builder", "§aBuilder §7| §a", "");
        Team content = getTeam(sb, "005Content", "§3Content §7| §3", "");

        Team yootuber = getTeam(sb, "006Youtuber", "§5", "");
        Team titan = getTeam(sb, "007Titan", "§e", "");
        Team champ = getTeam(sb, "008Champ", "§c", "");
        Team prime = getTeam(sb, "009Prime", "§6", "");
        Team spieler = getTeam(sb, "010Spieler", "§7", "");

        for(Player all:Bukkit.getOnlinePlayers()) {
            String team = "010Spieler";
            if (all.hasPermission("team.admin")) {
                team = "000Admin";
            } else if (all.hasPermission("team.mod")) {
                team = "001Mod";
            } else if (all.hasPermission("team.sup")) {
                team = "002Sup";
            } else if (all.hasPermission("team.dev")) {
                team = "003Dev";
            } else if (all.hasPermission("team.builder")) {
                team = "004Builder";
            } else if (all.hasPermission("team.content")) {
                team = "005Content";
            } else if (all.hasPermission("server.yt")) {
                team = "006Youtuber";
            } else if (all.hasPermission("server.titan")) {
                team = "007Titan";
            } else if (all.hasPermission("server.champ")) {
                team = "008Champ";
            } else if (all.hasPermission("server.prime")) {
                team = "009Prime";
            }

            sb.getTeam(team).addPlayer(all);

            all.setDisplayName(sb.getTeam(team).getPrefix() + all.getName());

            all.setPlayerListName(sb.getTeam(team).getPrefix() + all.getName());
        }
        p.setScoreboard(sb);
    }

    @SuppressWarnings({ "deprecation", "unused" })
    public static void updateScoreboard(Player p) {
        if(p.getScoreboard() == null) {
            setScoreboard(p);
        }

        Scoreboard sb = p.getScoreboard();
        Objective obj = sb.getObjective("aaa");

        int kills = FFAStatsAPI.getKills(p.getUniqueId().toString());
        int deaths = FFAStatsAPI.getDeaths(p.getUniqueId().toString());
        int killstreak = FFAStatsAPI.getKillstreak(p.getUniqueId().toString());

        obj.getScore(updateTeam(sb, "Kills", "§8➜ §d" + kills, "", ChatColor.AQUA)).setScore(7);
        obj.getScore(updateTeam(sb, "Deaths", "§8➜ §d" + deaths, "", ChatColor.GREEN)).setScore(4);
        obj.getScore(updateTeam(sb, "Killstreak", "§8➜ §d" + killstreak, "", ChatColor.DARK_GREEN)).setScore(1);

        Team admin = getTeam(sb, "000Admin", "§4Admin §7| §4", "");
        Team mod = getTeam(sb, "001Mod", "§9Mod §7| §9", "");
        Team sup = getTeam(sb, "002Sup", "§bSup §7| §b", "");
        Team dev = getTeam(sb, "003Dev", "§dDev §7| §d", "");
        Team builder = getTeam(sb, "004Builder", "§aBuilder §7| §a", "");
        Team content = getTeam(sb, "005Content", "§3Content §7| §3", "");

        Team yootuber = getTeam(sb, "006Youtuber", "§5", "");
        Team titan = getTeam(sb, "007Titan", "§e", "");
        Team champ = getTeam(sb, "008Champ", "§c", "");
        Team prime = getTeam(sb, "009Prime", "§6", "");
        Team spieler = getTeam(sb, "010Spieler", "§7", "");

        for(Player all:Bukkit.getOnlinePlayers()) {
            String team = "010Spieler";
            if (all.hasPermission("team.admin")) {
                team = "000Admin";
            } else if (all.hasPermission("team.mod")) {
                team = "001Mod";
            } else if (all.hasPermission("team.sup")) {
                team = "002Sup";
            } else if (all.hasPermission("team.dev")) {
                team = "003Dev";
            } else if (all.hasPermission("team.builder")) {
                team = "004Builder";
            } else if (all.hasPermission("team.content")) {
                team = "005Content";
            } else if (all.hasPermission("server.yt")) {
                team = "006Youtuber";
            } else if (all.hasPermission("server.titan")) {
                team = "007Titan";
            } else if (all.hasPermission("server.champ")) {
                team = "008Champ";
            } else if (all.hasPermission("server.prime")) {
                team = "009Prime";
            }

            sb.getTeam(team).addPlayer(all);
        }
    }

    public static Team getTeam(Scoreboard sb, String Team, String prefix, String suffix) {
        Team team = sb.getTeam(Team);
        if(team == null) {
            team = sb.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setAllowFriendlyFire(true);
        team.setCanSeeFriendlyInvisibles(true);
        return team;
    }

    public static String updateTeam(Scoreboard sb, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = sb.getTeam(Team);
        if(team == null) {
            team = sb.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());
        return entry.toString();
    }

    public static void startUpdater() {

        new BukkitRunnable() {

            @Override
            public void run() {

                for(Player all:Bukkit.getOnlinePlayers()) {
                    updateScoreboard(all);
                }


            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }

}
