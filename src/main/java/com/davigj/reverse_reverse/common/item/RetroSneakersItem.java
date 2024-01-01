package com.davigj.reverse_reverse.common.item;

import com.davigj.reverse_reverse.client.model.MoonWalkersModel;
import com.davigj.reverse_reverse.client.model.RetroSneakersModel;
import com.davigj.reverse_reverse.core.RRConfig;
import com.davigj.reverse_reverse.core.ReverseReverse;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class RetroSneakersItem extends ArmorItem {
    private static final AttributeModifier SPEED_BOOST_MODIFIER = new AttributeModifier(
            "retro_boost",
            0.10,
            AttributeModifier.Operation.ADDITION
    );
    private static final AttributeModifier STEP_HEIGHT_MODIFIER = new AttributeModifier(
            "retro_step",
            1.0,
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
}

