// Do zmiany Airborne ma działać inaczej
package Postac.cards;

import Postac.Postac;
import Postac.characters.TheUnforgiven;
import Postac.powers.DrainTheAirPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.makeCardPath;


public class DrainTheAir extends AbstractDynamicCard {



    public static final String ID = Postac.makeID(DrainTheAir.class.getSimpleName());
    public static final String IMG = makeCardPath("DrainTheAir.png");
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UpgradeDescription = cardStrings.UPGRADE_DESCRIPTION;



    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheUnforgiven.Enums.COLOR_DARK_BLUE;

    private static final int COST = 3;


    public DrainTheAir() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }

    public DrainTheAir(String id, String img, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrainTheAirPower(p,p,1)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UpgradeDescription;
            initializeDescription();
            this.isInnate = true;
        }
    }
}
