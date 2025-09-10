package Postac.cards;

import Postac.Postac;
import Postac.actions.MakeTempCardInHandAction;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.*;

public class FirstCut extends AbstractDynamicCard {


    public static final String ID = Postac.makeID(FirstCut.class.getSimpleName());
    public static final String IMG = makeCardPath("FirstCut.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UpgradedDescription = cardStrings.UPGRADE_DESCRIPTION;



    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_DAMAGE = 2;



    public FirstCut() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        cardsToPreview = new SecondCut();
        tags.add(Postac.CREATOR);
    }

    public FirstCut(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractCard SecondCutCard = new SecondCut();
        if (this.upgraded){
            SecondCutCard.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(SecondCutCard, 1));
    }



    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            rawDescription = UpgradedDescription;
            upgradeDamage(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}
