package Postac.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Postac.Postac;
import Postac.util.TextureLoader;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class ImmortalShieldBow extends CustomRelic {

    public static final String ID = Postac.makeID("ImmortalShieldBow");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ImmortalShieldBow.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ImmortalShieldBowOutline.png"));

    public ImmortalShieldBow() {
        super(ID, IMG,OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    public void wasHPLost(int damageAmount) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && damageAmount >= 0 && !this.grayscale) {
            this.flash();
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NextTurnBlockPower(AbstractDungeon.player, 7, this.name), 7));
            this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player,7));
            this.grayscale = true;
        }
    }

    public void atBattleStart() {
        this.grayscale = false;
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
