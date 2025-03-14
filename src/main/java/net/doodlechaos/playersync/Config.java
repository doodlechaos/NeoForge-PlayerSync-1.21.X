package net.doodlechaos.playersync;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config
{
    // These fields hold the configuration values and their specification.
    public static final Config CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    // Example config value: a welcome message
    public final ModConfigSpec.ConfigValue<String> inputAudioPathOgg;
    public final ModConfigSpec.ConfigValue<String> pathToFFMPEG;
    public final ModConfigSpec.ConfigValue<String> outputVideoPath;


    // The constructor takes in a builder to define the config entries.
    private Config(ModConfigSpec.Builder builder) {
        // Define a property with a comment and translation key.
        inputAudioPathOgg = builder
                .comment("Path for the input audio file")
                .translation("playersync.inputAudioOgg")  // Custom translation key without category prefix.
                .define("inputAudioOgg", "C:\\Users\\marky\\Downloads\\mainThemeRemix.ogg");

        pathToFFMPEG = builder
                .comment("Path to ffmpeg.exe")
                .translation("playersync.pathToFFMPEG")
                .define("pathToFFMPEG", "C:\\FFmpeg\\bin\\ffmpeg.exe");
        outputVideoPath = builder
                .comment("Output Video Path")
                .translation("playersync.outputVideoPath")
                .define("outputVideoPath", "C:\\Users\\marky\\Downloads\\testRenderPlayerSync.mp4");
    }

    // Use a static block to build both the config instance and its spec.
    static {
        // The builder's configure method returns a pair containing the config instance and spec.
        // Replace Pair.getLeft()/getRight() with the appropriate methods if your Pair implementation differs.
        Pair<Config, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(Config::new);
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }
}
