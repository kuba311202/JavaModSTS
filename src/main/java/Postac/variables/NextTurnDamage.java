package Postac.variables;

import Postac.cards.AbstractDefaultCard;
import Postac.cards.AbstractGreyCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static Postac.Postac.makeID;

public class NextTurnDamage extends DynamicVariable {

    @Override
    public String key() {
        return makeID("NTD");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return card.isMagicNumberModified;
    }

    @Override
    public int value(AbstractCard card) {
            return ((AbstractGreyCard) card).nextTurnDamage;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractGreyCard) card).baseMagicNumber;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return  ((AbstractGreyCard) card).upgradedMagicNumber;
    }
}