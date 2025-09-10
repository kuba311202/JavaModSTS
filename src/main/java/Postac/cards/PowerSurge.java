package Postac.cards;


import Postac.actions.MakeItQuickAction;
import Postac.actions.ReducePowerAction;
import Postac.powers.PowerSurgePower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Postac.Postac;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Postac.Postac.makeCardPath;


public class PowerSurge extends AbstractGreyCard {


    public static final String ID = Postac.makeID(PowerSurge.class.getSimpleName());
    public static final String IMG = makeCardPath("PowerSurge.png");//
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 2;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int MAGIC_NUMBER = 4;
    private static final int UPGRADE_MAGIC_NUMBER = 2;


    public PowerSurge() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = MAGIC_NUMBER;
        baseMagicNumber = MAGIC_NUMBER;
        exhaust = true;
        this.tags.add(Postac.FLANK);
    }

    public PowerSurge(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isFlanked(this)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p,damage)));
        if(isFlanked(this)){
                this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
            this.addToBot(new ApplyPowerAction(p,p, new PowerSurgePower(p,p,1)));
            if(p.hasPower("Postac:FatedPower") && p.getPower("Postac:FatedPower").amount>0) {
                this.addToBot(new ReduceCostForTurnAction(p.hand.getRandomCard(true), 1));
                this.addToBot(new ReducePowerAction(p, p, "Postac:FatedPower", 1));
                p.getPower("Postac:FatedPower").flash();
            }
        }
    }



    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}
