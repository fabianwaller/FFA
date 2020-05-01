package de.rewex.ffa;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import de.rewex.ffa.commands.BuildCmd;
import de.rewex.ffa.commands.SetlocCmd;
import de.rewex.ffa.commands.SpawnCmd;
import de.rewex.ffa.commands.StatsCmd;
import de.rewex.ffa.listeners.ConnectListeners;
import de.rewex.ffa.manager.ScoreAPI;
import de.rewex.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements PluginMessageListener {

    public static String prefix = "§7» §dFFA §7| ";
    public static String passpr = "§7» §6Gamepass §7| ";
    public static String noperm = prefix + "§cDazu hast du keine Rechte§8!";
    public static String offplayer = prefix + "§cDieser Spieler ist offline§8!";
    public static String noplayer = "[FFA] Nur ein Spieler kann diesen Befehl ausführen";

    public static Main instance;
    public JoinState state;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        state = JoinState.JOIN;

        getServer().getMessenger().registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "LABYMOD");
        getServer().getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "WDL|CONTROL");

        registerCommands();
        registerListeners();

        MySQL.connect();
        MySQL.createTable();
        if (!MySQL.isConnected()) {
            Bukkit.shutdown();
        }

        ScoreAPI.startUpdater();
        updateMotd();
        Bukkit.getConsoleSender().sendMessage(Main.prefix + "§aPlugin aktiviert §7[§a" + getDescription().getVersion() + "]");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cPlugin deaktiviert");
    }

    private void registerCommands() {
        getCommand("build").setExecutor(new BuildCmd(this));
        getCommand("setlocation").setExecutor(new SetlocCmd(this));
        getCommand("spawn").setExecutor(new SpawnCmd(this));
        getCommand("stats").setExecutor(new StatsCmd(this));



    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        //de.rewex.ffa.listeners
        pm.registerEvents(new ConnectListeners(), this);


    }

    public void onPluginMessageReceived(String channel, Player p, byte[] data) {
        if (channel.equals("WDL|INIT")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + p.getName() + " §7Du wurdest von §9§lRewex.de§r §7gekickt!"
                    + "\n "
                    + "\n§7Grund: §cWorldDownloader [WDL]"
                    + "\n§7Gekickt von: §cSystem"
                    + "\n ");
        }
    }

    public void updateMotd() {
        if(Bukkit.getOnlinePlayers().size()<Bukkit.getMaxPlayers()) {
            Main.getInstance().state = JoinState.JOIN;
        } else {
            Main.getInstance().state = JoinState.FULL;
        }

        if(Main.getInstance().state == JoinState.JOIN) {
            ((CraftServer)Bukkit.getServer()).getServer().setMotd("§aJoin");
        } else {
            ((CraftServer)Bukkit.getServer()).getServer().setMotd("§cFull");
        }
    }


}
