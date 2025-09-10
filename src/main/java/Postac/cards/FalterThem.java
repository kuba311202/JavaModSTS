package Postac.cards;

import Postac.Postac;
import Postac.actions.ReducePowerAction;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Postac.Postac.*;


public class FalterThem extends AbstractGreyCard {


    public static final String ID = Postac.makeID(FalterThem.class.getSimpleName());
    public static final String IMG = makeCardPath("FalterThem.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 2;

    private static final int DAMAGE = 11;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER = 1;


    public FalterThem() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC_NUMBER;
        this.tags.add(Postac.FLANK);
    }

    public FalterThem(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
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
        this.addToBot(new DamageAction(m, new DamageInfo(p,damage)));
        this.addToTop(new RemoveAllBlockAction(m,p));
        if(this.isFlanked(this)) {
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, baseMagicNumber, false)));
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
            upgradeDamage(UPGRADE_DAMAGE);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}



