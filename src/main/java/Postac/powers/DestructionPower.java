package Postac.powers;

import Postac.Postac;
import Postac.actions.MakeTempCardInHandAction;
import Postac.cards.Cut;
import Postac.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

import static Postac.Postac.makePowerPath;



public class DestructionPower extends TwoAmountPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = Postac.makeID("DestructionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public DestructionPower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.amount2 = 0;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
        this.updateExistingCuts();

    }
    private void updateExistingCuts() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof Cut) {
                if (!c.upgraded) {
                    c.baseDamage = 3 + this.amount2;
                } else {
                    c.baseDamage = 5 + this.amount2;
                }
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof Cut) {
                if (!c.upgraded) {
                    c.baseDamage = 3 + this.amount2;
                } else {
                    c.baseDamage = 5 + this.amount2;
                }
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof Cut) {
                if (!c.upgraded) {
                    c.baseDamage = 3 + this.amount2;
                } else {
                    c.baseDamage = 5 + this.amount2;
                }
            }
        }

        var1 = AbstractDungeon.player.exhaustPile.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (c instanceof Cut) {
                if (!c.upgraded) {
                    c.baseDamage = 3 + this.amount2;
                } else {
                    c.baseDamage = 5 + this.amount2;
                }
            }
        }

    }

    public void onDrawOrDiscard() {
        this.updateExistingCuts();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Cut) {
            this.amount2 = this.amount2 + this.amount;
        }
        this.updateExistingCuts();
        updateDescription();
    }

    public void onRemove() {
        this.amount2 = 0;
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new DestructionPower(owner, source, amount);
    }
}
