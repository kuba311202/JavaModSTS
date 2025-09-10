package Postac.cards;

import Postac.Postac;
import Postac.actions.ReducePowerAction;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.makeCardPath;

public class DoubleProtection extends AbstractGreyCard {


    public static final String ID = Postac.makeID(DoubleProtection.class.getSimpleName());
    public static final String IMG = makeCardPath("DoubleProtection.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    private static final AbstractCard.CardRarity RARITY = CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final  int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 3;



    public DoubleProtection() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        tags.add(Postac.FLANK);
    }

    public DoubleProtection(String id, String img, String name, int cost, String description, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
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
            this.addToBot(new GainBlockAction(p,p,block));
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
            initializeDescription();
        }
    }
}
