package de.rewex.ffa;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import de.rewex.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements PluginMessageListener {

    public static String prefix = "§7» §dFFA §7| ";
    public static String noperm = prefix + "§cDazu hast du keine Rechte§8!";
    public static String offplayer = prefix + "§cDieser Spieler ist offline§8!";
    public static String noplayer = "[FFA] Nur ein Spieler kann diesen Befehl ausführen";

    public static Main instance;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

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

        Bukkit.getConsoleSender().sendMessage(Main.prefix + "§aPlugin aktiviert §7[§a" + getDescription().getVersion() + "]");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cPlugin deaktiviert");
    }

    private void registerCommands() {
        //getCommand("build").setExecutor(new BuildCmd(this));


    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();

        //de.rewex.ffa.
        //pm.registerEvents(new Enterhaken(), this);


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
}
