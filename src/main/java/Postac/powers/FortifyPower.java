package Postac.powers;

import Postac.Postac;
import Postac.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;


import static Postac.Postac.makePowerPath;



public class FortifyPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = Postac.makeID("FortifyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    public int total = 0;
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));


    public FortifyPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.total = amount;
        this.amount = amount;
        this.source = source;
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

        type = PowerType.BUFF;
        isTurnBased = false;
        canGoNegative = true;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void onAfterCardPlayed(AbstractCard usedCard){
        AbstractPlayer p = AbstractDungeon.player;
        this.amount = this.amount - 1;
        this.flash();
        if (this.amount == 0)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p, new DexterityPower(p,1)));
            this.amount = this.total;
       }
    }
    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p,p,"Postac:FortifyPower"));
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.total += stackAmount;
        this.updateDescription();
    }

    @Override
    public AbstractPower makeCopy() {
        return new FortifyPower(owner, source, amount);
    }
}