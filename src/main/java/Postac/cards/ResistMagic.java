//Do zmiany, below dziala inaczej
package Postac.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Postac.Postac;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.Iterator;

import static Postac.Postac.makeCardPath;


public class ResistMagic extends AbstractGreyCard {


    public static final String ID = Postac.makeID(ResistMagic.class.getSimpleName());
    public static final String IMG = makeCardPath("ResistMagic.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UpgradeDescription = cardStrings.UPGRADE_DESCRIPTION;



    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 2;

    private static final int DAMAGE = 14;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER = 1;

    public ResistMagic() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC_NUMBER;
        magicNumber = MAGIC_NUMBER;
        this.tags.add(Postac.BELOW);
        exhaust = true;
    }

    public ResistMagic(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
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
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage)));
        int currHp = m.currentHealth;
        if (currHp <= Math.ceil(m.maxHealth * 0.5)) {
            this.addToBot(new ApplyPowerAction(p,p, new ArtifactPower(p,magicNumber)));
        } else if (currHp > Math.ceil(m.maxHealth * 0.5)) {
            this.addToBot(new ReducePowerAction(p,p,"Vulnerable",1));
            this.addToBot(new ReducePowerAction(p,p,"Frail",1));
            this.addToBot(new ReducePowerAction(p,p,"Weak",1));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            rawDescription = UpgradeDescription;
            initializeDescription();
        }
    }
}

