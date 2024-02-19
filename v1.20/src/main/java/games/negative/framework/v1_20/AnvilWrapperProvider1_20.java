package games.negative.framework.v1_20;

import games.negative.framework.base.gui.anvil.AnvilVersionWrapper;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutCloseWindow;
import net.minecraft.network.protocol.game.PacketPlayOutOpenWindow;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.IInventory;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.inventory.*;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class AnvilWrapperProvider1_20 implements AnvilVersionWrapper {

    private int getRealNextContainerId(Player player) {
        return toNMS(player).nextContainerCounter();
    }

    /**
     * Turns a {@link Player} into an NMS one
     *
     * @param player The player to be converted
     * @return the NMS EntityPlayer
     */
    private EntityPlayer toNMS(Player player) {
        return ((EntityPlayer) player);
    }

    @Override
    public int getNextContainerId(Player player, Object container) {
        return ((AnvilContainer) container).getContainerId();
    }

    @Override
    public void handleInventoryCloseEvent(Player player) {
        CraftEventFactory.handleInventoryCloseEvent(toNMS(player));
    }

    @Override
    public void sendPacketOpenWindow(Player player, int containerId, String inventoryTitle) {
        toNMS(player).c.a(new PacketPlayOutOpenWindow(containerId, Containers.h, IChatBaseComponent.a(inventoryTitle)));
    }

    @Override
    public void sendPacketCloseWindow(Player player, int containerId) {
        toNMS(player).c.a(new PacketPlayOutCloseWindow(containerId));
    }

    @Override
    public void setActiveContainerDefault(Player player) {
        toNMS(player).bS = toNMS(player).bR;
    }

    @Override
    public void setActiveContainer(Player player, Object container) {
        toNMS(player).bS = (Container) container;
    }

    @Override
    public void setActiveContainerId(Object container, int containerId) {}

    @Override
    public void addActiveContainerSlotListener(Object container, Player player) {
        toNMS(player).a((Container) container);
    }

    @Override
    public Inventory toBukkitInventory(Object container) {
        return ((Container) container).getBukkitView().getTopInventory();
    }

    @Override
    public Object newContainerAnvil(Player player, String title) {
        return new AnvilContainer(player, getRealNextContainerId(player), title);
    }

    private static class AnvilContainer extends ContainerAnvil {
        public AnvilContainer(Player player, int containerId, String guiTitle) {
            super(
                    containerId,
                    ((EntityPlayer) player).fS(),
                    ContainerAccess.a(((CraftWorld) player.getWorld()).getHandle(), new BlockPosition(0, 0, 0)));
            this.checkReachable = false;
            setTitle(IChatBaseComponent.a(guiTitle));
        }

        @Override
        public void m() {
            Slot output = this.b(2);
            if (!output.h()) {
                output.e(this.b(0).g().p());
            }

            this.w.a(0);

            // Sync to the client
            this.b();
            this.d();
        }

        @Override
        public void b(EntityHuman player) {}

        @Override
        protected void a(EntityHuman player, IInventory container) {}

        public int getContainerId() {
            return this.j;
        }
    }

}
