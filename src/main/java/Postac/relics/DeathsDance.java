package Postac.relics;

import Postac.powers.Airborne;
import Postac.powers.Tempest;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Postac.Postac;
import Postac.util.TextureLoader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;


import java.util.Objects;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class DeathsDance extends CustomRelic {


    public static final String ID = Postac.makeID("DeathsDance");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("deathsdance.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("deathsdanceoutline.png"));
    public DeathsDance() {
        super(ID, IMG,OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }



    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractCreature p = AbstractDungeon.player;
        if (p.hasPower("Postac:Tempest") && Objects.equals(c.cardID, "Postac:SteelTempest")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 1)));
            this.grayscale = true;
            }
        }

    public void atTurnStart() {
        this.grayscale = false;
    }


    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
