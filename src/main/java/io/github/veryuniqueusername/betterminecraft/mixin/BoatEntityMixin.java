package io.github.veryuniqueusername.betterminecraft.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;

import static io.github.veryuniqueusername.betterminecraft.BetterMinecraft.MOD_LOGGER;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin {
	@Shadow public abstract @Nullable Entity getPrimaryPassenger();

	@ModifyVariable(method = "updatePaddles", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
	private float changeF(float f) {
		return f + (Objects.requireNonNull(getPrimaryPassenger()).isSprinting() ? 0.04F : 0.0F);
	}


}
