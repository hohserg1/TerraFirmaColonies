package net.ckeeze.terrafirmacolonies.api;

import com.minecolonies.api.crafting.RecipeCraftingType;
import com.minecolonies.api.crafting.registry.CraftingType;
import net.ckeeze.terrafirmacolonies.Terrafirmacolonies;
import net.dries007.tfc.common.recipes.TFCRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TFCCraftingTypes {

    public static final DeferredRegister<CraftingType> DEFERRED_REGISTER = DeferredRegister.create(ResourceLocation.fromNamespaceAndPath("minecolonies", "craftingtypes"), Terrafirmacolonies.MODID);

    public static final RegistryObject<RecipeCraftingType> anvilSmithing = DEFERRED_REGISTER.register(
        "anvil_smithing",
        () -> new RecipeCraftingType(ResourceLocation.fromNamespaceAndPath(Terrafirmacolonies.MODID, "anvil_smithing"), TFCRecipeTypes.ANVIL.get(), __ -> true)
    );
    public static final RegistryObject<RecipeCraftingType> anvilWelding = DEFERRED_REGISTER.register(
        "anvil_welding",
        () -> new RecipeCraftingType(ResourceLocation.fromNamespaceAndPath(Terrafirmacolonies.MODID, "anvil_welding"), TFCRecipeTypes.WELDING.get(), __ -> true)
    );
}
