package de.rewex.ffa.commands;

import de.rewex.ffa.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoHitDelayCmd implements CommandExecutor {

    private final Main plugin;

    public NoHitDelayCmd(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Main.noplayer);
            return true;
        }

        Player p = (Player) sender;

        if(!p.hasPermission("ffa.nohitdelay")) {
            p.sendMessage(Main.noperm);
            return true;
        }

        if(args.length == 0) {
            if(Main.getInstance().nohitdelay == true) {
                p.sendMessage(Main.prefix + "NoHitDelay ist §aeingeschaltet");
            } else {
                p.sendMessage(Main.prefix + "NoHitDelay ist §causgeschaltet");
            }

        } else if (args.length == 1 && args[0].equalsIgnoreCase("toggle")) {
            if(Main.getInstance().nohitdelay == true) {
                p.sendMessage(Main.prefix + "§aNoHitDelay ist nun eingeschaltet");
                Bukkit.getOnlinePlayers().forEach(all -> {
                    setNoHitDelay(all);
                });
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 3.0F, 2.0F);
            } else {
                p.sendMessage(Main.prefix + "Um NoHitDelay auszuschalten starte bitte den Server erneut");
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 3.0F, 2.0F);
            }

        } else {
            p.sendMessage(Main.prefix + "/nohitdelay | /nohitdelay toggle");
        }

        return true;
    }

    public void setNoHitDelay(Player p) {
        p.setMaximumNoDamageTicks(1);
    }

}
