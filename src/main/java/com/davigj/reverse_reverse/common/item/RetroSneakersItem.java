package com.davigj.reverse_reverse.common.item;

import com.davigj.reverse_reverse.client.model.ChunkyBootsModel;
import com.davigj.reverse_reverse.core.ReverseReverse;
import com.davigj.reverse_reverse.core.registry.RRItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

@EventBusSubscriber(modid = ReverseReverse.MOD_ID, value = {Dist.CLIENT})
public class RetroSneakersItem extends ArmorItem {
    public static final ResourceLocation SNEAKERS_TEXTURE = ReverseReverse.location("textures/entity/equipment/boots/retro_layer_1.png");
//    private static final AttributeModifier SPEED_BOOST_MODIFIER = new AttributeModifier(
//            "retro_boost",
//            0.80,
//            AttributeModifier.Operation.MULTIPLY_BASE
//    );
//    private static final AttributeModifier STEP_HEIGHT_MODIFIER = new AttributeModifier(
//            "retro_step",
//            1.5,
//            AttributeModifier.Operation.ADDITION
//    );

    public RetroSneakersItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, properties);
    }

//    @Override
//    public void onArmorTick(ItemStack stack, Level level, Player player) {
//        if (player.level.isClientSide) {
//            LocalPlayer local = (LocalPlayer) player;
//            AttributeInstance speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
//            AttributeInstance stepHeight = player.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());
//            if (local.input.forwardImpulse < 0 && speedAttribute != null && stepHeight != null) {
//                if (!speedAttribute.hasModifier(SPEED_BOOST_MODIFIER)) {
//                    speedAttribute.addTransientModifier(SPEED_BOOST_MODIFIER);
//                }
//                if (!stepHeight.hasModifier(STEP_HEIGHT_MODIFIER)) {
//                    stepHeight.addTransientModifier(STEP_HEIGHT_MODIFIER);
//                }
//                if (!player.isOnGround()) {
//                    Vec3 motion = player.getDeltaMovement();
//                    double newHorizontalSpeed = 1.08;
//                    player.setDeltaMovement(new Vec3(
//                            motion.x * newHorizontalSpeed,
//                            motion.y,
//                            motion.z * newHorizontalSpeed
//                    ));
//                }
//            } else {
//                if (speedAttribute != null && speedAttribute.hasModifier(SPEED_BOOST_MODIFIER)) {
//                    speedAttribute.removeModifier(SPEED_BOOST_MODIFIER);
//                }
//                if (stepHeight != null && stepHeight.hasModifier(STEP_HEIGHT_MODIFIER)) {
//                    stepHeight.removeModifier(STEP_HEIGHT_MODIFIER);
//                }
//            }
//        }
//    }
//
    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                return ChunkyBootsModel.INSTANCE;
            }
        }, new Holder[]{RRItems.RETRO_SNEAKERS});
    }

//    @Override
//    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
//        return slot == EquipmentSlot.FEET ? SNEAKERS_TEXTURE : super.getArmorTexture(stack, entity, slot, layer, innerModel);
//    }


    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.reverse_reverse.backwards").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.reverse_reverse.retro_sneakers_speed_boost").withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("item.reverse_reverse.retro_sneakers_step_height_boost").withStyle(ChatFormatting.BLUE));
    }
}

