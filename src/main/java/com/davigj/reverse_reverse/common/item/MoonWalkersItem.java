package com.davigj.reverse_reverse.common.item;

import com.davigj.reverse_reverse.core.registry.RRParticleTypes;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MoonWalkersItem extends ArmorItem {
    public MoonWalkersItem(ArmorMaterial material, EquipmentSlot slot, Properties builder) {
        super(material, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.level.isClientSide && !player.isOnGround()) {
            player.setNoGravity(((LocalPlayer) player).input.forwardImpulse < 0);
            if (player.tickCount % 12 == 0 && ((LocalPlayer) player).input.forwardImpulse < 0) {
                RandomSource rand = player.getRandom();
                double x = player.getX() - 0.5;
                double y = player.getY();
                double z = player.getZ() - 0.5;
                double d3 = (float) x + rand.nextFloat();
                double d6 = (float) z + rand.nextFloat();
                level.addParticle(RRParticleTypes.MOONDUST.get(), d3, y + 0.025, d6, 0, 0, 0);
            }
        }
    }
}
