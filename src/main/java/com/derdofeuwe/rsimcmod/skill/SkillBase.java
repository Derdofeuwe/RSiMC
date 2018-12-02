package com.derdofeuwe.rsimcmod.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.derdofeuwe.rsimcmod.skill.guide.SkillGuide;
import com.derdofeuwe.rsimcmod.util.Colors;
import com.derdofeuwe.rsimcmod.util.Config;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class SkillBase
implements Comparable<SkillBase> {
    public static final int MAX_LEVEL = Config.skillMaxLevel;
    public static final double MAX_EXPERIENCE = Double.MAX_VALUE;
    private static HashMap<String, SkillBase> SKILLS_BY_NAME = new HashMap();
    private static List<SkillBase> SKILLS = new ArrayList<SkillBase>();
    public static final SkillBase attack = new SkillBase("Attack", Colors.PRIMARY_ATTACK, Colors.SECONDARY_ATTACK, 0, 0, 0);
    public static final SkillBase strength = new SkillBase("Strength", Colors.PRIMARY_STRENGTH, Colors.SECONDARY_STRENGTH, 0, 1, 3);
    public static final SkillBase defence = new SkillBase("Defence", Colors.PRIMARY_DEFENCE, Colors.SECONDARY_DEFENCE, 0, 2, 6);
    public static final SkillBase ranged = new SkillBase("Ranged", Colors.PRIMARY_RANGED, Colors.SECONDARY_RANGED, 1, 0, 1);
    public static final SkillBase sight = new SkillBase("Sight", Colors.PRIMARY_SIGHT, Colors.SECONDARY_SIGHT, 1, 1, 4).setLegacySkill(ranged);
    public static final SkillBase evasion = new SkillBase("Evasion", Colors.PRIMARY_EVASION, Colors.SECONDARY_EVASION, 1, 2, 7).setLegacySkill(defence);
    public static final SkillBase magic = new SkillBase("Magic", Colors.PRIMARY_MAGIC, Colors.SECONDARY_MAGIC, 2, 0, 2);
    public static final SkillBase will = new SkillBase("Will", Colors.PRIMARY_WILL, Colors.SECONDARY_WILL, 2, 1, 5).setLegacySkill(magic);
    public static final SkillBase warding = new SkillBase("Warding", Colors.PRIMARY_WARDING, Colors.SECONDARY_WARDING, 2, 2, 8).setLegacySkill(defence);
    public static final SkillBase constitution = new SkillBase("Constitution", 10, Colors.PRIMARY_CONSTITUTION, Colors.SECONDARY_CONSTITUTION, 0, 3, 9);
    public static final SkillBase prayer = new SkillBase("Prayer", Colors.PRIMARY_PRAYER, Colors.SECONDARY_PRAYER, 1, 3, 10);
    public static final SkillBase summoning = new SkillBase("Summoning", Colors.PRIMARY_SUMMONING, Colors.SECONDARY_SUMMONING, 2, 3, 11);
    public static final SkillBase mining = new SkillBase("Mining", Colors.PRIMARY_MINING, Colors.SECONDARY_MINING, 0, 4, 12);
    public static final SkillBase smithing = new SkillBase("Smithing", Colors.PRIMARY_SMITHING, Colors.SECONDARY_SMITHING, 0, 5, 15);
    public static final SkillBase hunter = new SkillBase("Hunter", Colors.PRIMARY_HUNTER, Colors.SECONDARY_HUNTER, 0, 6, 18);
    public static final SkillBase crafting = new SkillBase("Crafting", Colors.PRIMARY_CRAFTING, Colors.SECONDARY_CRAFTING, 0, 7, 21);
    public static final SkillBase woodcutting = new SkillBase("Woodcutting", Colors.PRIMARY_WOODCUTTING, Colors.SECONDARY_WOODCUTTING, 1, 4, 13);
    public static final SkillBase firemaking = new SkillBase("Firemaking", Colors.PRIMARY_FIREMAKING, Colors.SECONDARY_FIREMAKING, 1, 5, 16);
    public static final SkillBase fletching = new SkillBase("Fletching", Colors.PRIMARY_FLETCHING, Colors.SECONDARY_FLETCHING, 1, 6, 19);
    public static final SkillBase construction = new SkillBase("Construction", Colors.PRIMARY_CONSTRUCTION, Colors.SECONDARY_CONSTRUCTION, 1, 7, 22);
    public static final SkillBase farming = new SkillBase("Farming", Colors.PRIMARY_FARMING, Colors.SECONDARY_FARMING, 2, 4, 14);
    public static final SkillBase herblore = new SkillBase("Herblore", Colors.PRIMARY_HERBLORE, Colors.SECONDARY_HERBLORE, 2, 5, 17);
    public static final SkillBase fishing = new SkillBase("Fishing", Colors.PRIMARY_FISHING, Colors.SECONDARY_FISHING, 2, 6, 20);
    public static final SkillBase cooking = new SkillBase("Cooking", Colors.PRIMARY_COOKING, Colors.SECONDARY_COOKING, 2, 7, 23);
    public static final SkillBase slayer = new SkillBase("Slayer", Colors.PRIMARY_SLAYER, Colors.SECONDARY_SLAYER, 0, 8, 24);
    public static final SkillBase agility = new SkillBase("Agility", Colors.PRIMARY_AGILITY, Colors.SECONDARY_AGILITY, 1, 8, 25);
    public static final SkillBase runecrafting = new SkillBase("Runecrafting", Colors.PRIMARY_RUNECRAFTING, Colors.SECONDARY_RUNECRAFTING, 2, 8, 26);
    public static final SkillBase thieving = new SkillBase("Thieving", Colors.PRIMARY_THIEVING, Colors.SECONDARY_THIEVING, 1, 9, 27);
    private final String skillName;
    private final String unlocalizedName;
    private final int primaryColor;
    private final int secondaryColor;
    private final int startingLevel;
    private final int xCoord;
    private final int yCoord;
    private final int statIndex;
    private final ResourceLocation skillIcon;
    private final SkillGuide skillGuide;
    private SkillBase legacySkill;

    public static SkillBase getSkillByName(String name) {
        return SKILLS_BY_NAME.get(name.toLowerCase());
    }

    public static List<SkillBase> getSkills() {
        return SKILLS;
    }

    public static int getMaxTotal() {
        return MAX_LEVEL * SkillBase.getSkills().size();
    }

    public SkillBase(String name, int startingLevel, int primaryColor, int secondaryColor, SkillGuide guide, int x, int y, int stat) {
        if (SKILLS_BY_NAME.containsKey(name.toLowerCase())) {
            throw new IllegalArgumentException(name + " already exists as a skill!");
        }
        this.skillName = name;
        this.unlocalizedName = "skill." + name.toLowerCase() + ".name";
        this.startingLevel = startingLevel;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.xCoord = x;
        this.yCoord = y;
        this.statIndex = stat;
        this.skillIcon = new ResourceLocation("rsmc", "textures/gui/skillIcons/" + this.skillName + ".png");
        this.skillGuide = guide;
        SKILLS_BY_NAME.put(name.toLowerCase(), this);
        SKILLS.add(this);
    }

    public SkillBase(String name, int startingLevel, int primaryColor, int secondaryColor, int x, int y, int stat) {
        this(name, startingLevel, primaryColor, secondaryColor, new SkillGuide(), x, y, stat);
    }

    public SkillBase(String name, int primary, int secondary, int x, int y, int stat) {
        this(name, 1, primary, secondary, x, y, stat);
    }

    public boolean hasLegacySkill() {
        return this.legacySkill != null;
    }

    public SkillBase setLegacySkill(SkillBase skill) {
        this.legacySkill = skill;
        return this;
    }

    public SkillBase getLegacySkill() {
        return this.legacySkill;
    }

    private String getLocalizedName() {
        return LanguageRegistry.instance().getStringLocalization(this.unlocalizedName);
    }

    public String getDisplayName() {
        return this.getLocalizedName();
    }

    public ResourceLocation getSkillIcon() {
        return this.skillIcon;
    }

    public String getUnlocalizedName() {
        return this.getLocalizedName();
    }

    public String getName() {
        return this.skillName;
    }

    public Side getSide() {
        return FMLCommonHandler.instance().getEffectiveSide();
    }

    public int getStartingLevel() {
        return this.startingLevel;
    }

    public int getPrimaryColor() {
        return this.primaryColor;
    }

    public int getSecondaryColor() {
        return this.secondaryColor;
    }

    public int getCoordX() {
        return this.xCoord;
    }

    public int getCoordY() {
        return this.yCoord;
    }

    public int getStatIndex() {
        return this.statIndex;
    }

    public SkillGuide getSkillGuide() {
        return this.skillGuide;
    }

    @Override
    public int compareTo(@NotNull SkillBase o) {
        return Integer.compare(this.statIndex, o.statIndex);
    }

    public static ItemStack makeFirework(byte twinkle, byte trail, byte type, byte flight, int[] colors) {
        ItemStack rocket = new ItemStack((Item)GameData.getItemRegistry().func_82594_a("fireworks"));
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound nbttagcompound2 = new NBTTagCompound();
        nbttagcompound2.func_74774_a("Flicker", twinkle);
        nbttagcompound2.func_74774_a("Trail", trail);
        nbttagcompound2.func_74774_a("Type", type);
        nbttagcompound2.func_74783_a("Colors", colors);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound2);
        nbttagcompound1.func_74782_a("Explosions", (NBTBase)nbttaglist);
        nbttagcompound1.func_74774_a("Flight", flight);
        nbttagcompound.func_74782_a("Fireworks", (NBTBase)nbttagcompound1);
        rocket.func_77982_d(nbttagcompound);
        return rocket;
    }
}


