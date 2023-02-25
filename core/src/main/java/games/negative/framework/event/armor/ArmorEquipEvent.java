package games.negative.framework.event.armor;

import games.negative.framework.event.PluginEvent;
import games.negative.framework.event.armor.internal.ArmorType;
import games.negative.framework.event.armor.internal.EquipMethod;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * @author Borlea
 * @since Jul 30, 2015
 */
public final class ArmorEquipEvent extends PluginEvent implements Cancellable {

    private final Player player;
    private final EquipMethod equipType;
    private final ArmorType type;
    private boolean cancel = false;
    private ItemStack oldArmorPiece, newArmorPiece;

    /**
     * Constructor for the ArmorEquipEvent.
     *
     * @param player The player who put on / removed the armor.
     * @param type The ArmorType of the armor added
     * @param oldArmorPiece The ItemStack of the armor removed.
     * @param newArmorPiece The ItemStack of the armor added.
     */
    public ArmorEquipEvent(Player player, EquipMethod equipType, ArmorType type, ItemStack oldArmorPiece, ItemStack newArmorPiece) {
        this.player = player;
        this.equipType = equipType;
        this.type = type;
        this.oldArmorPiece = oldArmorPiece;
        this.newArmorPiece = newArmorPiece;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Gets if this event is cancelled.
     *
     * @return If this event is cancelled
     */
    public boolean isCancelled() {
        return cancel;
    }

    /**
     * Sets if this event should be cancelled.
     *
     * @param cancel If this event should be cancelled.
     */
    public void setCancelled(final boolean cancel) {
        this.cancel = cancel;
    }

    public ArmorType getType() {
        return type;
    }

    /**
     * Returns the last equipped armor piece, could be a piece of armor, {@link Material#AIR}, or null.
     */
    public ItemStack getOldArmorPiece() {
        return oldArmorPiece;
    }

    public void setOldArmorPiece(final ItemStack oldArmorPiece) {
        this.oldArmorPiece = oldArmorPiece;
    }

    /**
     * Returns the newly equipped armor, could be a piece of armor, {@link Material#AIR}, or null.
     */
    public ItemStack getNewArmorPiece() {
        return newArmorPiece;
    }

    public void setNewArmorPiece(final ItemStack newArmorPiece) {
        this.newArmorPiece = newArmorPiece;
    }

    /**
     * Gets the method used to either equip or unequip an armor piece.
     */
    public EquipMethod getMethod() {
        return equipType;
    }

}
