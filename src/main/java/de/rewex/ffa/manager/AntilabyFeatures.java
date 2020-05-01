package de.rewex.ffa.manager;

import de.rewex.ffa.Main;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class AntilabyFeatures implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        HashMap<EnumLabyModFeature, Boolean> list = new HashMap();
        list.put(EnumLabyModFeature.ANIMATIONS, Boolean.valueOf(false));
        list.put(EnumLabyModFeature.DAMAGEINDICATOR, Boolean.valueOf(false));
        list.put(EnumLabyModFeature.MINIMAP_RADAR, Boolean.valueOf(false));
        list.put(EnumLabyModFeature.POTIONS, Boolean.valueOf(false));

        setLabyModFeature(e.getPlayer(), list);
        e.getPlayer().sendMessage(Main.prefix + "Verbotene LabyMod §7Funktionen wurden vom §9System §7auf diesem Gameserver ausgeschaltet");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setLabyModFeature(Player p, HashMap<EnumLabyModFeature, Boolean> list) {
        try {
            HashMap<String, Boolean> nList = new HashMap();
            for (EnumLabyModFeature f : list.keySet()) {
                nList.put(f.name(), list.get(f));
            }
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(nList);
            ByteBuf a = Unpooled.copiedBuffer(byteOut.toByteArray());
            PacketDataSerializer b = new PacketDataSerializer(a);
            PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("LABYMOD", b);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
            System.out.print("[LabyMod] Disable LabyMod functions for " + p.getName());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static enum EnumLabyModFeature {
        FOOD,  GUI,  NICK,  BLOCKBUILD,  CHAT,  EXTRAS,  ANIMATIONS,  POTIONS,  ARMOR,  DAMAGEINDICATOR,  MINIMAP_RADAR;

        private EnumLabyModFeature() {}
    }

}
