package com.davigj.reverse_reverse.common.item;

import com.davigj.reverse_reverse.client.model.RetroSneakersModel;
import com.davigj.reverse_reverse.core.ReverseReverse;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class RetroSneakersItem extends ArmorItem {
    private static final AttributeModifier SPEED_BOOST_MODIFIER = new AttributeModifier(
            "retro_boost",
            0.80,
            AttributeModifier.Operation.MULTIPLY_BASE
    );
    private static final AttributeModifier STEP_HEIGHT_MODIFIER = new AttributeModifier(
            "retro_step",
            1.5,
            AttributeModifier.Operation.ADDITION
    );

    public RetroSneakersItem(ArmorMaterial material, EquipmentSlot slot, Properties builder) {
        super(material, slot, builder);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.level.isClientSide) {
            LocalPlayer local = (LocalPlayer) player;
            AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            AttributeInstance stepHeight = player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());
            if (local.input.forwardImpulse < 0 && speedAttribute != null && stepHeight != null) {
                if (!speedAttribute.hasModifier(SPEED_BOOST_MODIFIER)) {
                    speedAttribute.addTransientModifier(SPEED_BOOST_MODIFIER);
                }
                if (!stepHeight.hasModifier(STEP_HEIGHT_MODIFIER)) {
                    stepHeight.addTransientModifier(STEP_HEIGHT_MODIFIER);
                }
                if (!player.isOnGround()) {
                    Vec3 motion = player.getDeltaMovement();
                    double newHorizontalSpeed = 1.08;
                    player.setDeltaMovement(new Vec3(
                            motion.x * newHorizontalSpeed,
                            motion.y,
                            motion.z * newHorizontalSpeed
                    ));
                }
            } else {
                if (speedAttribute != null && speedAttribute.hasModifier(SPEED_BOOST_MODIFIER)) {
                    speedAttribute.removeModifier(SPEED_BOOST_MODIFIER);
                }
                if (stepHeight != null && stepHeight.hasModifier(STEP_HEIGHT_MODIFIER)) {
                    stepHeight.removeModifier(STEP_HEIGHT_MODIFIER);
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                return new RetroSneakersModel<>(RetroSneakersModel.createArmorLayer().bakeRoot());
            }
        });
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return new ResourceLocation(ReverseReverse.MOD_ID, "textures/models/armor/retro_sneakers.png").toString();
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.reverse_reverse.backwards").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.reverse_reverse.retro_sneakers_speed_boost").withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("item.reverse_reverse.retro_sneakers_step_height_boost").withStyle(ChatFormatting.BLUE));
    }
}

