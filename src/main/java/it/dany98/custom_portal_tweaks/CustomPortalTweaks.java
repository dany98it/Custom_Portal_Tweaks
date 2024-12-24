package it.dany98.custom_portal_tweaks;

import com.mojang.logging.LogUtils;
import it.dany98.custom_portal_tweaks.block.ModBlocks;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Iterator;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CustomPortalTweaks.MOD_ID)
public class CustomPortalTweaks {
    public static final String MOD_ID = "custom_portal_tweaks";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CustomPortalTweaks() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Iterator<Portal> portalIterator= Config.portals.iterator();
        Iterator<RegistryObject<CustomPortalBlock>> customPortalsBlock= ModBlocks.PORTAL.iterator();

        while (portalIterator.hasNext()) {
            if(!customPortalsBlock.hasNext()) {
                break;
            }
            Portal portal = portalIterator.next();
            Item igniter = ForgeRegistries.ITEMS.getValue(new ResourceLocation(portal.getItemIgniter()));
            CustomPortalBuilder.beginPortal()
                    .frameBlock(new ResourceLocation(portal.getFrameBlock()))
                    .customPortalBlock(customPortalsBlock.next().get())
                    .lightWithItem(igniter)
                    .destDimID(new ResourceLocation(portal.getDimension()))
                    .registerPortal();
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
