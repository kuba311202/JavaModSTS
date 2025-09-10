package Postac.powers;

import Postac.Postac;
import Postac.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static Postac.Postac.makePowerPath;

public class NextTurnPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = Postac.makeID("NextTurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));


    public NextTurnPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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
    }

  /*  public void atEndOfTurn(boolean isPlayer) {
        for (int i = 0; i < AbstractDungeon.player.powers.size(); i++) {
            if (AbstractDungeon.player.powers.get(i).ID.contains("NextTurn") || AbstractDungeon.player.powers.get(i).ID.contains("Next Turn")) {
                if (AbstractDungeon.player.powers.get(i).ID.equals("Postac:NextTurnPower")) {
                    continue;
                }
                int am2 = 0;
                new am2 = TwoAmountPower(AbstractDungeon.player.powers.get(i).amount2);
                 AbstractDungeon.player.powers.get(i).amount2 += amount2;

            }

        }
    }*/


    @Override
    public AbstractPower makeCopy() {
        return new NextTurnPower(owner, source, amount);
    }
}

