package Postac.potions;

import Postac.Postac;
import basemod.abstracts.CustomPotion;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.ArrayList;
import java.util.Iterator;

public class BelowPotion extends CustomPotion {

    public static final String POTION_ID = Postac.makeID("BelowPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public BelowPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.FIRE);
        
        // Potency is the damage/magic number equivalent of potions.
        potency = getPotency();
        
        // Initialize the Description
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        
       // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;
        this.targetRequired = false;

        // Initialize the on-hover name + description
        tips.add(new PowerTip(name, description));
        
    }

    public void initializeData() {
        this.potency = this.getPotency();
        if (AbstractDungeon.player != null && this.potency > 1) {
            if (this.potency > 2) {
                this.description = potionStrings.DESCRIPTIONS[0] + potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[3];
            } else {
                this.description = potionStrings.DESCRIPTIONS[0] + potionStrings.DESCRIPTIONS[1] + this.potency + potionStrings.DESCRIPTIONS[2];
            }
        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature target) {
        ArrayList<AbstractCard> cardsList = this.generateColorlessCardChoices();
        this.addToBot(new SelectCardsCenteredAction(cardsList, "Choose.", (cards) -> {
            for(int i = 0; i < this.potency; ++i) {
                AbstractCard card = ((AbstractCard)cards.get(0)).makeStatEquivalentCopy();
                card.setCostForTurn(0);
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(card));
            }

        }));
    }

    private ArrayList<AbstractCard> generateColorlessCardChoices() {
        ArrayList<AbstractCard> choiceList = new ArrayList();
        ArrayList<AbstractCard> choiceList1 = new ArrayList();
        Iterator var4 = CardLibrary.getAllCards().iterator();

        while(var4.hasNext()) {
            AbstractCard c = (AbstractCard)var4.next();
            if (c.hasTag(Postac.BELOW)) {
                    choiceList.add(c.makeCopy());
            }
        }


        while(choiceList1.size()<4){
        choiceList1.add(choiceList.get(AbstractDungeon.cardRandomRng.random(choiceList.size() - 1)));}
        return choiceList1;
    }
    
    @Override
    public AbstractPotion makeCopy() {
        return new BelowPotion();
    }

    @Override
    public int getPotency(final int potency) {
        return 1;
    }

}
