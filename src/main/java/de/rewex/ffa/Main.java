package de.rewex.ffa;

import de.rewex.anitcheat.Autoclicker;
import de.rewex.ffa.chat.ChatListeners;
import de.rewex.ffa.commands.*;
import de.rewex.ffa.listeners.ConnectListeners;
import de.rewex.ffa.listeners.KillListeners;
import de.rewex.ffa.listeners.MapProtect;
import de.rewex.ffa.listeners.PlayerListeners;
import de.rewex.ffa.manager.*;
import de.rewex.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements PluginMessageListener {

    public static String prefix = "§5•§d● FFA §7| ";
    public static String passpr = "§e•§6● Gamepass §7| ";
    public static String noperm = prefix + "§cDazu hast du keine Rechte§8!";
    public static String offplayer = prefix + "§cDieser Spieler ist offline§8!";
    public static String noplayer = "[FFA] Nur ein Spieler kann diesen Befehl ausführen";

    public static Main instance;
    public JoinState state;
    public MapSwitcher mapswitcher;

    public boolean nohitdelay = false;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        state = JoinState.JOIN;
        mapswitcher = new MapSwitcher();

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
        //mapswitcher.startCounter();
        updateMotd();

        Bukkit.getConsoleSender().sendMessage(Main.prefix + "§aPlugin aktiviert §7[§a" + getDescription().getVersion() + "]");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cPlugin deaktiviert");
    }

    private void registerCommands() {
        getCommand("build").setExecutor(new BuildCmd(this));
        //getCommand("nohitdelay").setExecutor(new NoHitDelayCmd(this));
        getCommand("setlocation").setExecutor(new SetlocCmd(this));
        getCommand("spawn").setExecutor(new SpawnCmd(this));
        getCommand("stats").setExecutor(new StatsCmd(this));

    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        //de.rewex.ffa.chat
        pm.registerEvents(new ChatListeners(), this);

        //de.rewex.ffa.listeners
        pm.registerEvents(new ConnectListeners(), this);
        pm.registerEvents(new KillListeners(), this);
        pm.registerEvents(new MapProtect(), this);
        pm.registerEvents(new PlayerListeners(), this);

        //de.rewex.ffa.manager
        pm.registerEvents(new AntilabyFeatures(), this);
        pm.registerEvents(new Autoclicker(), this);

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
