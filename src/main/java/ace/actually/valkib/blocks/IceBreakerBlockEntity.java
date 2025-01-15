package ace.actually.valkib.blocks;

import ace.actually.valkib.ValkIB;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;

public class IceBreakerBlockEntity extends BlockEntity {
    public IceBreakerBlockEntity(BlockPos pos, BlockState state) {
        super(ValkIB.ICE_BREAKER_BLOCK_ENTITY, pos, state);
    }
    public static void tick(World world, BlockPos pos, BlockState state, IceBreakerBlockEntity be)
    {
        if(world instanceof ServerWorld serverWorld)
        {
            if(VSGameUtilsKt.isBlockInShipyard(serverWorld,pos))
            {
                ServerShip ship = VSGameUtilsKt.getShipManagingPos(serverWorld,pos);
                Vec3d middle = VSGameUtilsKt.toWorldCoordinates(ship, Vec3d.of(pos));
                BlockPos block = new BlockPos((int) middle.x, (int) middle.y, (int) middle.z);
                int radius = 6;
                int diameter = radius*2;
                for (int i = 0; i < diameter; i++) {
                    for (int j = 0; j < diameter; j++) {
                        for (int k = 0; k < diameter; k++) {

                            if( ( (i-radius)*(i-radius) + (j-radius)*(j-radius) ) < (radius*radius) ){
                                if(serverWorld.getBlockState(block.add((i-radius),(k-radius),(j-radius))).isOf(Blocks.ICE))
                                {
                                    serverWorld.breakBlock(block.add((i-radius),(k-radius),(j-radius)),true);
                                    serverWorld.setBlockState(block.add((i-radius),(k-radius),(j-radius)),Blocks.WATER.getDefaultState());
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
