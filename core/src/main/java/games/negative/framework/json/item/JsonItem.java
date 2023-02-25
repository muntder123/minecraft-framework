package games.negative.framework.json.item;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import games.negative.framework.base.itembuilder.ItemBuilder;
import games.negative.framework.util.Utils;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@Data
public class JsonItem {

    @SerializedName("display-name")
    private final String displayName;

    @SerializedName("lore")
    private final ArrayList<String> lore;

    @SerializedName("material")
    private final Material material;

    @SerializedName("data")
    private final byte data;
    @SerializedName("amount")
    private final int amount;

    @SerializedName("enchantments")
    private final ArrayList<JsonItemEnchantment> enchantments;

    @SerializedName("flags")
    private final ArrayList<ItemFlag> flags;

    @SerializedName("unbreakable")
    private final boolean unbreakable;

    @SerializedName("optional-data")
    private final JsonOptionalDataContainer dataContainer;

    public JsonItem() {
        this.displayName = "%green%Example %blue%Display %orange%Name";
        this.lore = Lists.newArrayList("%green%Example %blue%Lore", "%orange%Line 2");
        this.material = Material.STONE;
        this.data = 0;
        this.amount = 1;
        this.enchantments = Lists.newArrayList(new JsonItemEnchantment("DAMAGE_ALL", 1));
        this.flags = Lists.newArrayList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
        this.unbreakable = true;
        this.dataContainer = new JsonOptionalDataContainer(new JsonOptionalDataValue[]{
                new JsonOptionalDataValue("example", "value")
        });
    }

    public JsonItem(@NotNull String displayName, @Nullable ArrayList<String> lore, @NotNull Material material, byte data, int amount, @Nullable ArrayList<JsonItemEnchantment> enchantments, @Nullable ArrayList<ItemFlag> flags, boolean unbreakable, @Nullable JsonOptionalDataContainer dataContainer) {
        this.displayName = displayName;
        this.lore = lore;
        this.material = material;
        this.data = data;
        this.amount = amount;
        this.enchantments = enchantments;
        this.flags = flags;
        this.unbreakable = unbreakable;
        this.dataContainer = dataContainer;
    }

    public ItemStack toItemStack() {
        ItemBuilder builder = ItemBuilder.newItemBuilder(this.material, this.amount, this.data);
        builder.setName(Utils.color(JsonTextTranslator.translate(this.displayName)));
        if (lore != null) {
            builder.setLore(Utils.color(JsonTextTranslator.translate(this.lore)));
        }

        if (this.enchantments != null) {
            for (JsonItemEnchantment enchantment : this.enchantments) {
                builder.addEnchantment(Enchantment.getByName(enchantment.getEnchantmentKey()), enchantment.getLevel());
            }
        }

        if (this.flags != null) {
            for (ItemFlag flag : this.flags) {
                builder.addItemFlags(flag);
            }
        }

        builder.setUnbreakable(unbreakable);
        return builder.build();
    }

    public ItemBuilder toItemBuilder() {
        return ItemBuilder.newItemBuilder(this.toItemStack());
    }


}
