package Postac.relics;

import Postac.Postac;
import Postac.cards.AbstractGreyCard;
import Postac.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.*;

public class InfinityEdge extends CustomRelic {

    public static final String ID = Postac.makeID("InfinityEdge");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("InfinityEdge.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("InfinityEdgeOutline.png"));

    public InfinityEdge() {
        super(ID, IMG,OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCard Left = p.hand.getBottomCard();
        AbstractCard Right = p.hand.getTopCard();
        if(c.hasTag(Postac.FLANK) && !this.grayscale) {
            if(c == Right || c == Left) {
                this.addToBot(new GainEnergyAction(1));
                this.flash();
                this.grayscale = true;
            }
        }
    }

    public void atBattleStart() {
        this.grayscale = false;
    }


    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
