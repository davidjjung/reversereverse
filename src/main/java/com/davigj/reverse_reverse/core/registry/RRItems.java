package com.davigj.reverse_reverse.core.registry;

import com.davigj.reverse_reverse.common.item.MoonWalkersItem;
import com.davigj.reverse_reverse.common.item.NostalgicGlassesItem;
import com.davigj.reverse_reverse.common.item.RetroSneakersItem;
import com.davigj.reverse_reverse.core.ReverseReverse;
import com.davigj.reverse_reverse.core.other.RRArmorTiers;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ReverseReverse.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RRItems {
    public static final ItemSubRegistryHelper HELPER = ReverseReverse.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> RETRO_SNEAKERS = HELPER.createItem("retro_sneakers", () -> {
        return new RetroSneakersItem(RRArmorTiers.RETRO, EquipmentSlot.FEET,
                (new Item.Properties()).durability(84).tab(CreativeModeTab.TAB_COMBAT));});

    public static final RegistryObject<Item> NOSTALGIC_GLASSES = HELPER.createItem("nostalgic_glasses", () -> {
        return new NostalgicGlassesItem(RRArmorTiers.NOSTALGIC, EquipmentSlot.HEAD,
                (new Item.Properties()).durability(120).tab(CreativeModeTab.TAB_COMBAT));});

    public static final RegistryObject<Item> MOON_WALKERS = HELPER.createItem("moon_walkers", () -> {
        return new MoonWalkersItem(RRArmorTiers.MOON, EquipmentSlot.FEET,
                (new Item.Properties()).durability(100).tab(CreativeModeTab.TAB_COMBAT));});
}
