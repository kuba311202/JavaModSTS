package Postac.relics;

import Postac.Postac;

import Postac.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import java.util.Iterator;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class RavenousHydra extends CustomRelic {


    public static final String ID = Postac.makeID("RavenousHydra");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RavenousHydra.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("RavenousHydraOutline.png"));
    public RavenousHydra() {
        super(ID, IMG,OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }


    public void onPlayCard(AbstractCard c, AbstractMonster m) {
            if(c.type == AbstractCard.CardType.ATTACK) {
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
        }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
