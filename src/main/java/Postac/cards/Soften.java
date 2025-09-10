
package Postac.cards;


import Postac.powers.Tempest;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Postac.Postac;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static Postac.Postac.makeCardPath;


public class Soften extends AbstractGreyCard {


    public static final String ID = Postac.makeID(Soften.class.getSimpleName());
    public static final String IMG = makeCardPath("Soften.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int MAGIC_NUMBER = 2;

    public Soften() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC_NUMBER;
    }

    public Soften(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m,new DamageInfo(p,damage)));
        this.addToBot(new ApplyPowerAction(m,p, new VulnerablePower(m,1, false)));
        this.addToBot(new ApplyPowerAction(p,p, new Tempest(p,p,magicNumber)));
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
