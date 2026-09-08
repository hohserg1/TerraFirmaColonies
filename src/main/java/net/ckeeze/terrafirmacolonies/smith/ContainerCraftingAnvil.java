package net.ckeeze.terrafirmacolonies.smith;

import net.ckeeze.terrafirmacolonies.api.TFCContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCraftingAnvil extends AbstractContainerMenu {

    public static ContainerCraftingAnvil fromFriendlyByteBuf(int windowId, Inventory inv, FriendlyByteBuf packetBuffer) {
        BlockPos tePos = packetBuffer.readBlockPos();
        int moduleId = packetBuffer.readInt();
        return new ContainerCraftingAnvil(windowId, inv, tePos, moduleId);
    }

    private final IItemHandler anvilInventory;
    private final Inventory playerInventory;
    public final BlockPos buildingPos;
    private int moduleId;

    public ContainerCraftingAnvil(int windowId, Inventory inv, BlockPos pos, int moduleId) {
        super(TFCContainers.craftingAnvil.get(), windowId);
        playerInventory = inv;
        buildingPos = pos;
        this.moduleId = moduleId;
        anvilInventory = new ItemStackHandler(4);
        addSlot(new SlotItemHandler(anvilInventory, 0, 31, 68));
        addSlot(new SlotItemHandler(anvilInventory, 1, 13, 68));
        addSlot(new SlotItemHandler(anvilInventory, 2, 129, 68));
        addSlot(new SlotItemHandler(anvilInventory, 3, 147, 68));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(this.playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int var7 = 0; var7 < 9; ++var7) {
            this.addSlot(new Slot(this.playerInventory, var7, 8 + var7 * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        if (0 <= i && i < anvilInventory.getSlots()) {
            anvilInventory.extractItem(i, 64, false);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    public void clicked(int slotId, int clickedButton, ClickType mode, Player playerIn) {
        if (0 <= slotId && slotId < anvilInventory.getSlots()) {
            if (mode == ClickType.PICKUP || mode == ClickType.PICKUP_ALL || mode == ClickType.SWAP) {
                Slot slot = this.slots.get(slotId);
                this.handleSlotClick(slot, this.getCarried());
            }
        } else {
            super.clicked(slotId, clickedButton, mode, this.playerInventory.player);
        }
    }

    private ItemStack handleSlotClick(Slot slot, ItemStack stack) {
        if (stack.getCount() > 0) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            slot.set(copy);
        } else if (slot.getItem().getCount() > 0) {
            slot.set(ItemStack.EMPTY);
        }

        return slot.getItem().copy();
    }
}
