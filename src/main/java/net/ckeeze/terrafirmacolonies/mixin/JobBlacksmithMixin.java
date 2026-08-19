package net.ckeeze.terrafirmacolonies.mixin;

import com.minecolonies.api.colony.ICitizenData;
import com.minecolonies.core.colony.jobs.AbstractJobCrafter;
import com.minecolonies.core.colony.jobs.JobBlacksmith;
import com.minecolonies.core.entity.ai.workers.crafting.EntityAIWorkBlacksmith;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(JobBlacksmith.class)
public abstract class JobBlacksmithMixin extends AbstractJobCrafter<EntityAIWorkBlacksmith, JobBlacksmith> {
    public JobBlacksmithMixin(ICitizenData entity) {
        super(entity);
    }

    @Override
    public void finishRequest(boolean successful) {
        super.finishRequest(successful);
        if (successful) {
            damageHammer();
        }
    }

    @Unique
    private void damageHammer() {
        getCitizen().getInventory().damageInventoryItem(getCitizen().getInventory().getHeldItemSlot(InteractionHand.MAIN_HAND), 1, getCitizen().getEntity().orElse(null), null);
    }
}
