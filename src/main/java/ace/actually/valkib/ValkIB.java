package ace.actually.valkib;

import ace.actually.valkib.blocks.IceBreakerBlock;
import ace.actually.valkib.blocks.IceBreakerBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ValkIB implements ModInitializer {


    public static final IceBreakerBlock ICE_BREAKER_BLOCK = new IceBreakerBlock(AbstractBlock.Settings.create());
    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, Identifier.of("valkib","ice_breaker"),ICE_BREAKER_BLOCK);
        Registry.register(Registries.ITEM,Identifier.of("valkib","ice_breaker"),new BlockItem(ICE_BREAKER_BLOCK,new Item.Settings()));
    }

    public static BlockEntityType<IceBreakerBlockEntity> ICE_BREAKER_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier("valkib", "ice_breaker_block_entity"),
            FabricBlockEntityTypeBuilder.create(IceBreakerBlockEntity::new, ICE_BREAKER_BLOCK).build()
    );
}
