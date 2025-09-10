package Postac.relics;

import Postac.Postac;
import Postac.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class Bloodthirster extends CustomRelic {

    public static final String ID = Postac.makeID("Bloodthirster");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Bloodthirster.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BTOutline.png"));

    public Bloodthirster() {
        super(ID, IMG,OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if(!this.grayscale && c.hasTag(Postac.BELOW)) {
            this.addToBot(new GainEnergyAction(1));
            this.grayscale = true;
            this.flash();
            }
        }

        public void atBattleStart() {
        this.grayscale = false;
    }


    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
