package com.davigj.reverse_reverse.common.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

//@EventBusSubscriber(modid = ReverseReverse.MOD_ID, value = {Dist.CLIENT})
public class MoonWalkersItem extends ArmorItem {
    public MoonWalkersItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, properties);
    }

    //    @Override
//    public void onArmorTick(ItemStack stack, Level level, Player player) {
//        if (player.level.isClientSide && !player.isOnGround()) {
//            player.setNoGravity(((LocalPlayer) player).input.forwardImpulse < 0);
//            if (player.tickCount % 12 == 0 && ((LocalPlayer) player).input.forwardImpulse < 0) {
//                RandomSource rand = player.getRandom();
//                double x = player.getX() - 0.5;
//                double y = player.getY();
//                double z = player.getZ() - 0.5;
//                double d3 = (float) x + rand.nextFloat();
//                double d6 = (float) z + rand.nextFloat();
//                level.addParticle(RRParticleTypes.MOONDUST.get(), d3, y + 0.025, d6, 0, 0, 0);
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
//        event.registerItem(new IClientItemExtensions() {
//            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
//                return MoonWalkersModel.INSTANCE;
//            }
//        }, new Holder[]{RRItems.MOON_WALKERS});
//    }
//
//    @Override
//    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
//        return ReverseReverse.MOD_ID + ":textures/models/armor/moon_walkers_layer_1.png";
//    }
//
//    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
//        tooltip.add(Component.translatable("item.reverse_reverse.backwards").withStyle(ChatFormatting.GRAY));
//        tooltip.add(Component.translatable("item.reverse_reverse.moon_walkers_zero_gravity").withStyle(ChatFormatting.BLUE));
//    }
}
