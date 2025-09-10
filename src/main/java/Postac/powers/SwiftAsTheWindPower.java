package Postac.powers;

import Postac.Postac;
import Postac.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Postac.Postac.makePowerPath;



public class SwiftAsTheWindPower extends AbstractUnforgivenPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = Postac.makeID("SwiftAsTheWindPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    public int total = 0;
    private static int drawSize;
    private static int handSize;

    public SwiftAsTheWindPower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        this.total = this.amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
         drawSize = AbstractDungeon.player.drawPile.size();
         handSize = AbstractDungeon.player.hand.size();
        }


    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        AbstractCard c;
        if (amount > 0) {
            if (super.isCreated(drawSize, handSize)) {
                amount = amount - 1;
                this.flash();
                this.updateDescription();
                c = AbstractDungeon.player.hand.getTopCard();
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c));
                }
            }
        }

    public void atStartOfTurn(){
        this.amount = this.total;
        updateDescription();
    }


    public void updateDescription() {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SwiftAsTheWindPower(owner, source, amount);
    }
}
