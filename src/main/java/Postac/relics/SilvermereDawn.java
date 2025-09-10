package Postac.relics;

import Postac.Postac;
import Postac.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import static Postac.Postac.*;

public class SilvermereDawn extends CustomRelic {

    public static final String ID = Postac.makeID("SilvermereDawn");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("SilvermereDawn.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public SilvermereDawn() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public void atTurnStart() {
        this.grayscale = false;
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractCreature p = AbstractDungeon.player;
        if(!this.grayscale && c.hasTag(Postac.FINALBLOW)) {
            this.addToBot(new HealAction(p,p,4));
            counter = counter - 1;
            this.flash();
            this.grayscale = true;
            }
        }

    public void atBattleStart() {
        this.grayscale = false;
    }


    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
