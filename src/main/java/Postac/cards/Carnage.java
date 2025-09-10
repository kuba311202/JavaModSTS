//Do zmiany, zmieniamy działanie airborne i smash/final blow
package Postac.cards;


import Postac.Postac;
import Postac.characters.TheUnforgiven;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;


import static Postac.Postac.makeCardPath;



public class Carnage extends AbstractGreyCard {


    public static final String ID = Postac.makeID(Carnage.class.getSimpleName());
    public static final String IMG = makeCardPath("Carnage.png");//
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 3;

    private static final int DAMAGE = 19;
    private static final int UPGRADE_PLUS_DMG = 5;


    public Carnage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.tags.add(Postac.FINALBLOW);
    }

    public Carnage(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }

    public void triggerOnGlowCheck() {
        AbstractPlayer p = AbstractDungeon.player;
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
            if (p.hasPower("Postac:Airborne")) {
                if (p.getPower("Postac:Airborne").amount >= 4) {
                    this.costForTurn = 1;
                    this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                }else{
                    costForTurn = cost;
                }
            }else{
                costForTurn = cost;
            }
        }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage)));
        if(p.hasPower("Postac:Airborne" )){
            if (p.getPower("Postac:Airborne").amount >= 4){
                this.addToBot(new ReducePowerAction(p, p, "Postac:Airborne", 4));
            }
        }
    }
    public void triggerWhenDrawn() {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower("Postac:Airborne" )){
            if (p.getPower("Postac:Airborne").amount >= 4){
                this.costForTurn = 1;
            }
        }
    }

    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower("Postac:Airborne" )){
            if (p.getPower("Postac:Airborne").amount >= 4){
                this.costForTurn = 1;
            }
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower("Postac:Airborne" )){
            if (p.getPower("Postac:Airborne").amount >= 4){
                this.costForTurn = 1;
            }
        }
    }



    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
