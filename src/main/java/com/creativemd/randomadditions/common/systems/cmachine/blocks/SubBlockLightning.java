package com.creativemd.randomadditions.common.systems.cmachine.blocks;

import ic2.core.util.Vector3;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.creativemd.creativecore.client.rendering.RenderHelper3D;
import com.creativemd.creativecore.common.utils.CubeObject;
import com.creativemd.creativecore.common.utils.RotationUtils;
import com.creativemd.randomadditions.common.subsystem.SubBlockSystem;
import com.creativemd.randomadditions.common.subsystem.SubContainerTileEntity;
import com.creativemd.randomadditions.common.subsystem.SubGuiTileEntity;
import com.creativemd.randomadditions.common.subsystem.TileEntityRandom;
import com.creativemd.randomadditions.common.systems.cmachine.SubBlockCMachine;
import com.creativemd.randomadditions.common.systems.cmachine.tileentity.TileEntityLightning;

import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SubBlockLightning extends SubBlockCMachine{

	public SubBlockLightning(SubBlockSystem system) {
		super("Lightning", system);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public SubGuiTileEntity getGui(TileEntity tileEntity) {
		return null;
	}

	@Override
	public SubContainerTileEntity getContainer(TileEntity tileEntity) {
		return null;
	}

	@Override
	public TileEntityRandom getTileEntity() {
		return new TileEntityLightning();
	}
	
	@Override
	public ArrayList<CubeObject> getCubes(ItemStack stack, IBlockAccess world, int x, int y, int z)
	{
		ArrayList<CubeObject> cubes = new ArrayList<CubeObject>();
		cubes.add(new CubeObject(1, 0.2, 0.2, 0.85, 0.8, 0.8, Blocks.iron_block));
		cubes.add(new CubeObject(0.85, 0.4, 0.4, 0.05, 0.6, 0.6, Blocks.stone));
		int amount = 3;
		double size = 0.7D/amount;
		for (int i = 0; i < amount; i++) {
			cubes.add(new CubeObject(i*size+size/2+0.05, 0.3, 0.3, i*size+size/2+0.15, 0.7, 0.7, Blocks.planks));
		}
		if(stack != null)
		{
			for (int i = 0; i < cubes.size(); i++) {
				cubes.set(i, CubeObject.rotateCube(cubes.get(i), ForgeDirection.DOWN));
			}
		}
		return cubes;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void drawRender(TileEntity entity, double x, double y, double z)
	{
		if(((TileEntityLightning) entity).getCurrentPower() > ((TileEntityLightning) entity).usePerHit)
		{
			Entity living = ((TileEntityLightning) entity).getEntityInRange();
			if(living != null)
			{
				Vec3 start = Vec3.createVectorHelper(0.5, 0.5, 0.5);
				Vec3 end = Vec3.createVectorHelper(living.posX-entity.xCoord+0.5, living.posY-entity.yCoord+0.5, living.posZ-entity.zCoord+0.5);
				RenderHelper3D.renderLightning(x, y, z, start, end, 0.6, 8, 2);
			}
		}
	}
	
	@Override
	public boolean useSideForRotation()
	{
		return true;
	}
	
	@Override
	public int getRotation()
	{
		return 2;
	}
}
