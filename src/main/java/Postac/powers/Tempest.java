package Postac.powers;

import Postac.cards.SteelTempest;
import basemod.devcommands.power.Power;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import Postac.Postac;
import Postac.util.TextureLoader;

import java.util.Iterator;
import java.util.Objects;

import static Postac.Postac.makePowerPath;


public class Tempest extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = Postac.makeID("Tempest");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));


    public Tempest(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.description = DESCRIPTIONS[0];

        type = PowerType.BUFF;
        isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
        CreateCriticalStrike();
    }


    private void CreateCriticalStrike() {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower("Postac:Tempest")) {
            if (p.getPower("Postac:Tempest").amount < 7) {
            } else {
                this.addToBot(new ReducePowerAction(p, p, "Postac:Tempest", 7));
                this.addToBot(new ApplyPowerAction(p, p, new CriticalStrike(p, p, 1), 1));
                this.flash();
            }
        }
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower("Postac:Tempest")) {
            if (p.getPower("Postac:Tempest").amount >= 7) {
                this.addToBot(new ReducePowerAction(p, p, "Postac:Tempest", 7));
                this.addToBot(new ApplyPowerAction(p, p, new CriticalStrike(p, p, 1), 1));
                this.flash();
            }
        }
    }




    @Override
    public AbstractPower makeCopy() {
        return new Tempest(owner, source, amount);
    }
}
