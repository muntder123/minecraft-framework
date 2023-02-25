package games.negative.framework.base.itembuilder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class ItemBuilderFactory {

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private static ItemBuilderFactory instance;

    public abstract ItemBuilder createItemBuilder(ItemStack itemStack);

    public abstract ItemBuilder createItemBuilder(Material material);

    public abstract ItemBuilder createItemBuilder(Material material, int amount);

    public abstract ItemBuilder createItemBuilder(Material material, int amount, byte durability);
}
