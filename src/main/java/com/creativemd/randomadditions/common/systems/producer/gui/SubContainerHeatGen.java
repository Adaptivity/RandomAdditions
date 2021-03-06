package com.creativemd.randomadditions.common.systems.producer.gui;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

import com.creativemd.creativecore.common.container.SubContainer;
import com.creativemd.randomadditions.common.subsystem.SubContainerTileEntity;
import com.creativemd.randomadditions.common.subsystem.TileEntityRandom;
import com.creativemd.randomadditions.common.systems.producer.tileentity.TileEntityHeatGenerator;

public class SubContainerHeatGen extends SubContainerTileEntity{

	public SubContainerHeatGen(TileEntityRandom tileEntity) {
		super(tileEntity);
	}

	@Override
	public void onGuiPacket(int control, String value, EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Slot> getSlots(EntityPlayer player) {
		ArrayList<Slot> slots = new ArrayList<Slot>();
		if(tileEntity instanceof TileEntityHeatGenerator)
		{
			for (int i = 0; i < ((TileEntityHeatGenerator)tileEntity).inventory.length; i++) {
				slots.add(new Slot((IInventory) tileEntity, i, 50+i*18, 20));
			}
		}
		slots.addAll(getPlayerSlots(player, 8, 84));
		return slots;
	}

	@Override
	public boolean doesGuiNeedUpdate() {
		return true;
	}

}
