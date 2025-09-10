package Postac.relics;

import Postac.powers.Airborne;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Postac.Postac;
import Postac.util.TextureLoader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.Iterator;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class Zephyr extends CustomRelic {


    public static final String ID = Postac.makeID("Zephyr");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Zephyr.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public Zephyr() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.MAGICAL);
    }


    public void atBattleStart() {
        AbstractCreature p = AbstractDungeon.player;
        this.flash();
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(var3.hasNext()) {
            AbstractMonster m = (AbstractMonster)var3.next();
            this.addToBot(new RelicAboveCreatureAction(p, this));
            this.addToBot(new ApplyPowerAction(p, p, new Airborne(m,p, 1), 1));
        }
    }



    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
