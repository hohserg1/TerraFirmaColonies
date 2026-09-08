package net.ckeeze.terrafirmacolonies.smith;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenCraftingAnvil extends Screen implements MenuAccess<ContainerCraftingAnvil> {
    public ScreenCraftingAnvil(ContainerCraftingAnvil container, Inventory inv, Component p_96550_) {
        super(p_96550_);
    }

    @Override
    public ContainerCraftingAnvil getMenu() {
        return null;
    }
}
