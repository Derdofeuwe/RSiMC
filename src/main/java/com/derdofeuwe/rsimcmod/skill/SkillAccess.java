package com.derdofeuwe.rsimcmod.skill;

import net.minecraft.nbt.NBTTagCompound;

public interface SkillAccess {
    public SkillBase getBase();

    public int getLevelCurrent();

    public int getLevelEffective();

    public double getTotalExp();

    public double getLevelExp();

    public double getExpProgress();

    default public NBTTagCompound toNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("currentLevel", this.getLevelCurrent());
        nbt.setInteger("effectiveLevel", this.getLevelEffective());
        nbt.setDouble("totalXP", this.getTotalExp());
        return nbt;
    }
}
