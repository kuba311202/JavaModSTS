package Postac.powers;

import Postac.Postac;
import Postac.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static Postac.Postac.makePowerPath;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTags.STARTER_DEFEND;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTags.STARTER_STRIKE;


public class FocusOnTheWindPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = Postac.makeID("FocusOnTheWindPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    public int total = 0;

    public FocusOnTheWindPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.total = amount;
        this.amount = 0;

        type = PowerType.BUFF;
        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (this.total == 2) {
            if (card.tags.contains(STARTER_STRIKE) || card.tags.contains(STARTER_DEFEND)) {
                AbstractPlayer p = AbstractDungeon.player;
                addToBot(new ApplyPowerAction(p, p, new Tempest(p, p, 1)));
                description = DESCRIPTIONS[1];
            }
        } else {
            if (card.tags.contains(STARTER_STRIKE)) {
                AbstractPlayer p = AbstractDungeon.player;
                addToBot(new ApplyPowerAction(p, p, new Tempest(p, p, 1)));
                description = DESCRIPTIONS[0];
            }
        }
    }
    @Override
    public AbstractPower makeCopy() {
        return new FocusOnTheWindPower(owner, source, amount);
    }
}
