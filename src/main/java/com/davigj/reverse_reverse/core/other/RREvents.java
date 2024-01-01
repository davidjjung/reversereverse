package com.davigj.reverse_reverse.core.other;

import architectspalette.core.registry.APBlocks;
import architectspalette.core.registry.APItems;
import architectspalette.core.registry.util.APBlockItem;
import com.davigj.reverse_reverse.common.item.MoonWalkersItem;
import com.davigj.reverse_reverse.common.item.NostalgicGlassesItem;
import com.davigj.reverse_reverse.common.item.RetroSneakersItem;
import com.davigj.reverse_reverse.core.RRConfig;
import com.davigj.reverse_reverse.core.ReverseReverse;
import com.davigj.reverse_reverse.core.registry.RRItems;
import com.teamabnormals.blueprint.core.util.TradeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ReverseReverse.MOD_ID)
public class RREvents {
    @SubscribeEvent
    public static void onFOVSetup(ViewportEvent.ComputeFov event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
            if (boots.getItem() instanceof RetroSneakersItem) {
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

    @SubscribeEvent
    public static void villagerTrades(VillagerTradesEvent event) {
        if (RRConfig.COMMON.villagersTrade.get()) {
            if (RRConfig.COMMON.nostalgicTrade.get()) {
                TradeUtil.addVillagerTrades(event, VillagerProfession.ARMORER, TradeUtil.EXPERT, new TradeUtil.BlueprintTrade(
                        new ItemStack(Items.ROSE_BUSH, 1), new ItemStack(Items.EMERALD, 14),
                        new ItemStack(RRItems.NOSTALGIC_GLASSES.get(), 1), 3, 8, 3
                ));
            }
            if (RRConfig.COMMON.retroTrade.get()) {
                TradeUtil.addVillagerTrades(event, VillagerProfession.ARMORER, TradeUtil.JOURNEYMAN, new TradeUtil.BlueprintTrade(
                        12, new ItemStack(RRItems.RETRO_SNEAKERS.get()).getItem(), 1, 3, 8));
            }
            if (ModList.get().isLoaded("architects_palette") && RRConfig.COMMON.moonTrade.get()) {
                TradeUtil.addVillagerTrades(event, VillagerProfession.ARMORER, TradeUtil.MASTER, new TradeUtil.BlueprintTrade(
                        new ItemStack(APBlocks.MOONSTONE.get(), 4), new ItemStack(Items.EMERALD, 30),
                        new ItemStack(RRItems.MOON_WALKERS.get(), 1), 2, 25, 3
                ));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onFallDamage (LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player && event.getSource().isFall()) {
            if (player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof MoonWalkersItem) {
                float damage = event.getAmount();
                if (damage * Math.max((1 - RRConfig.COMMON.moonFallReduction.get()), 0) <= 3.0F) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onFallHurt (LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof MoonWalkersItem && event.getSource().isFall()) {
            event.setAmount((float) (event.getAmount() * Math.max((1 - RRConfig.COMMON.moonFallReduction.get()), 0)));
        }
    }
}