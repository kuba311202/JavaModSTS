package Postac.relics;

import Postac.Postac;
import Postac.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class SerpentsFang extends CustomRelic {


    public static final String ID = Postac.makeID("SerpentsFang");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SerpentsFang.png"));
    //private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("deathsdanceoutline.png"));
    public SerpentsFang() {
        super(ID, IMG, RelicTier.RARE, LandingSound.MAGICAL);
    }


    public void atBattleStart() {
        AbstractCreature p = AbstractDungeon.player;
        this.flash();
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
