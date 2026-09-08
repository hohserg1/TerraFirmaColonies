package net.ckeeze.terrafirmacolonies.api;

import net.ckeeze.terrafirmacolonies.Terrafirmacolonies;
import net.ckeeze.terrafirmacolonies.smith.ContainerCraftingAnvil;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TFCContainers {
    public static final DeferredRegister<MenuType<?>> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Terrafirmacolonies.MODID);

    public static RegistryObject<MenuType<ContainerCraftingAnvil>> craftingAnvil = DEFERRED_REGISTER.register("crafting_anvil", () -> IForgeMenuType.create(ContainerCraftingAnvil::fromFriendlyByteBuf));
}
