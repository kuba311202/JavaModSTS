package Postac.cards;

import Postac.Postac;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Postac.Postac.makeCardPath;


public class MinorBlade extends AbstractGreyCard {



    public static final String ID = Postac.makeID(MinorBlade.class.getSimpleName());
    public static final String IMG = makeCardPath("MinorBlade.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 1;

    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER = 1;
    private static final int MAGIC_NUMBER_2 = 2;
    private static final int UPGRADE_MAGIC_NUMBER_2 = 1;



    public MinorBlade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = MAGIC_NUMBER;
        baseMagicNumber = MAGIC_NUMBER;
        defaultBaseSecondMagicNumber = MAGIC_NUMBER_2;
        defaultSecondMagicNumber = MAGIC_NUMBER_2;
        this.cardsToPreview = new Cut();
        tags.add(Postac.CREATOR);
    }

    public MinorBlade(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m,p, new WeakPower(m,magicNumber,false)));
        AbstractCard cut = new Cut();
        this.addToBot(new MakeTempCardInHandAction(cut,defaultSecondMagicNumber));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            upgradeDefaultSecondMagicNumber(UPGRADE_MAGIC_NUMBER_2);
            initializeDescription();

        }
    }
}


