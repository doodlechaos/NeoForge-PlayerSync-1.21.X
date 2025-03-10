package net.doodlechaos.playersync.mixin;

import net.doodlechaos.playersync.PlayerSync;
import net.minecraft.client.DeltaTracker;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(DeltaTracker.Timer.class)
public class DeltaTrackerTimerMixin {

    /**
     * Overrides the tick delta value if a custom value is set in Playersync.
     * The method signature "getGameTimeDeltaPartialTick(Z)F" means it takes a boolean and returns a float.
     */
    @Inject(method = "getGameTimeDeltaPartialTick", at = @At("HEAD"), cancellable = true)
    private void onGetGameTimeDeltaPartialTick(boolean runsNormally, CallbackInfoReturnable<Float> cir) {
        // Retrieve the custom tick delta from your mod.
        float customDelta = PlayerSync.myTickDelta;
        // If customDelta is set to a valid value (here we assume a value >= 0 means "override"),
        // then cancel the original method and set the return value.
        if (customDelta >= 0) {
            cir.setReturnValue(customDelta);
        }
    }
}