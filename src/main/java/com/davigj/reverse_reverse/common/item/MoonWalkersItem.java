package com.davigj.reverse_reverse.common.item;

import com.davigj.reverse_reverse.client.model.MoonWalkersModel;
import com.davigj.reverse_reverse.core.ReverseReverse;
import com.davigj.reverse_reverse.core.registry.RRParticleTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

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

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                return new MoonWalkersModel<>(MoonWalkersModel.createArmorLayer().bakeRoot());
            }
        });
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return ReverseReverse.MOD_ID + ":textures/models/armor/moon_walkers.png";
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.reverse_reverse.backwards").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.reverse_reverse.moon_walkers_zero_gravity").withStyle(ChatFormatting.BLUE));
    }
}
