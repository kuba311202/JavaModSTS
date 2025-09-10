package Postac.relics;

import Postac.Postac;
import Postac.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class WitsEnd extends CustomRelic {

    private static int amount = 5;
    public static final String ID = Postac.makeID("WitsEnd");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WitsEnd.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public WitsEnd() {
        super(ID, IMG, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractCreature p = AbstractDungeon.player;
        if(!this.grayscale) {
            this.addToBot(new GainBlockAction(p, p, 1));
            amount = amount - 1;
            if(amount == 0) {
                this.addToBot(new ApplyPowerAction(p,p, new NextTurnBlockPower(p,5)));
                this.grayscale=true;
            }
        }
    }

    public void atTurnStart() {
        this.grayscale = false;
        amount = 5;
    }



    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
