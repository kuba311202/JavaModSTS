package Postac.cards;


import Postac.powers.Tempest;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Postac.Postac;
import Postac.characters.TheUnforgiven;

import static Postac.Postac.makeCardPath;


public class QuickSlash extends AbstractGreyCard {


    public static final String ID = Postac.makeID(QuickSlash.class.getSimpleName());
    public static final String IMG = makeCardPath("QuickSlash.png");//
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 4;
    private static final int UPGRADE_DAMAGE = 1;

    public QuickSlash() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        tags.add(Postac.TEMPESTGAIN);
    }

    public QuickSlash(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        int i = 0;
        if(p.hasPower("Postac:Tempest")) {
            while (i <= p.getPower("Postac:Tempest").amount) {
                this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                i = i + 1;
            }
        }else{
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
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

