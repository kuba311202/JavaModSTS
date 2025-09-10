
package Postac.powers;


import Postac.Postac;
import Postac.cards.AbstractGreyCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;


public abstract class AbstractUnforgivenPower extends AbstractPower {

    public ArrayList<AbstractGameAction> actions = new ArrayList();

    public boolean isCreated(int draw, int hand){
        int drawSize = AbstractDungeon.player.drawPile.size();
        int handSize = AbstractDungeon.player.hand.size();
        if (draw == drawSize) {
            if (hand <= handSize) {
                AbstractDungeon.player.hand.getTopCard().tags.add(Postac.CREATED);
                return true;
            }
        }
        return false;
    }

    public void onUseCard(AbstractGreyCard card, UseCardAction action) {

    }

}
