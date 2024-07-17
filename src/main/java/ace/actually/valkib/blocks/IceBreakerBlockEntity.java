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
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        for (int k = -1; k < 2; k++) {
                            if(serverWorld.getBlockState(block.add(i,j,k)).isIn(BlockTags.ICE))
                            {
                                serverWorld.breakBlock(block.add(i,j,k),true);
                                serverWorld.setBlockState(block.add(i,j,k),Blocks.WATER.getDefaultState());
                            }
                        }
                    }
                }
            }
        }

    }
}
