package games.negative.framework.v1_11;

import com.google.common.collect.Lists;
import games.negative.framework.base.itembuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemBuilderProvider1_11 implements ItemBuilder {

    private final ItemStack is;

    public ItemBuilderProvider1_11(Material material, int amount, byte durability) {
        this.is = new ItemStack(material, amount, durability);
    }

    public ItemBuilderProvider1_11(Material material) {
        this.is = new ItemStack(material);
    }

    public ItemBuilderProvider1_11(Material material, int amount) {
        this.is = new ItemStack(material, amount);
    }

    public ItemBuilderProvider1_11(ItemStack is) {
        this.is = is;
    }

    public ItemBuilder setName(@NotNull String name) {
        ItemMeta im = this.is.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        this.is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        is.setDurability(durability);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        is.setAmount(amount);
        return this;
    }

    public ItemBuilder setLore(@NotNull String... lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(translate(Arrays.asList(lore)));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setLore(@NotNull List<String> lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(translate(lore));
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(@NotNull Enchantment enchantment, int level) {
        is.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder addEnchantment(@NotNull Enchantment enchantment, int level) {
        is.addEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder removeEnchantment(@NotNull Enchantment enchantment) {
        is.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder setItemFlags(@NotNull ItemFlag... itemFlags) {
        ItemMeta im = is.getItemMeta();
        im.addItemFlags(itemFlags);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addItemFlags(@NotNull ItemFlag... itemFlags) {
        ItemMeta im = is.getItemMeta();
        im.addItemFlags(itemFlags);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeItemFlags(@NotNull ItemFlag... itemFlags) {
        ItemMeta im = is.getItemMeta();
        im.removeItemFlags(itemFlags);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        ItemMeta im = is.getItemMeta();
        im.spigot().setUnbreakable(unbreakable);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setSkullOwner(@NotNull String owner) {
        try {
            SkullMeta im = (SkullMeta) is.getItemMeta();
            im.setOwner(owner);
            is.setItemMeta(im);
        } catch (Exception ignored) {
        }
        return this;
    }

    public ItemBuilder addEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        is.addEnchantments(enchantments);
        return this;
    }

    public ItemBuilder removeLoreLine(String line) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (!lore.contains(line)) return this;
        lore.remove(line);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder removeLoreLine(int index) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        if (index < 0 || index > lore.size()) return this;
        lore.remove(index);
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (im.hasLore()) lore = new ArrayList<>(im.getLore());
        lore.add(translate(line));
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder addLoreLine(String line, int index) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        lore.set(index, translate(line));
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    @Override
    public ItemBuilder replaceLore(String original, String replacement) {
        ItemMeta im = is.getItemMeta();
        List<String> lore = new ArrayList<>(im.getLore());
        lore.replaceAll(s -> s.replace(original, replacement));
        im.setLore(lore);
        is.setItemMeta(im);
        return this;
    }

    public ItemBuilder setDyeColor(@NotNull DyeColor color) {
        throw new UnsupportedOperationException("This is not supported anymore");
    }

    public ItemBuilder setWoolColor(@NotNull DyeColor color) {
        throw new UnsupportedOperationException("This is not supported anymore");
    }

    public ItemBuilder setLeatherArmorColor(@NotNull Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        } catch (ClassCastException ignored) {
        }
        return this;
    }

    public ItemBuilder setMaterial(@NotNull Material material) {
        this.is.setType(material);
        return this;
    }

    public @NotNull String getDisplayName() {
        return is.getItemMeta().getDisplayName();
    }

    public short getDurability() {
        return is.getDurability();
    }

    public int getAmount() {
        return is.getAmount();
    }

    public @NotNull List<String> getLore() {
        return this.is.getItemMeta().getLore();
    }

    public @NotNull Map<Enchantment, Integer> getEnchantments() {
        return is.getEnchantments();
    }

    public @NotNull List<ItemFlag> getItemFlags() {
        return Lists.newArrayList(is.getItemMeta().getItemFlags());
    }

    public boolean isUnbreakable() {
        return is.getItemMeta().spigot().isUnbreakable();
    }

    public @Nullable String getSkullOwner() {
        try {
            SkullMeta im = (SkullMeta) is.getItemMeta();
            return im.getOwner();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable DyeColor getDyeColor() {
        return null;
    }

    public @Nullable DyeColor getWoolColor() {
        return null;
    }

    public @Nullable Color getLeatherArmorColor() {
        return null;
    }

    public @NotNull Material getMaterial() {
        return is.getType();
    }

    public ItemStack build() {
        return is;
    }

    public ItemBuilder clone() {
        return new ItemBuilderProvider1_11(is);
    }

    private String translate(@NotNull String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private List<String> translate(@NotNull Iterable<String> s) {
        List<String> result = Lists.newArrayList();
        for (String text : s) {
            result.add(translate(text));
        }
        return result;
    }

}
