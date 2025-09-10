//Do zmiany tempeest soe zmienia
package Postac.cards;

import Postac.Postac;
import Postac.characters.TheUnforgiven;
import Postac.powers.Tempest;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static Postac.Postac.makeCardPath;


public class TempestOnslaught extends AbstractDynamicCard {


    public static final String ID = Postac.makeID(TempestOnslaught.class.getSimpleName());
    public static final String IMG = makeCardPath("TempestOnslaught.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;


    private static final int COST = 2;
    private AbstractPower Temp;


    public TempestOnslaught() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
        tags.add(Postac.TEMPESTGAIN);
    }

    public TempestOnslaught(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.Temp = AbstractDungeon.player.getPower("Postac:Tempest");
        int total = Temp.amount;
        this.addToBot(new ApplyPowerAction(p,p, new Tempest(p,p,total)));
        if(upgraded) {
            this.addToBot(new DrawCardAction(p, 1));
        }
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            exhaust = false;
            initializeDescription();
        }
    }
}
