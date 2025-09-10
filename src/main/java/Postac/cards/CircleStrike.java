
package Postac.cards;

import Postac.Postac;
import Postac.actions.ReducePowerAction;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.makeCardPath;


public class CircleStrike extends AbstractGreyCard {


    public static final String ID = Postac.makeID(CircleStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("CircleStrike.png");//
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static int DAMAGE2 = 9;

    public CircleStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        damage = DAMAGE;
        this.tags.add(Postac.FLANK);
    }

    public CircleStrike(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            if (isFlanked(this)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.isFlanked(this)){
            this.addToBot(new DamageAllEnemiesAction(p, DAMAGE2,damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            if(p.hasPower("Postac:FatedPower") && p.getPower("Postac:FatedPower").amount>0){
                this.addToBot(new ReduceCostForTurnAction(p.hand.getRandomCard(true),1));
                this.addToBot(new ReducePowerAction(p,p,"Postac:FatedPower",1));
                p.getPower("Postac:FatedPower").flash();
            }
        }else{
            this.addToBot(new DamageAction(m, new DamageInfo(p,damage,damageTypeForTurn)));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            DAMAGE2 = DAMAGE;
            initializeDescription();
        }
    }
}
