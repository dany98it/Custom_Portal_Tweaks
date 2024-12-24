package it.dany98.custom_portal_tweaks.datagen;

import it.dany98.custom_portal_tweaks.CustomPortalTweaks;
import it.dany98.custom_portal_tweaks.block.ModBlocks;
import net.kyrptonaught.customportalapi.CustomPortalBlock;
import net.minecraft.data.PackOutput;

import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CustomPortalTweaks.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (RegistryObject<CustomPortalBlock> block: ModBlocks.PORTAL) {
            blockWithItem(block);
        }
    }

    private void blockWithItem(RegistryObject<CustomPortalBlock> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(Blocks.NETHER_PORTAL));
    }
}
