package dev.tr7zw.modeldumper;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.tr7zw.modeldumper.exporter.MultiBufferObjConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public abstract class ModelDumperModBase {

    public static ModelDumperModBase instance;
    public static final Logger LOGGER = LogManager.getLogger();
    private static final File dumpFolder = new File("ModelDumps");
    protected KeyMapping keybindPlayer = new KeyMapping("key.modeldumper.dumpplayers", -1, "Model Dumper");
    protected KeyMapping keybindEntities = new KeyMapping("key.modeldumper.dumpentities", -1, "Model Dumper");
    protected boolean pressedPlayer = false;
    protected boolean pressedEntities = false;
    public boolean dumpPlayers = false;
    public boolean dumpEntities = false;
    public Set<Entity> dumpedEntities = new HashSet<>();
    private MultiBufferObjConsumer modelConsumer;

    public void onInitialize() {
        instance = this;
        initModloader();
    }

    
    @SuppressWarnings("resource")
    public void clientTick() {
        if(pressedEntities) {
            modelConsumer.close();
            modelConsumer = null;
            LocalPlayer player = Minecraft.getInstance().player;
            player.sendSystemMessage(Component.literal("Finished! Dumped " + dumpedEntities.size() + " entities!").withStyle(ChatFormatting.GREEN));
            dumpedEntities.clear();
        }
        if(dumpPlayers) {
            LocalPlayer player = Minecraft.getInstance().player;
            player.sendSystemMessage(Component.literal("Finished! Dumped " + dumpedEntities.size() + " players!").withStyle(ChatFormatting.GREEN));
            dumpedEntities.clear();
        }
        dumpPlayers = false;
        pressedEntities = false;
        modelConsumer = null;
        if (keybindPlayer.isDown()) {
            if (pressedPlayer)
                return;
            pressedPlayer = true;
            dumpPlayers = true;
            LocalPlayer player = Minecraft.getInstance().player;
            player.sendSystemMessage(Component.literal("Dumping all rendered player models...").withStyle(ChatFormatting.GREEN));
        } else {
            pressedPlayer = false;
        }
        if(keybindEntities.isDown()) {
            if(pressedEntities) {
                return;
            }
            LocalPlayer player = Minecraft.getInstance().player;
            dumpEntities = true;
            pressedEntities = true;
            File outFile = new File(dumpFolder, "Entitydump-" + System.currentTimeMillis());
            outFile.mkdirs();
            modelConsumer = new MultiBufferObjConsumer(outFile);
            player.sendSystemMessage(Component.literal("Dumping all rendered entity models...").withStyle(ChatFormatting.GREEN));
        }else {
            dumpEntities = false;
            pressedEntities = false;
        }
    }
    
    public abstract void initModloader();

    public MultiBufferSource getModelConsumer() {
        return modelConsumer;
    }
    
}
