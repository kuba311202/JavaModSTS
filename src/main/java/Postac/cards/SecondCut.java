package Postac.cards;

import Postac.Postac;
import Postac.actions.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.makeCardPath;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardColor.COLORLESS;


public class SecondCut extends AbstractGreyCard {


    public static final String ID = Postac.makeID(SecondCut.class.getSimpleName());
    public static final String IMG = makeCardPath("SecondCut.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UpgradedDescription = cardStrings.UPGRADE_DESCRIPTION;



    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = COLORLESS;

    private static final int COST = 1;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;



    public SecondCut() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        exhaust = true;
        cardsToPreview = new ThirdCut();
        tags.add(Postac.CREATOR);
        tags.add(Postac.CREATED);

    }

    public SecondCut(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractCard ThirdCutCard = new ThirdCut();
        if (this.upgraded){
            ThirdCutCard.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(ThirdCutCard, 1));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            rawDescription = UpgradedDescription;
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
