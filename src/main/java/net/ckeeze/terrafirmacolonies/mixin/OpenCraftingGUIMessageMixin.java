package net.ckeeze.terrafirmacolonies.mixin;

import com.minecolonies.api.colony.IColony;
import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.core.colony.buildings.modules.AbstractCraftingBuildingModule;
import com.minecolonies.core.network.messages.server.colony.building.OpenCraftingGUIMessage;
import net.ckeeze.terrafirmacolonies.api.TFCCraftingTypes;
import net.ckeeze.terrafirmacolonies.smith.ContainerCraftingAnvil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OpenCraftingGUIMessage.class)
public class OpenCraftingGUIMessageMixin {

    @Shadow
    private int id;

    @Inject(method = "onExecute", at = @At("HEAD"), cancellable = true, remap = false)
    public void onExecute(Context ctxIn, boolean isLogicalServer, IColony colony, IBuilding building, CallbackInfo ci) {
        ServerPlayer player = ctxIn.getSender();
        if (player != null) {
            if (building.getModule(id) instanceof AbstractCraftingBuildingModule module) {
                if (module.canLearn(TFCCraftingTypes.anvilSmithing.get())) {
                    NetworkHooks.openScreen(player, new MenuProvider() {
                        public @NotNull Component getDisplayName() {
                            return Component.literal("Anvil Crafting GUI");
                        }

                        public @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inv, @NotNull Player player) {
                            return new ContainerCraftingAnvil(id, inv, building.getID(), module.getProducer().getRuntimeID());
                        }
                    }, (buffer) -> new FriendlyByteBuf(buffer.writeBlockPos(building.getID()).writeInt(module.getProducer().getRuntimeID())));
                    ci.cancel();
                }
            }
        }
    }
}
