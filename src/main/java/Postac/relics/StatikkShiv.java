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
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


import java.util.Objects;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class StatikkShiv extends CustomRelic {


    public static final String ID = Postac.makeID("StatikkShiv");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("StatikkShiv.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("deathsdanceoutline.png"));
    public StatikkShiv() {
        super(ID, IMG, RelicTier.RARE, LandingSound.MAGICAL);
    }



    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractCreature p = AbstractDungeon.player;
        if (Objects.equals(c.cardID, "Postac:Cut")) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 1), 1));
        }
    }


    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
