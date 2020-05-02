package de.rewex.ffa.manager;

import de.rewex.ffa.manager.utils.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class InventoryHandler {

    public static void setFFA(Player p) {
        LocationManager.telLocation(p, "spawn");

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

        p.getInventory().setItem(0, new ItemBuilder(Material.WOOD_AXE).setName("§dHolzaxt").setUnbreakable().build());

    }

    public static void update(Player p) {
        int level = p.getLevel();
        if(level==1) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setItem(0, new ItemBuilder(Material.WOOD_SWORD).setName("§dHolzschwert").setUnbreakable().build());
        } else if(level==2) {
            p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§dLederjacke").setUnbreakable().build());
        } else if(level==3) {
            p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setName("§dLederstiefel").setUnbreakable().build());
        } else if(level==4) {
            p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setName("§dLederkappe").setUnbreakable().build());
        } else if(level==4) {
            p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setName("§dLederhose").setUnbreakable().build());
        } else if(level==5) {
            p.getInventory().setItem(0, new ItemBuilder(Material.WOOD_AXE).setName("§dHolzaxt").addEnchant(Enchantment.DAMAGE_ALL,1).setUnbreakable().build());
        } else if(level==6) {
            p.getInventory().setItem(0, new ItemBuilder(Material.WOOD_SWORD).setName("§dHolzschwert").addEnchant(Enchantment.DAMAGE_ALL,1).setUnbreakable().build());
        } else if(level==7) {
            p.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§dLederjacke").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==8) {
            p.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setName("§dLederstiefel").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==9) {
            p.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setName("§dLederkappe").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==10) {
            p.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setName("§dLederhose").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        }

        else if(level==11) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setItem(0, new ItemBuilder(Material.GOLD_SWORD).setName("§dGoldschwert").setUnbreakable().build());
        } else if(level==12) {
            p.getInventory().setHelmet(new ItemBuilder(Material.GOLD_CHESTPLATE).setName("§dGoldbrustpanzer").setUnbreakable().build());
        } else if(level==13) {
            p.getInventory().setBoots(new ItemBuilder(Material.GOLD_BOOTS).setName("§dGoldstiefel").setUnbreakable().build());
        } else if(level==14) {
            p.getInventory().setHelmet(new ItemBuilder(Material.GOLD_HELMET).setName("§dGoldhelm").setUnbreakable().build());
        } else if(level==15) {
            p.getInventory().setLeggings(new ItemBuilder(Material.GOLD_LEGGINGS).setName("§dGoldhose").setUnbreakable().build());
        } else if(level==16) {
            p.getInventory().setItem(0, new ItemBuilder(Material.GOLD_SWORD).setName("§dGoldschwert").addEnchant(Enchantment.DAMAGE_ALL,1).setUnbreakable().build());
        } else if(level==17) {
            p.getInventory().setItem(0, new ItemBuilder(Material.GOLD_CHESTPLATE).setName("§dGoldbrustpanzer").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==18) {
            p.getInventory().setChestplate(new ItemBuilder(Material.GOLD_BOOTS).setName("§dGoldstiefel").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==19) {
            p.getInventory().setBoots(new ItemBuilder(Material.GOLD_HELMET).setName("§dGoldhelm").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==20) {
            p.getInventory().setBoots(new ItemBuilder(Material.GOLD_LEGGINGS).setName("§dGoldhose").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        }

        else if(level==21) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setName("§dSteinschwert").setUnbreakable().build());
        } else if(level==22) {
            p.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§dKettenbrustpanzer").setUnbreakable().build());
        } else if(level==23) {
            p.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS).setName("§dKettenstiefel").setUnbreakable().build());
        } else if(level==24) {
            p.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§dKettenhelm").setUnbreakable().build());
        } else if(level==25) {
            p.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setName("§dKettenhose").setUnbreakable().build());
        } else if(level==26) {
            p.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setName("§dSteinschwert").addEnchant(Enchantment.DAMAGE_ALL,1).setUnbreakable().build());
        } else if(level==27) {
            p.getInventory().setItem(0, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§dKettenbrustpanzer").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==28) {
            p.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_BOOTS).setName("§dKettenstiefel").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==29) {
            p.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§dKettenhelm").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==30) {
            p.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setName("§dKettenhose").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        }

        else if(level==31) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setName("§dEisenschwert").setUnbreakable().build());
        } else if(level==32) {
            p.getInventory().setHelmet(new ItemBuilder(Material.IRON_CHESTPLATE).setName("§dEisenbrustpanzer").setUnbreakable().build());
        } else if(level==33) {
            p.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).setName("§dEisenstiefel").setUnbreakable().build());
        } else if(level==34) {
            p.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET).setName("§dEisenhelm").setUnbreakable().build());
        } else if(level==35) {
            p.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).setName("§dEisenhose").setUnbreakable().build());
        } else if(level==36) {
            p.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setName("§dEisenschwert").addEnchant(Enchantment.DAMAGE_ALL,1).setUnbreakable().build());
        } else if(level==37) {
            p.getInventory().setItem(0, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§dEisenbrustpanzer").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==38) {
            p.getInventory().setChestplate(new ItemBuilder(Material.IRON_BOOTS).setName("§dEisenstiefel").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==39) {
            p.getInventory().setBoots(new ItemBuilder(Material.IRON_HELMET).setName("§dEisenhelm").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==40) {
            p.getInventory().setBoots(new ItemBuilder(Material.IRON_LEGGINGS).setName("§dEisenhose").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        }

        else if(level==41) {
            p.getInventory().clear();
            p.getInventory().setArmorContents(null);
            p.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setName("§dDiamantschwert").setUnbreakable().build());
        } else if(level==42) {
            p.getInventory().setHelmet(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§dDiamantbrustpanzer").setUnbreakable().build());
        } else if(level==43) {
            p.getInventory().setBoots(new ItemBuilder(Material.DIAMOND_BOOTS).setName("§dDiamantstiefel").setUnbreakable().build());
        } else if(level==44) {
            p.getInventory().setHelmet(new ItemBuilder(Material.DIAMOND_HELMET).setName("§dDiamanthelm").setUnbreakable().build());
        } else if(level==45) {
            p.getInventory().setLeggings(new ItemBuilder(Material.DIAMOND_LEGGINGS).setName("§dDiamanthose").setUnbreakable().build());
        } else if(level==46) {
            p.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setName("§dDiamantschwert").addEnchant(Enchantment.DAMAGE_ALL,1).setUnbreakable().build());
        } else if(level==47) {
            p.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§dDiamantbrustpanzer").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==48) {
            p.getInventory().setChestplate(new ItemBuilder(Material.DIAMOND_BOOTS).setName("§dDiamantstiefel").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==49) {
            p.getInventory().setBoots(new ItemBuilder(Material.DIAMOND_HELMET).setName("§dDiamanthelm").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        } else if(level==50) {
            p.getInventory().setBoots(new ItemBuilder(Material.DIAMOND_LEGGINGS).setName("§dDiamanthose").addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,1).setUnbreakable().build());
        }




    }



}
