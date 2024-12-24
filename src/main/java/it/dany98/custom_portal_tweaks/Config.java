package it.dany98.custom_portal_tweaks;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.ArrayList;
import java.util.List;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = CustomPortalTweaks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> PORTALS = BUILDER
            .comment("Create a custom portal max 20")
            .defineListAllowEmpty(
                    "portals",
                    List.of(
                            new Portal(
                                    "minecraft:stone",
                                    "minecraft:diamond",
                                    "minecraft:the_nether").toJsonSting()
                    ),
                    Config::validatePortal
            );

    private static boolean validatePortal(final Object obj) {
        return obj instanceof String;
    }

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static List<Portal> portals;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        // convert the list of strings into a set of items
        portals = new ArrayList<>();
        for (String json :PORTALS.get()) {
            portals.add(Portal.fromJsonString(json));
        }
    }
}
