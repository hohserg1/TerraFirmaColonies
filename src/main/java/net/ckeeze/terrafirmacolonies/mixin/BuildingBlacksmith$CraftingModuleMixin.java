package net.ckeeze.terrafirmacolonies.mixin;

import com.minecolonies.api.colony.jobs.registry.JobEntry;
import com.minecolonies.api.crafting.IGenericRecipe;
import com.minecolonies.api.crafting.registry.CraftingType;
import com.minecolonies.core.colony.buildings.modules.AbstractCraftingBuildingModule;
import com.minecolonies.core.colony.buildings.workerbuildings.BuildingBlacksmith;
import net.ckeeze.terrafirmacolonies.api.TFCCraftingTypes;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Set;

@Mixin(BuildingBlacksmith.CraftingModule.class)
public class BuildingBlacksmith$CraftingModuleMixin extends AbstractCraftingBuildingModule.Crafting {

    public BuildingBlacksmith$CraftingModuleMixin(JobEntry jobEntry) {
        super(jobEntry);
    }

    @Override
    public Set<CraftingType> getSupportedCraftingTypes() {
        return Set.of(TFCCraftingTypes.anvilSmithing.get(), TFCCraftingTypes.anvilWelding.get());
    }

    /**
     * @author hohserg
     * @reason any recipe suitable
     */
    @Overwrite(remap = false)
    @Override
    public boolean isRecipeCompatible(@NotNull IGenericRecipe recipe) {
        return true;
    }
}
