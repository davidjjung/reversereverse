package com.davigj.reverse_reverse.common.item;

import com.davigj.reverse_reverse.core.ReverseReverse;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class NostalgicGlassesItem extends ArmorItem {

    public NostalgicGlassesItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (player.tickCount % 20 == 0) {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, false, false));
        }
    }

    @Override
    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return new ResourceLocation(ReverseReverse.MOD_ID, "textures/models/armor/nostalgic_layer_1.png").toString();
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        tooltip.add(Component.translatable(MobEffects.REGENERATION.getDescriptionId()).append(" I (0:03)").withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("item.reverse_reverse.nostalgic_glasses_backwards").withStyle(ChatFormatting.BLUE));
    }
}
