package net.ckeeze.terrafirmacolonies.mixin;

import com.minecolonies.api.entity.ai.statemachine.states.AIWorkerState;
import com.minecolonies.api.entity.ai.statemachine.states.IAIState;
import com.minecolonies.api.util.InventoryUtils;
import com.minecolonies.core.colony.buildings.workerbuildings.BuildingBlacksmith;
import com.minecolonies.core.colony.jobs.JobBlacksmith;
import com.minecolonies.core.entity.ai.workers.crafting.AbstractEntityAICrafting;
import com.minecolonies.core.entity.ai.workers.crafting.EntityAIWorkBlacksmith;
import com.minecolonies.core.util.citizenutils.CitizenItemUtils;
import net.ckeeze.terrafirmacolonies.api.TFCEquipmentTypes;
import net.minecraft.world.InteractionHand;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityAIWorkBlacksmith.class)
public abstract class EntityAIWorkBlacksmithMixin extends AbstractEntityAICrafting<JobBlacksmith, BuildingBlacksmith> {
    public EntityAIWorkBlacksmithMixin(@NotNull JobBlacksmith job) {
        super(job);
    }

    @Override
    protected IAIState decide() {
        if (this.checkForToolOrWeapon(TFCEquipmentTypes.tfchammer.get()) && this.checkForToolOrWeapon(TFCEquipmentTypes.tfccoal.get()))
            return super.decide();
        else
            return AIWorkerState.IDLE;
    }

    @Override
    protected IAIState craft() {
        if (ensureForgeIgnited() && equipHammer()) {
            return super.craft();
        } else {
            return AIWorkerState.PREPARING;
        }
    }

    @Unique
    private boolean equipHammer() {
        int slot = InventoryUtils.findFirstSlotInItemHandlerWith(getInventory(), TFCEquipmentTypes.tfchammer.get()::checkIsEquipment);
        if (slot == -1) {
            return false;
        } else {
            CitizenItemUtils.setHeldItem(this.worker, InteractionHand.MAIN_HAND, slot);
            return true;
        }
    }

    @Unique
    private boolean ensureForgeIgnited() {
        return true;
    }
}
