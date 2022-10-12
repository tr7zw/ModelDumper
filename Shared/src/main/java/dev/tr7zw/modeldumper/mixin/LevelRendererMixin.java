package dev.tr7zw.modeldumper.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.tr7zw.modeldumper.ModelDumperModBase;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {

    @Shadow
    private EntityRenderDispatcher entityRenderDispatcher;
    
    @Inject(method = "renderEntity", at = @At("HEAD"))
    private void renderEntity(Entity arg, double d, double e, double g, float h, PoseStack arg2,
            MultiBufferSource replaced, CallbackInfo ci) {
        MultiBufferSource source = ModelDumperModBase.instance.getModelConsumer();
        if(source != null && !ModelDumperModBase.instance.dumpedEntities.contains(arg)) {
            ModelDumperModBase.instance.dumpedEntities.add(arg);
            double d0 = Mth.lerp(h, arg.xOld, arg.getX());
            double d1 = Mth.lerp(h, arg.yOld, arg.getY());
            double d2 = Mth.lerp(h, arg.zOld, arg.getZ());
            float f = Mth.lerp(h, arg.yRotO, arg.getYRot());
            this.entityRenderDispatcher.render(arg, d0 - d, d1 - e, d2 - g, f, h, arg2, source,
                    this.entityRenderDispatcher.getPackedLightCoords(arg, h));
        }
    }

    
}
