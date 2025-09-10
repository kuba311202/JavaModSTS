package Postac.cards;

import Postac.powers.Flow;
import Postac.powers.Tempest;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Postac.Postac;
import Postac.characters.TheUnforgiven;

import static Postac.Postac.makeCardPath;


public class SweepingBlade extends AbstractDynamicCard {



    public static final String ID = Postac.makeID(SweepingBlade.class.getSimpleName());
    public static final String IMG = makeCardPath("SB.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;



    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;
    private static int COST = 1;
    private static int MAGIC_NUMBER = 3;
    private static int MAGIC_NUMBER_2 = 2;




    public SweepingBlade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = MAGIC_NUMBER;
        this.defaultBaseSecondMagicNumber = MAGIC_NUMBER_2;
        tags.add(Postac.TEMPESTGAIN);
    }

    public SweepingBlade(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new Flow(p,p, magicNumber)));
        this.addToBot(new ApplyPowerAction(p, p, new Tempest(p,p, defaultSecondMagicNumber)));
    }



    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.COST = 0;
            initializeDescription();
        }
    }

}
