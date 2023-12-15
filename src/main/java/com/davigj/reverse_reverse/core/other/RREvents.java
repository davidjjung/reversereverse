package com.davigj.reverse_reverse.core.other;

import com.davigj.reverse_reverse.common.item.NostalgicGlassesItem;
import com.davigj.reverse_reverse.common.item.RetroSneakersItem;
import com.davigj.reverse_reverse.core.ReverseReverse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ReverseReverse.MOD_ID)
public class RREvents {
    @SubscribeEvent
    public static void onFOVSetup(ViewportEvent.ComputeFov event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            ItemStack gauntlets = player.getItemBySlot(EquipmentSlot.FEET);
            if (gauntlets.getItem() instanceof RetroSneakersItem) {
                event.setFOV(70.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            ItemStack helm = player.getItemBySlot(EquipmentSlot.HEAD);
            if (helm.getItem() instanceof NostalgicGlassesItem) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onCameraSetup(ViewportEvent.ComputeCameraAngles event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            ItemStack helm = player.getItemBySlot(EquipmentSlot.HEAD);
            if (helm.getItem() instanceof NostalgicGlassesItem) {
                event.setYaw(event.getYaw() + 180.0F);
            }
        }
    }
}