package Postac;

import Postac.potions.*;
import Postac.powers.Airborne;
import Postac.relics.*;
import Postac.variables.DefaultSecondMagicNumber;
import Postac.variables.NextTurnDamage;
import basemod.*;
import basemod.eventUtil.AddEventParams;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Postac.cards.*;
import Postac.characters.TheUnforgiven;
import Postac.events.IdentityCrisisEvent;
import Postac.util.IDCheckDontTouchPls;
import Postac.util.TextureLoader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@SpireInitializer
public class Postac implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {

    @SpireEnum
    public static CardTags CREATED;
    @SpireEnum
    public static CardTags CREATOR;
    @SpireEnum
    public static CardTags TEMPESTGAIN;
    @SpireEnum
    public static CardTags AIRBORNEGAIN;
    @SpireEnum
    public static CardTags FLANK;
    @SpireEnum
    public static CardTags FINALBLOW;
    @SpireEnum
    public static CardTags BELOW;
    @SpireEnum
    public static CardTags FLOW;

    public static final Logger logger = LogManager.getLogger(Postac.class.getName());
    private static String modID;


    public static Properties PostacDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true;


    private static final String MODNAME = "TheUnforgiven";
    private static final String AUTHOR = "RedGuy22";
    private static final String DESCRIPTION = "Mod that adds a new character: The Unforgiven!";
    


    public static final Color DEFAULT_DARK_BLUE = CardHelper.getColor(0.0f, 0.0f, 171.0f);
    
    // Potion Colors in RGB
    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown
    

    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
  
    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_DEFAULT_DARK_BLUE = "PostacResources/images/512/bg_attack_default_dark_blue.png";
    private static final String SKILL_DEFAULT_DARK_BLUE = "PostacResources/images/512/bg_skill_default_dark_blue.png";
    private static final String POWER_DEFAULT_DARK_BLUE = "PostacResources/images/512/bg_power_default_dark_blue.png";
    
    private static final String ENERGY_ORB_DEFAULT_DARK_BLUE = "PostacResources/images/512/card_default_dark_blue_orb.png";
    private static final String CARD_ENERGY_ORB = "PostacResources/images/512/card_small_orb.png";
    
    private static final String ATTACK_DEFAULT_DARK_BLUE_PORTRAIT = "PostacResources/images/1024/bg_attack_default_gray.png";
    private static final String SKILL_DEFAULT_DARK_BLUE_PORTRAIT = "PostacResources/images/1024/bg_skill_default_gray.png";
    private static final String POWER_DEFAULT_DARK_BLUE_PORTRAIT = "PostacResources/images/1024/bg_power_default_gray.png";
    private static final String ENERGY_ORB_DEFAULT_DARK_BLUE_PORTRAIT = "PostacResources/images/1024/card_default_dark_blue_orb.png";
    
    // Character assets
    private static final String THE_DEFAULT_BUTTON = "PostacResources/images/charSelect/DefaultCharacterButton.png";
    private static final String THE_DEFAULT_PORTRAIT = "PostacResources/images/charSelect/DefaultCharacterPortraitBG.png";
    public static final String THE_DEFAULT_SHOULDER_1 = "PostacResources/images/char/defaultCharacter/shoulder.png";
    public static final String THE_DEFAULT_SHOULDER_2 = "PostacResources/images/char/defaultCharacter/shoulder2.png";
    public static final String THE_DEFAULT_CORPSE = "PostacResources/images/char/defaultCharacter/corpse.png";
    
    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "PostacResources/images/Badge.png";
    
    // Atlas and JSON files for the Animations
    public static final String THE_DEFAULT_SKELETON_ATLAS = "PostacResources/images/char/defaultCharacter/skeleton.atlas";
    public static final String THE_DEFAULT_SKELETON_JSON = "PostacResources/images/char/defaultCharacter/skeleton.json";
    
    // =============== MAKE IMAGE PATHS =================
    
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }

    public Postac() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);

        setModID("Postac");
        
        logger.info("Done subscribing");
        
        logger.info("Creating the color " + TheUnforgiven.Enums.COLOR_DARK_BLUE.toString());
        
        BaseMod.addColor(TheUnforgiven.Enums.COLOR_DARK_BLUE, DEFAULT_DARK_BLUE, DEFAULT_DARK_BLUE, DEFAULT_DARK_BLUE,
                DEFAULT_DARK_BLUE, DEFAULT_DARK_BLUE, DEFAULT_DARK_BLUE, DEFAULT_DARK_BLUE,
                ATTACK_DEFAULT_DARK_BLUE, SKILL_DEFAULT_DARK_BLUE, POWER_DEFAULT_DARK_BLUE, ENERGY_ORB_DEFAULT_DARK_BLUE,
                ATTACK_DEFAULT_DARK_BLUE_PORTRAIT, SKILL_DEFAULT_DARK_BLUE_PORTRAIT, POWER_DEFAULT_DARK_BLUE_PORTRAIT,
                ENERGY_ORB_DEFAULT_DARK_BLUE_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");
        
        
        logger.info("Adding mod settings");
        PostacDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("TheUnforgiven", "PostacConfig", PostacDefaultSettings);
            config.load();
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
        
    }
    
    public static void setModID(String ID) {
        Gson coolG = new Gson();
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8));
        InputStream in = Postac.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json");
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class);
        logger.info("You are attempting to set your mod ID as: " + ID);
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) {
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION);
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) {
            modID = EXCEPTION_STRINGS.DEFAULTID;
        } else {
            modID = ID;
        }
        logger.info("Success! ID is " + modID);
    }
    
    public static String getModID() {
        return modID;
    }
    
    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = Postac.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = Postac.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    
    // ====== YOU CAN EDIT AGAIN ======
    
    
    public static void initialize() {
        logger.info("========================= Initializing Default Mod. Hi. =========================");
        Postac TheUnforgiven = new Postac();
        logger.info("========================= /Default Mod Initialized. Hello World./ =========================");
    }

    
    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================
    
    
    // =============== LOAD THE CHARACTER =================
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TheUnforgiven.Enums.THE_UNFORGIVEN.toString());
        
        BaseMod.addCharacter(new TheUnforgiven("the Unforgiven", TheUnforgiven.Enums.THE_UNFORGIVEN),
                THE_DEFAULT_BUTTON, THE_DEFAULT_PORTRAIT, TheUnforgiven.Enums.THE_UNFORGIVEN);

        
        receiveEditPotions();
        logger.info("Added " + TheUnforgiven.Enums.THE_UNFORGIVEN.toString());
    }
    
    // =============== /LOAD THE CHARACTER/ =================
    
    
    // =============== POST-INITIALIZE =================
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        
        // Create the on/off button:
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                enablePlaceholder,
                settingsPanel,
                (label) -> {},
                (button) -> {
            
            enablePlaceholder = button.enabled;
            try {
                SpireConfig config = new SpireConfig("defaultMod", "PostacConfig", PostacDefaultSettings);
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        settingsPanel.addUIElement(enableNormalsButton); // Add the button to the settings panel. Button is a go.
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);


        AddEventParams eventParams = new AddEventParams.Builder(IdentityCrisisEvent.ID, IdentityCrisisEvent.class) // for this specific event
            .dungeonID(TheCity.ID)
            .playerClass(TheUnforgiven.Enums.THE_UNFORGIVEN)
            .create();


        BaseMod.addEvent(eventParams);

        logger.info("Done loading badge Image and mod options");
    }

    
    // =============== / POST-INITIALIZE/ =================
    
    // ================ ADD POTIONS ===================
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        

        BaseMod.addPotion(TempestPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, TempestPotion.POTION_ID, TheUnforgiven.Enums.THE_UNFORGIVEN);

        BaseMod.addPotion(AirbornePotion.class,Color.GOLDENROD, Color.LIGHT_GRAY, Color.YELLOW, AirbornePotion.POTION_ID, TheUnforgiven.Enums.THE_UNFORGIVEN);

        BaseMod.addPotion(PotionOfBalance.class, Color.GOLD, Color.BROWN, Color.VIOLET, PotionOfBalance.POTION_ID, TheUnforgiven.Enums.THE_UNFORGIVEN);

        BaseMod.addPotion(BelowPotion.class, Color.FIREBRICK, Color.DARK_GRAY, Color.RED, BelowPotion.POTION_ID, TheUnforgiven.Enums.THE_UNFORGIVEN);

        BaseMod.addPotion(CutPotion.class, Color.FOREST, Color.SALMON, Color.PINK, CutPotion.POTION_ID, TheUnforgiven.Enums.THE_UNFORGIVEN);

        logger.info("Done editing potions");
    }
    
    // ================ /ADD POTIONS/ ===================
    
    
    // ================ ADD RELICS ===================
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");

        BaseMod.addRelicToCustomPool(new Zephyr(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new ImmortalShieldBow(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new DeathsDance(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new RavenousHydra(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new InfinityEdge(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new SerpentsFang(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new SilvermereDawn(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new StatikkShiv(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new WitsEnd(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new Bloodthirster(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        BaseMod.addRelicToCustomPool(new GuinsoosRageBlade(), TheUnforgiven.Enums.COLOR_DARK_BLUE);

        logger.info("Done adding relics!");
    }
    
    // ================ /ADD RELICS/ ===================
    
    
    // ================ ADD CARDS ===================
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        pathCheck();
        logger.info("Add variables");
        BaseMod.addDynamicVariable(new NextTurnDamage());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        
        logger.info("Adding cards");

        new AutoAdd("Postac")
            .packageFilter(AbstractDefaultCard.class)
            .setDefaultSeen(true)
            .cards();

        logger.info("Done adding cards!");

    }
    
    // ================ /ADD CARDS/ ===================
    
    
    // ================ LOAD THE TEXT ===================
    
    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        
        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/Postac-Card-Strings.json");
        
        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/Postac-Power-Strings.json");
        
        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/Postac-Relic-Strings.json");
        
        // Event Strings
        BaseMod.loadCustomStringsFile(EventStrings.class,
                getModID() + "Resources/localization/eng/Postac-Event-Strings.json");
        
        // PotionStrings
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                getModID() + "Resources/localization/eng/Postac-Potion-Strings.json");
        
        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/Postac-Character-Strings.json");
        
        // OrbStrings
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                getModID() + "Resources/localization/eng/Postac-Orb-Strings.json");
        
        logger.info("Done edittting strings");
    }
    
    // ================ /LOAD THE TEXT/ ===================
    
    // ================ LOAD THE KEYWORDS ===================

    @Override
    public void receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword
        
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Postac-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }
        BaseMod.addKeyword(new String[]{"Flanked","flanked","Flanked:"},"When card is first from left or right.");
        BaseMod.addKeyword(new String[]{"Tempest", "tempest"},"Transform into Critical Strike when you have 7 or more.");
        BaseMod.addKeyword(new String[]{"Airborne", "airborne"},"Lets you gain additional effects on cards with Final Blow.");
        BaseMod.addKeyword(new String[]{"Final Blow", "final blow"},"Requires x Airborne to gain additional effects.");
        BaseMod.addKeyword(new String[]{"dash", "Dash"},"Your next Steel Tempest deals damage to all enemies.");
        BaseMod.addKeyword(new String[]{"Below", "below"},"When enemy has 40% or less, of hp.");
        BaseMod.addKeyword(new String[]{"creator", "Creator"},"Card that creates other cards after playing it.");
    }
    
    // ================ /LOAD THE KEYWORDS/ ===================    
    
    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }



}
