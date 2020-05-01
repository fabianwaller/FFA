package de.rewex.ffa.manager;

import de.rewex.ffa.manager.utils.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class InventoryHandler {

    public static void setFFA(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(20.0D);
        p.setHealthScale(20);
        p.setFoodLevel(20);
        p.setExp(0.0F);
        p.setLevel(0);

        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        for(PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }

        p.getInventory().setItem(0, new ItemBuilder(Material.WOOD_AXE).setName("§dHolzaxt").build());
        p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§dLederbrustpanzer").build());

        LocationManager.telLocation(p, "spawn");
    }

}
