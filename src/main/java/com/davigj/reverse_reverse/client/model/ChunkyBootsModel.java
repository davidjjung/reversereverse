package com.davigj.reverse_reverse.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamabnormals.caverns_and_chasms.client.model.CopperArmorModel;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChunkyBootsModel<T extends LivingEntity> extends HumanoidArmorModel<T> {
    private final ModelPart RightLeg;
    	private final ModelPart LeftLeg;
    public static final ChunkyBootsModel<?> INSTANCE;

    public ChunkyBootsModel(ModelPart part) {
        super(part);
        this.RightLeg = part.getChild("right_leg");
		this.LeftLeg = part.getChild("left_leg");
    }

    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = meshdefinition.getRoot();

        root.getChild("right_leg").addOrReplaceChild("right_vamp", CubeListBuilder.create().texOffs(0, 27).addBox(-3.0F, 10.0F, -5.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        root.getChild("left_leg").addOrReplaceChild("left_vamp", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)).mirror(false)
                .texOffs(0, 27).mirror().addBox(-3.0F, 10.0F, -5.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

        return meshdefinition;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        RightLeg.render(poseStack, buffer, packedLight, packedOverlay, color);
        LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, color);
    }

    public static LayerDefinition createLayerDefinition(CubeDeformation deformation) {
        return LayerDefinition.create(createBodyLayer(deformation), 64, 32);
    }

    static {
        INSTANCE = new ChunkyBootsModel<>(createLayerDefinition(LayerDefinitions.OUTER_ARMOR_DEFORMATION).bakeRoot());
    }
}