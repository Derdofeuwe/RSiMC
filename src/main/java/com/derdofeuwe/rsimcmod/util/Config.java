package com.derdofeuwe.rsimcmod.util;

import com.derdofeuwe.rsimcmod.RSiMCMain;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Config {
    public static boolean debug;
    public static int scriptReloadTicks;
    public static boolean perWorldStats;
    public static boolean starterItems;
    public static double experienceScale;
    public static boolean enableSkills;
    public static boolean replaceHungerWithStamina;
    public static boolean disableCrafting;
    public static int skillMaxLevel;
    public static int bankPageLevel;
    public static int bankPageStart;
    public static boolean killableVillagers;
    public static boolean jokeMode;
    public static boolean magicTreeSparkle;
    public static boolean injectItems;
    public static boolean breakableOres;
    public static boolean pickupOres;
    public static int saplingReplantMode;
    public static double saplingDropChance;
    public static int itemHotSwapMode;
    public static boolean breakableSmelters;
    public static boolean breakableAnvils;
    public static boolean breakableCrucibles;
    public static boolean breakableCompostBins;
    public static boolean breakableCharmers;
    public static final int HOT_SWAP_OFF = 0;
    public static final int HOT_SWAP_BEFORE = 1;
    public static final int HOT_SWAP_BREAK = 2;
    public static int oreTimerEssence;
    public static int oreTimerCopper;
    public static int oreTimerTin;
    public static int oreTimerIron;
    public static int oreTimerSilver;
    public static int oreTimerCoal;
    public static int oreTimerGem;
    public static int oreTimerGold;
    public static int oreTimerMithril;
    public static int oreTimerAdamantite;
    public static int oreTimerRunite;
    public static int oreTimerBarrows;
    public static float logBurnModifier;
    public static int flaxPerChunk;
    public static int flaxPerGroup;
    public static boolean disableVanilaOreGen;
    public static boolean disableVanillaTreeGen;
    public static boolean doOreGeneration;
    public static boolean doTreeGeneration;
    public static boolean doEssenceGeneration;
    public static boolean doAltarGeneration;
    public static boolean doWildFlaxGeneration;
    public static boolean doBushGeneration;
    public static boolean doFruitGeneration;
    public static boolean doHerbGeneration;
    public static boolean doCropGeneration;
    public static String oreBiomeMatchEssence;
    public static int oreMaxEssence;
    public static int oreMinEssence;
    public static int oreMaxhEssence;
    public static int oreMinhEssence;
    public static int oreSpawnEssence;
    public static int oreMaxCopper;
    public static int oreMaxTin;
    public static int oreMaxIron;
    public static int oreMaxSilver;
    public static int oreMaxCoal;
    public static int oreMaxGem;
    public static int oreMaxGold;
    public static int oreMaxMithril;
    public static int oreMaxAdamant;
    public static int oreMaxRune;
    public static int oreMaxBarrows;
    public static int oreMinCopper;
    public static int oreMinTin;
    public static int oreMinIron;
    public static int oreMinSilver;
    public static int oreMinCoal;
    public static int oreMinGem;
    public static int oreMinGold;
    public static int oreMinMithril;
    public static int oreMinAdamant;
    public static int oreMinRune;
    public static int oreMinBarrows;
    public static int oreSpawnCopper;
    public static int oreSpawnTin;
    public static int oreSpawnIron;
    public static int oreSpawnSilver;
    public static int oreSpawnCoal;
    public static int oreSpawnGem;
    public static int oreSpawnGold;
    public static int oreSpawnMithril;
    public static int oreSpawnAdamant;
    public static int oreSpawnRune;
    public static int oreSpawnBarrows;
    public static int oreSizeCopper;
    public static int oreSizeTin;
    public static int oreSizeIron;
    public static int oreSizeSilver;
    public static int oreSizeCoal;
    public static int oreSizeGem;
    public static int oreSizeGold;
    public static int oreSizeMithril;
    public static int oreSizeAdamant;
    public static int oreSizeRune;
    public static int oreSizeBarrows;

    public static void init() {
        if (RSiMCMain.config == null) {
            RSiMCMain.config = new Configuration(new File(RSiMCMain.dataDir, "main.cfg"));
            Config.loadConfiguration();
            RSiMCMain.logger.info("Config loaded", new Object[0]);
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals("RSiMCMain")) {
            Config.loadConfiguration();
            RSiMCMain.logger.info("Config reloaded", new Object[0]);
        }
    }

    public static void loadConfiguration() {
        debug = RSiMCMain.config.getBoolean("Debug", "General Settings", true, "Adds extra logging and other debugging goodies");
        perWorldStats = RSiMCMain.config.getBoolean("Per World Stats", "General Settings", true, "Whether or not RSiMCMain skills and stats are per world (recommended for SSP)");
        enableSkills = RSiMCMain.config.getBoolean("Enable Skills", "General Settings", true, "Setting this to false will assume the player has 99 everything");
        experienceScale = RSiMCMain.config.getFloat("Experience Scale", "General Settings", 1.0f, 0.5f, 10.0f, "Changing this will boost (> 1.0) or hinder (< 1.0) or halt (= 0) exp rates");
        replaceHungerWithStamina = RSiMCMain.config.getBoolean("Replace Hunger with stamina", "General Settings", true, "Replaces Minecraft Hunger with stamina [recommended]");
        disableCrafting = RSiMCMain.config.getBoolean("Disable Vanilla Crafting", "General Settings", true, "Disables certain Vanilla recipes, replacing most with RSiMCMain ones [recommended]");
        skillMaxLevel = RSiMCMain.config.getInt("Skill Max Level", "General Settings", 200, 1, 500, "The max level that skills can be leveled to");
        scriptReloadTicks = RSiMCMain.config.getInt("Spell Reload Ticks", "General Settings", 600, 20, 6000, "The number of ticks between script reloads");
        starterItems = RSiMCMain.config.getBoolean("Starter Items", "General Settings", true, "Setting this to false will not give items to a new player when they join for the first time");
        bankPageLevel = Math.max(1, RSiMCMain.config.get("General Settings", "Bank Page Levels", 50, "The number of levels needed to unlock a new bank page").getInt());
        bankPageStart = RSiMCMain.config.get("General Settings", "Bank Starting Pages", 4, "The number of bank pages players start with").getInt();
        killableVillagers = RSiMCMain.config.getBoolean("Killable Villagers", "General Settings", false, "Setting this to true will allow villagers to be killed outside of creative mode");
        jokeMode = RSiMCMain.config.getBoolean("Joke Mode", "Optional Client Settings", false, "18+ Recommended!");
        magicTreeSparkle = RSiMCMain.config.getBoolean("Magic Tree Sparkles", "Optional Client Settings", true, "This will affect magic logs, blockPlanks, and blockLeaves");
        injectItems = RSiMCMain.config.getBoolean("Inject Items", "How Items and Blocks Act", true, "Setting this to false will drop items when you break a block, true will inject them into a player's inventory");
        breakableOres = RSiMCMain.config.getBoolean("Breakable Ores", "How Items and Blocks Act", true, "Setting this to false will prevent players from breaking ore generator blocks");
        pickupOres = RSiMCMain.config.getBoolean("Allow Ore Extraction", "How Items and Blocks Act", true, "Setting this to false will prevent players from being able to pick up and move ore blocks");
        saplingReplantMode = RSiMCMain.config.getInt("Tree Replant Mode", "How Items and Blocks Act", 2, 0, 3, "0 for none, 1 for blockLeaves replanting, 2 for blockLog replanting, 3 for blockLog regenerating");
        saplingDropChance = RSiMCMain.config.getFloat("Sapling Drop Chance", "How Items and Blocks Act", 0.1f, 0.0f, 1.0f, "Percentage chance for blockLeaves to drop saplings");
        itemHotSwapMode = RSiMCMain.config.getInt("Item Hot Swap", "How Items and Blocks Act", 1, 0, 2, "Tool and armor behavior. 0 for off, 1 for swap just before breaking, 2 for swap when breaking");
        breakableSmelters = RSiMCMain.config.getBoolean("Breakable Smelters", "How Items and Blocks Act", true, "Whether or not smelters can be broken/crafted");
        breakableAnvils = RSiMCMain.config.getBoolean("Breakable Anvils", "How Items and Blocks Act", true, "Whether or not anvils can be broken/crafted");
        breakableCrucibles = RSiMCMain.config.getBoolean("Breakable Crucibles", "How Items and Blocks Act", true, "Whether or not crucibles can be broken/crafted");
        breakableCompostBins = RSiMCMain.config.getBoolean("Breakable Compost Bins", "How Items and Blocks Act", true, "Whether or not compost bins can be broken/crafted");
        breakableCharmers = RSiMCMain.config.getBoolean("Breakable Charming Tables", "How Items and Blocks Act", true, "Whether or not charming tables can be broken/crafted");
        oreTimerEssence = RSiMCMain.config.getInt("Essence", "Ore Timers (in seconds)", 1, 1, 100000, "");
        oreTimerCopper = RSiMCMain.config.getInt("Copper", "Ore Timers (in seconds)", 4, 1, 100000, "");
        oreTimerTin = RSiMCMain.config.getInt("Tin", "Ore Timers (in seconds)", 4, 1, 100000, "");
        oreTimerIron = RSiMCMain.config.getInt("Iron", "Ore Timers (in seconds)", 10, 1, 100000, "");
        oreTimerSilver = RSiMCMain.config.getInt("Silver", "Ore Timers (in seconds)", 120, 1, 100000, "");
        oreTimerCoal = RSiMCMain.config.getInt("Coal", "Ore Timers (in seconds)", 60, 1, 100000, "");
        oreTimerGem = RSiMCMain.config.getInt("Gem", "Ore Timers (in seconds)", 60, 1, 100000, "");
        oreTimerGold = RSiMCMain.config.getInt("Gold", "Ore Timers (in seconds)", 120, 1, 100000, "");
        oreTimerMithril = RSiMCMain.config.getInt("Mithril", "Ore Timers (in seconds)", 240, 1, 100000, "");
        oreTimerAdamantite = RSiMCMain.config.getInt("Adamantite", "Ore Timers (in seconds)", 480, 1, 100000, "");
        oreTimerRunite = RSiMCMain.config.getInt("Runite", "Ore Timers (in seconds)", 1500, 1, 100000, "");
        oreTimerBarrows = RSiMCMain.config.getInt("Dragonite", "Ore Timers (in seconds)", 3600, 1, 100000, "");
        logBurnModifier = RSiMCMain.config.getFloat("Log Burn Modifier", "Modifiers for Various Mechanics", 1.0f, 0.01f, 10.0f, "");
        flaxPerChunk = RSiMCMain.config.getInt("Flax per chunk", "General Settings for World Gen", 1, 0, 100, "Weighted vaule of flax per chunk");
        flaxPerGroup = RSiMCMain.config.getInt("Flax per group", "General Settings for World Gen", 16, 0, 100, "Number of flax plants in a spawn");
        disableVanilaOreGen = RSiMCMain.config.getBoolean("Disable Vanilla Ore Gen", "General Settings for World Gen", true, "Turns off generation of vanilla ores (does not turn off lapis, redstone, or quartz) [recommended]");
        disableVanillaTreeGen = RSiMCMain.config.getBoolean("Disable Vanilla Tree Gen", "General Settings for World Gen", true, "Turns off generation of vanilla trees [recommended]");
        doOreGeneration = RSiMCMain.config.getBoolean("Do Ore Generation", "General Settings for World Gen", true, "Whether or not to generate RSiMCMain ores");
        doTreeGeneration = RSiMCMain.config.getBoolean("Do Tree Generation", "General Settings for World Gen", true, "Whether or not to generate RSiMCMain trees");
        doAltarGeneration = RSiMCMain.config.getBoolean("Do Altar Generation", "General Settings for World Gen", true, "Whether or not to generate RSiMCMain altars");
        doEssenceGeneration = RSiMCMain.config.getBoolean("Do Essence Generation", "General Settings for World Gen", true, "Whether or not to generate Rune Essence");
        doWildFlaxGeneration = RSiMCMain.config.getBoolean("Do Flax Generation", "General Settings for World Gen", true, "Whether or not to generate Flax");
        doBushGeneration = RSiMCMain.config.getBoolean("Do Bush Generation", "General Settings for World Gen", true, "Whether or not to generate wild Bushes");
        doCropGeneration = RSiMCMain.config.getBoolean("Do Crop Generation", "General Settings for World Gen", true, "Whether or not to generate wild Crops");
        doHerbGeneration = RSiMCMain.config.getBoolean("Do Herb Generation", "General Settings for World Gen", true, "Whether or not to generate wild Herbs");
        doFruitGeneration = RSiMCMain.config.getBoolean("Do Fruit Generation", "General Settings for World Gen", true, "Whether or not to generate wild Fruit Trees");
        oreBiomeMatchEssence = RSiMCMain.config.getString("Biome match string (default is 'Ice')", "Ore Generation Settings", "Ice", "");
        oreMaxEssence = RSiMCMain.config.getInt("Max Size Essence", "Ore Generation Settings", 10, 1, 100, "");
        oreMinEssence = RSiMCMain.config.getInt("Min Size Essence", "Ore Generation Settings", 5, 1, 100, "");
        oreMaxhEssence = RSiMCMain.config.getInt("Max Height Essence", "Ore Generation Settings", 7, 1, 255, "");
        oreMinhEssence = RSiMCMain.config.getInt("Min Height Essence", "Ore Generation Settings", 5, 1, 255, "");
        oreSpawnEssence = RSiMCMain.config.getInt("Rarity Essence", "Ore Generation Settings", 1, 1, 100, "");
        oreMaxCopper = RSiMCMain.config.getInt("Max Height Copper", "Ore Generation Settings", 128, 1, 255, "");
        oreMinCopper = RSiMCMain.config.getInt("Min Height Copper", "Ore Generation Settings", 32, 1, 255, "");
        oreSpawnCopper = RSiMCMain.config.getInt("Rarity Copper", "Ore Generation Settings", 15, 1, 100, "");
        oreSizeCopper = RSiMCMain.config.getInt("Vein Size Copper", "Ore Generation Settings", 8, 2, 100, "");
        oreMaxTin = RSiMCMain.config.getInt("Max Height Tin", "Ore Generation Settings", 128, 1, 255, "");
        oreMinTin = RSiMCMain.config.getInt("Min Height Tin", "Ore Generation Settings", 32, 1, 255, "");
        oreSpawnTin = RSiMCMain.config.getInt("Rarity Tin", "Ore Generation Settings", 15, 1, 100, "");
        oreSizeTin = RSiMCMain.config.getInt("Vein Size Tin", "Ore Generation Settings", 8, 2, 100, "");
        oreMaxIron = RSiMCMain.config.getInt("Max Height Iron", "Ore Generation Settings", 128, 1, 255, "");
        oreMinIron = RSiMCMain.config.getInt("Min Height Iron", "Ore Generation Settings", 20, 1, 255, "");
        oreSpawnIron = RSiMCMain.config.getInt("Rarity Iron", "Ore Generation Settings", 12, 1, 100, "");
        oreSizeIron = RSiMCMain.config.getInt("Vein Size Iron", "Ore Generation Settings", 8, 2, 100, "");
        oreMaxSilver = RSiMCMain.config.getInt("Max Height Silver", "Ore Generation Settings", 96, 1, 255, "");
        oreMinSilver = RSiMCMain.config.getInt("Min Height Silver", "Ore Generation Settings", 32, 1, 255, "");
        oreSpawnSilver = RSiMCMain.config.getInt("Rarity Silver", "Ore Generation Settings", 10, 1, 100, "");
        oreSizeSilver = RSiMCMain.config.getInt("Vein Size Silver", "Ore Generation Settings", 8, 2, 100, "");
        oreMaxCoal = RSiMCMain.config.getInt("Max Height Coal", "Ore Generation Settings", 128, 1, 255, "");
        oreMinCoal = RSiMCMain.config.getInt("Min Height Coal", "Ore Generation Settings", 1, 1, 255, "");
        oreSpawnCoal = RSiMCMain.config.getInt("Rarity Coal", "Ore Generation Settings", 9, 1, 100, "");
        oreSizeCoal = RSiMCMain.config.getInt("Vein Size Coal", "Ore Generation Settings", 16, 2, 100, "");
        oreMaxGem = RSiMCMain.config.getInt("Max Height Gem", "Ore Generation Settings", 128, 1, 255, "");
        oreMinGem = RSiMCMain.config.getInt("Min Height Gem", "Ore Generation Settings", 1, 1, 255, "");
        oreSpawnGem = RSiMCMain.config.getInt("Rarity Gem", "Ore Generation Settings", 3, 1, 100, "");
        oreSizeGem = RSiMCMain.config.getInt("Vein Size Gem", "Ore Generation Settings", 2, 2, 100, "");
        oreMaxGold = RSiMCMain.config.getInt("Max Height Gold", "Ore Generation Settings", 48, 1, 255, "");
        oreMinGold = RSiMCMain.config.getInt("Min Height Gold", "Ore Generation Settings", 1, 1, 255, "");
        oreSpawnGold = RSiMCMain.config.getInt("Rarity Gold", "Ore Generation Settings", 9, 1, 100, "");
        oreSizeGold = RSiMCMain.config.getInt("Vein Size Gold", "Ore Generation Settings", 8, 2, 100, "");
        oreMaxMithril = RSiMCMain.config.getInt("Max Height Mithril", "Ore Generation Settings", 32, 1, 255, "");
        oreMinMithril = RSiMCMain.config.getInt("Min Height Mithril", "Ore Generation Settings", 1, 1, 255, "");
        oreSpawnMithril = RSiMCMain.config.getInt("Rarity Mithril", "Ore Generation Settings", 6, 1, 100, "");
        oreSizeMithril = RSiMCMain.config.getInt("Vein Size Mithril", "Ore Generation Settings", 6, 2, 100, "");
        oreMaxAdamant = RSiMCMain.config.getInt("Max Height Adamantite", "Ore Generation Settings", 28, 1, 255, "");
        oreMinAdamant = RSiMCMain.config.getInt("Min Height Adamantite", "Ore Generation Settings", 1, 1, 255, "");
        oreSpawnAdamant = RSiMCMain.config.getInt("Rarity Adamantite", "Ore Generation Settings", 3, 1, 100, "");
        oreSizeAdamant = RSiMCMain.config.getInt("Vein Size Adamantite", "Ore Generation Settings", 5, 2, 100, "");
        oreMaxRune = RSiMCMain.config.getInt("Max Height Runite", "Ore Generation Settings", 16, 1, 255, "");
        oreMinRune = RSiMCMain.config.getInt("Min Height Runite", "Ore Generation Settings", 1, 1, 255, "");
        oreSpawnRune = RSiMCMain.config.getInt("Rarity Runite", "Ore Generation Settings", 2, 1, 100, "");
        oreSizeRune = RSiMCMain.config.getInt("Vein Size Runite", "Ore Generation Settings", 4, 2, 100, "");
        oreMaxBarrows = RSiMCMain.config.getInt("Max Height Barrows", "Ore Generation Settings", 4, 1, 255, "");
        oreMinBarrows = RSiMCMain.config.getInt("Min Height Barrows", "Ore Generation Settings", 1, 1, 255, "");
        oreSpawnBarrows = RSiMCMain.config.getInt("Rarity Barrows", "Ore Generation Settings", 1, 1, 100, "");
        oreSizeBarrows = RSiMCMain.config.getInt("Vein Size Barrows", "Ore Generation Settings", 2, 2, 100, "");
        if (RSiMCMain.config.hasChanged()) {
            RSiMCMain.config.save();
        }
    }

    public static class Categories {
        public static final String CATEGORY_GENERAL = "General Settings";
        public static final String CATEGORY_CLIENT_SETTINGS = "Optional Client Settings";
        public static final String CATEGORY_ITEM_BEHAVIOR = "How Items and Blocks Act";
        public static final String CATEGORY_ORE_TIMERS = "Ore Timers (in seconds)";
        public static final String CATEGORY_MODIFIERS = "Modifiers for Various Mechanics";
        public static final String CATEGORY_MISC_GENERATION = "General Settings for World Gen";
        public static final String CATEGORY_ORE_GENERATION = "Ore Generation Settings";
    }

}


