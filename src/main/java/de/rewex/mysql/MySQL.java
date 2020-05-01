package de.rewex.mysql;

import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    public static String prefix = "§7» §eMySQL §7| ";
    public static String host = "localhost";
    public static String database = "Server";
    public static String user = "Fabian";
    public static String password = "Lpwg1fcD";
    public static Connection con;

    public MySQL(String user, String pass, String host2, String dB) {}

    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", user, password);
                Bukkit.getConsoleSender().sendMessage(prefix + "§aconnected!");
            }
            catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(prefix + "§cError while connecting!");
            }
        }
    }

    public static void close() {
        if (isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(prefix + "§adisconnected!");
            }
            catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(prefix + "§cError while disconnecting!");
            }
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static void createTable() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS FFA (UUID VARCHAR(64), KILLS int, DEATHS int, KILLSTREAK int)");
                Bukkit.getConsoleSender().sendMessage(prefix + "§7FFA Stats table §acreated");
            }
            catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(prefix + "§cERROR §7while creating FFA Stats table");
                e.printStackTrace();
            }
        }
    }

    public static void update(String qry) {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResult(String qry) {
        ResultSet rs = null;
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

}
