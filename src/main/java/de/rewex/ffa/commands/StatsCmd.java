package de.rewex.ffa.commands;

import de.rewex.ffa.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCmd implements CommandExecutor {

    private final Main plugin;

    public StatsCmd(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Main.noplayer);
            return true;
        }

        Player p = (Player) sender;

        p.sendMessage(Main.prefix + "Deine Stats findest du im Scoreboard. Stats von anderen Spielern sind bald verfügbar!");

        return true;
    }

}
