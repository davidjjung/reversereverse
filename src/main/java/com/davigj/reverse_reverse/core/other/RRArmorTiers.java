package com.davigj.reverse_reverse.core.other;

import com.teamabnormals.blueprint.core.api.BlueprintArmorMaterial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RRArmorTiers {
    public static final BlueprintArmorMaterial RETRO;
    public static final BlueprintArmorMaterial NOSTALGIC;
    public static final BlueprintArmorMaterial MOON;

    public RRArmorTiers() {}

    private static Supplier<SoundEvent> getSound(String modid, ResourceLocation sound) {
        return (ModList.get().isLoaded(modid) ? () -> ForgeRegistries.SOUND_EVENTS.getValue(sound) : () -> null);
    }

    static {
        RETRO = new BlueprintArmorMaterial(new ResourceLocation(
                "reverse_reverse", "retro"), 18, new int[]{1, 0, 0, 0}, 4,
                getSound("minecraft", new ResourceLocation("minecraft", "block.wool.break")), 0.0F, 0.0F, () -> {
            return Ingredient.of(new ItemLike[]{(ItemLike) Items.WHITE_WOOL});
        });
        MOON = new BlueprintArmorMaterial(new ResourceLocation(
                "reverse_reverse", "moon"), 23, new int[]{1, 0, 0, 0}, 8,
                getSound("minecraft", new ResourceLocation("minecraft", "item.armor.equip_diamond")), 0.0F, 0.0F, () -> {
            return Ingredient.of(new ItemLike[]{(ItemLike) Blocks.STONE});
        });
        NOSTALGIC = new BlueprintArmorMaterial(new ResourceLocation(
                "reverse_reverse", "nostalgic"), 12, new int[]{0, 0, 0, 1}, 0,
                getSound("minecraft", new ResourceLocation("minecraft", "block.flowering_azalea.place")), 0.0F, 0.0F, () -> {
            return Ingredient.of(new ItemLike[]{(ItemLike) Items.ROSE_BUSH});
        });
    }
}
