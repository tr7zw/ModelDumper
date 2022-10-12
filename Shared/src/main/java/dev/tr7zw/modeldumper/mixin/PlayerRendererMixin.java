package dev.tr7zw.modeldumper.mixin;

import java.io.File;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.tr7zw.modeldumper.ModelDumperModBase;
import dev.tr7zw.modeldumper.exporter.MultiBufferObjConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin
        extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    public PlayerRendererMixin(Context context, PlayerModel<AbstractClientPlayer> entityModel, float f) {
        super(context, entityModel, f);
    }
    
    private static final File dumpFolder = new File("ModelDumps");
    
    @Inject(method = "render", at = @At("RETURN"))
    public void render(AbstractClientPlayer abstractClientPlayer, float f, float g, PoseStack poseStack,
            MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        if(ModelDumperModBase.instance.dumpPlayers && !ModelDumperModBase.instance.dumpedEntities.contains(abstractClientPlayer)) {
            ModelDumperModBase.instance.dumpedEntities.add(abstractClientPlayer);
            File outFile = new File(dumpFolder, abstractClientPlayer.getName().getString() + "-" + System.currentTimeMillis());
            outFile.mkdirs();
            MultiBufferObjConsumer consumer = new MultiBufferObjConsumer(outFile);
            super.render(abstractClientPlayer, f, g, new PoseStack(), consumer, i);
            consumer.close();
            LocalPlayer player = Minecraft.getInstance().player;
            player.sendSystemMessage(Component.literal("Dumped " + abstractClientPlayer.getName().getString()).withStyle(ChatFormatting.YELLOW));
        }
    }

}
