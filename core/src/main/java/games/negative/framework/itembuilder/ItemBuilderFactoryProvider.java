package games.negative.framework.itembuilder;

import games.negative.framework.base.itembuilder.ItemBuilder;
import games.negative.framework.base.itembuilder.ItemBuilderFactory;
import games.negative.framework.util.version.ServerVersion;
import games.negative.framework.util.version.VersionChecker;
import games.negative.framework.v1_10.ItemBuilderProvider1_10;
import games.negative.framework.v1_11.ItemBuilderProvider1_11;
import games.negative.framework.v1_12.ItemBuilderProvider1_12;
import games.negative.framework.v1_13.ItemBuilderProvider1_13;
import games.negative.framework.v1_14.ItemBuilderProvider1_14;
import games.negative.framework.v1_15.ItemBuilderProvider1_15;
import games.negative.framework.v1_16.ItemBuilderProvider1_16;
import games.negative.framework.v1_17.ItemBuilderProvider1_17;
import games.negative.framework.v1_18.ItemBuilderProvider1_18;
import games.negative.framework.v1_19.ItemBuilderProvider1_19;
import games.negative.framework.v1_8.ItemBuilderProvider1_8;
import games.negative.framework.v1_9.ItemBuilderProvider1_9;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemBuilderFactoryProvider extends ItemBuilderFactory {

    private final ServerVersion version;

    public ItemBuilderFactoryProvider(VersionChecker version) {
        this.version = version.getServerVersion();
        setInstance(this);
    }

    @Override
    public ItemBuilder createItemBuilder(ItemStack itemStack) {
        switch (version) {
            case V1_8:
                return new ItemBuilderProvider1_8(itemStack);
            case V1_9:
                return new ItemBuilderProvider1_9(itemStack);
            case V1_10:
                return new ItemBuilderProvider1_10(itemStack);
            case V1_11:
                return new ItemBuilderProvider1_11(itemStack);
            case V1_12:
                return new ItemBuilderProvider1_12(itemStack);
            case V1_13:
                return new ItemBuilderProvider1_13(itemStack);
            case V1_14:
                return new ItemBuilderProvider1_14(itemStack);
            case V1_15:
                return new ItemBuilderProvider1_15(itemStack);
            case V1_16:
                return new ItemBuilderProvider1_16(itemStack);
            case V1_17:
                return new ItemBuilderProvider1_17(itemStack);
            case V1_18:
                return new ItemBuilderProvider1_18(itemStack);
            case V1_19:
                return new ItemBuilderProvider1_19(itemStack);
            default:
                throw new UnsupportedOperationException("Unsupported version: " + version);
        }
    }

    @Override
    public ItemBuilder createItemBuilder(Material material) {
        switch (version) {
            case V1_8:
                return new ItemBuilderProvider1_8(material);
            case V1_9:
                return new ItemBuilderProvider1_9(material);
            case V1_10:
                return new ItemBuilderProvider1_10(material);
            case V1_11:
                return new ItemBuilderProvider1_11(material);
            case V1_12:
                return new ItemBuilderProvider1_12(material);
            case V1_13:
                return new ItemBuilderProvider1_13(material);
            case V1_14:
                return new ItemBuilderProvider1_14(material);
            case V1_15:
                return new ItemBuilderProvider1_15(material);
            case V1_16:
                return new ItemBuilderProvider1_16(material);
            case V1_17:
                return new ItemBuilderProvider1_17(material);
            case V1_18:
                return new ItemBuilderProvider1_18(material);
            case V1_19:
                return new ItemBuilderProvider1_19(material);
            default:
                throw new UnsupportedOperationException("Unsupported version: " + version);
        }
    }

    @Override
    public ItemBuilder createItemBuilder(Material material, int amount) {
        switch (version) {
            case V1_8:
                return new ItemBuilderProvider1_8(material, amount);
            case V1_9:
                return new ItemBuilderProvider1_9(material, amount);
            case V1_10:
                return new ItemBuilderProvider1_10(material, amount);
            case V1_11:
                return new ItemBuilderProvider1_11(material, amount);
            case V1_12:
                return new ItemBuilderProvider1_12(material, amount);
            case V1_13:
                return new ItemBuilderProvider1_13(material, amount);
            case V1_14:
                return new ItemBuilderProvider1_14(material, amount);
            case V1_15:
                return new ItemBuilderProvider1_15(material, amount);
            case V1_16:
                return new ItemBuilderProvider1_16(material, amount);
            case V1_17:
                return new ItemBuilderProvider1_17(material, amount);
            case V1_18:
                return new ItemBuilderProvider1_18(material, amount);
            case V1_19:
                return new ItemBuilderProvider1_19(material, amount);
            default:
                throw new UnsupportedOperationException("Unsupported version: " + version);
        }
    }

    @Override
    public ItemBuilder createItemBuilder(Material material, int amount, byte durability) {
        switch (version) {
            case V1_8:
                return new ItemBuilderProvider1_8(material, amount, durability);
            case V1_9:
                return new ItemBuilderProvider1_9(material, amount, durability);
            case V1_10:
                return new ItemBuilderProvider1_10(material, amount, durability);
            case V1_11:
                return new ItemBuilderProvider1_11(material, amount, durability);
            case V1_12:
                return new ItemBuilderProvider1_12(material, amount, durability);
            case V1_13:
                return new ItemBuilderProvider1_13(material, amount, durability);
            case V1_14:
                return new ItemBuilderProvider1_14(material, amount, durability);
            case V1_15:
                return new ItemBuilderProvider1_15(material, amount, durability);
            case V1_16:
                return new ItemBuilderProvider1_16(material, amount, durability);
            case V1_17:
                return new ItemBuilderProvider1_17(material, amount, durability);
            case V1_18:
                return new ItemBuilderProvider1_18(material, amount, durability);
            case V1_19:
                return new ItemBuilderProvider1_19(material, amount, durability);
            default:
                throw new UnsupportedOperationException("Unsupported version: " + version);
        }
    }
}
