package games.negative.framework.base.itembuilder;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public interface ItemBuilder extends Cloneable {

    ItemBuilder setName(@NotNull String name);

    ItemBuilder setDurability(short durability);

    ItemBuilder setAmount(int amount);

    ItemBuilder setLore(@NotNull String... lore);

    ItemBuilder setLore(@NotNull List<String> lore);

    ItemBuilder addUnsafeEnchantment(@NotNull Enchantment enchantment, int level);

    ItemBuilder addEnchantment(@NotNull Enchantment enchantment, int level);

    ItemBuilder removeEnchantment(@NotNull Enchantment enchantment);

    ItemBuilder setItemFlags(@NotNull ItemFlag... itemFlags);

    ItemBuilder addItemFlags(@NotNull ItemFlag... itemFlags);

    ItemBuilder removeItemFlags(@NotNull ItemFlag... itemFlags);

    ItemBuilder setUnbreakable(boolean unbreakable);

    ItemBuilder setSkullOwner(@NotNull String owner);

    ItemBuilder addEnchantments(@NotNull Map<Enchantment, Integer> enchantments);

    ItemBuilder removeLoreLine(String line);

    ItemBuilder removeLoreLine(int index);

    ItemBuilder addLoreLine(String line);

    ItemBuilder addLoreLine(String line, int index);

    ItemBuilder replaceLore(String original, String replacement);

    ItemBuilder setDyeColor(@NotNull DyeColor color);

    ItemBuilder setWoolColor(@NotNull DyeColor color);

    ItemBuilder setLeatherArmorColor(@NotNull Color color);

    ItemBuilder setMaterial(@NotNull Material material);

    @NotNull
    String getDisplayName();

    short getDurability();

    int getAmount();

    @NotNull
    List<String> getLore();

    @NotNull
    Map<Enchantment, Integer> getEnchantments();

    @NotNull
    List<ItemFlag> getItemFlags();

    boolean isUnbreakable();

    @Nullable
    String getSkullOwner();

    @Nullable
    DyeColor getDyeColor();

    @Nullable
    DyeColor getWoolColor();

    @Nullable
    Color getLeatherArmorColor();

    @NotNull
    Material getMaterial();

    ItemStack build();

    ItemBuilder clone();

    static ItemBuilder newItemBuilder(ItemStack itemStack) {
        return ItemBuilderFactory.getInstance().createItemBuilder(itemStack);
    }

    static ItemBuilder newItemBuilder(Material material) {
        return ItemBuilderFactory.getInstance().createItemBuilder(material);
    }

    static ItemBuilder newItemBuilder(Material material, int amount) {
        return ItemBuilderFactory.getInstance().createItemBuilder(material, amount);
    }

    static ItemBuilder newItemBuilder(Material material, int amount, byte durability) {
        return ItemBuilderFactory.getInstance().createItemBuilder(material, amount, durability);
    }

}
