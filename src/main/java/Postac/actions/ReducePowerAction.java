//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Postac.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ReducePowerAction extends AbstractGameAction {
    private String powerID;
    private AbstractPower powerInstance;

    public ReducePowerAction(AbstractCreature target, AbstractCreature source, String power, int amount) {
        this.setValues(target, source, amount);
        if (Settings.FAST_MODE) {
            this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        }

        this.powerID = power;
        this.actionType = ActionType.REDUCE_POWER;
    }

    public ReducePowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerInstance, int amount) {
        this.setValues(target, source, amount);
        if (Settings.FAST_MODE) {
            this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        }

        this.powerInstance = powerInstance;
        this.actionType = ActionType.REDUCE_POWER;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            AbstractPower reduceMe = null;
            if (this.powerID != null) {
                reduceMe = this.target.getPower(this.powerID);
            } else if (this.powerInstance != null) {
                reduceMe = this.powerInstance;
            }

            if (reduceMe != null) {
                if (this.amount <= reduceMe.amount) {
                    reduceMe.reducePower(this.amount);
                    reduceMe.updateDescription();
                    AbstractDungeon.onModifyPower();
                }
            }
        }

        this.tickDuration();
    }
}
