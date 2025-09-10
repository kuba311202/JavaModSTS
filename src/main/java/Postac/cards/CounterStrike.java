package Postac.cards;

import Postac.Postac;
import Postac.actions.ReducePowerAction;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.makeCardPath;

public class CounterStrike extends AbstractGreyCard {


    public static final String ID = Postac.makeID(CounterStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("CounterStrike.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static int COST = 2;

    private static final int BLOCK = 12;
    private static final int UPGRADE_BLOCK = 3;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE = 3;



    public CounterStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseDamage = DAMAGE;
        tags.add(Postac.FLANK);
    }

    public CounterStrike(String id, String img, String name, int cost, String description, CardType
            type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isFlanked(this)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p,p,block));
        if(this.isFlanked(this)) {
            this.addToBot(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            if(p.hasPower("Postac:FatedPower") && p.getPower("Postac:FatedPower").amount>0){
                this.addToBot(new ReduceCostForTurnAction(p.hand.getRandomCard(true),1));
                this.addToBot(new ReducePowerAction(p,p,"Postac:FatedPower",1));
                p.getPower("Postac:FatedPower").flash();
            }
        }

    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK);
            upgradeDamage(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}