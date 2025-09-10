
package Postac.cards;

import Postac.powers.Airborne;
import Postac.powers.Flow;
import Postac.powers.RetainCardsForTurn;
import Postac.powers.Tempest;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Postac.Postac;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Iterator;

import static Postac.Postac.makeCardPath;


public class CutOpen extends AbstractDynamicCard {


    public static final String ID = Postac.makeID(CutOpen.class.getSimpleName());
    public static final String IMG = makeCardPath("CutOpen.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER = 1;

    private static final int MAGIC_NUMBER2 = 3;
    private static final int UPGRADE_MAGIC_NUMBER2 = 2;

    public CutOpen() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = MAGIC_NUMBER;
        baseMagicNumber = MAGIC_NUMBER;
        defaultBaseSecondMagicNumber = MAGIC_NUMBER2;
        defaultSecondMagicNumber = MAGIC_NUMBER2;
        tags.add(Postac.BELOW);
        tags.add(Postac.TEMPESTGAIN);
        tags.add(Postac.FLOW);
    }

    public CutOpen(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while (var1.hasNext()) {
            AbstractMonster m = (AbstractMonster) var1.next();
            int currHp = m.currentHealth;
            if (!m.isDeadOrEscaped() && currHp <= Math.ceil(m.maxHealth * 0.5)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p,damage)));
        int currHp = m.currentHealth;
        if(currHp <= Math.ceil(m.maxHealth*0.5)) {
            this.addToBot(new ApplyPowerAction(p, p, new Tempest(p,p, MAGIC_NUMBER)));
        }
        else if(currHp > Math.ceil(m.maxHealth*0.5)){
            this.addToBot(new ApplyPowerAction(p, p, new Flow(p,p, MAGIC_NUMBER2)));
        }
    }



    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            upgradeDefaultSecondMagicNumber(UPGRADE_MAGIC_NUMBER2);
            initializeDescription();
        }
    }
}

