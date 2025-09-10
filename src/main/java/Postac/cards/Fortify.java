package Postac.cards;

import Postac.Postac;
import Postac.actions.MakeTempCardInHandAction;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Postac.powers.FortifyPower;

import static Postac.Postac.makeCardPath;

public class Fortify extends AbstractDynamicCard {


    public static final String ID = Postac.makeID(Fortify.class.getSimpleName());
    public static final String IMG = makeCardPath("Fortify.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UpgradeDescription = cardStrings.UPGRADE_DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 1;

    private static final int MAGIC_NUMBER = 6;
    private static final int MAGIC_NUMBER_2 = 1;


    public Fortify() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGIC_NUMBER;
        defaultBaseSecondMagicNumber = MAGIC_NUMBER_2;
    }

    public Fortify(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractCard CardToCreate = new Cut();
            this.addToBot(new ApplyPowerAction(p, p, new FortifyPower(p,p,MAGIC_NUMBER)));
            this.addToBot(new MakeTempCardInHandAction(CardToCreate, defaultSecondMagicNumber));
        }


        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                this.cardsToPreview = new Cut();
                rawDescription = UpgradeDescription;
                initializeDescription();
            }
        }
    }


