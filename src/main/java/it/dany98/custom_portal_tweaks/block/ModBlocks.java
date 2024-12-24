package it.dany98.custom_portal_tweaks.block;

import it.dany98.custom_portal_tweaks.CustomPortalTweaks;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CustomPortalTweaks.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CustomPortalTweaks.MOD_ID);

    public static final List<RegistryObject<CustomPortalBlock>> PORTAL = createPortalBlock();

    public static ArrayList<RegistryObject<CustomPortalBlock>> createPortalBlock() {
        ArrayList<RegistryObject<CustomPortalBlock>> portals = new ArrayList<>();
        for (int i=0; i<20; i++) {
            portals.add(registerBlock("portal_block"+i,
                    () -> new CustomPortalBlock(Block.Properties.copy(Blocks.NETHER_PORTAL).noCollission()
                            .strength(-1).sound(SoundType.GLASS).lightLevel(state -> 11))
            ));
        }
        return portals;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
