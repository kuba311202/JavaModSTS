package Postac.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class MakeItQuickAction extends AbstractGameAction {
    public MakeItQuickAction () {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();
        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if (c.costForTurn > 0) {
                c.costForTurn = 0;
                c.isCostModifiedForTurn = true;
            }
        }
        this.tickDuration();
    }
}

