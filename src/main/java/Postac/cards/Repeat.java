//Do zmiany, tempest dziaała inaczeej
package Postac.cards;


import Postac.actions.MakeTempCardInHandAction;
import Postac.powers.Tempest;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Postac.Postac;
import Postac.characters.TheUnforgiven;

import static Postac.Postac.makeCardPath;


public class Repeat extends AbstractGreyCard {


    public static final String ID = Postac.makeID(Repeat.class.getSimpleName());
    public static final String IMG = makeCardPath("Repeat.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_DAMAGE = 2;
    private static final int MAGIC_NUMBER = 2;


    public Repeat() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC_NUMBER;
        this.tags.add(Postac.FLANK);
        this.tags.add(Postac.TEMPESTGAIN);
        this.tags.add(Postac.CREATOR);
    }

    public Repeat(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isFlanked(this)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard OnceAgain = new Repeat();
        if(upgraded){
            OnceAgain.upgrade();
        }
        this.addToTop(new DamageAction(m, new DamageInfo(p,damage)));
        this.addToTop(new ApplyPowerAction(p,p, new Tempest(p,p,MAGIC_NUMBER)));
        if(this.isFlanked(this)) {
            this.addToTop(new MakeTempCardInHandAction(OnceAgain));
            this.addToBot(new ShuffleAction(p.hand));
            exhaust=true;
        }
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}
