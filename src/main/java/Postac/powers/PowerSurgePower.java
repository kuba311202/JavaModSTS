package Postac.powers;

import Postac.Postac;
import Postac.cards.SteelTempest;
import Postac.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sun.security.krb5.internal.crypto.Des;


import static Postac.Postac.makePowerPath;


public class PowerSurgePower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = Postac.makeID("PowerSurgePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public PowerSurgePower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        name = NAME;
        ID = POWER_ID;
        this.amount = amount;
        this.owner = owner;
        this.source = source;
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        if(!p.hasPower("Strength")) {
            this.addToBot(new RemoveSpecificPowerAction(p, p, this));
        }
        else if(card.type == AbstractCard.CardType.ATTACK && p.getPower("Strength").amount >= 0) {
            this.addToTop(new ReducePowerAction(p,p,"Strength",amount));
            if(p.getPower("Strength").amount <= 0) {
                this.addToBot(new RemoveSpecificPowerAction(p, p, this));

            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new PowerSurgePower(owner, source, amount);
    }
}
