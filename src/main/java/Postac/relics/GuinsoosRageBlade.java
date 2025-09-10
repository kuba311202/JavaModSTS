package Postac.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import Postac.Postac;
import Postac.util.TextureLoader;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Postac.Postac.makeRelicOutlinePath;
import static Postac.Postac.makeRelicPath;

public class GuinsoosRageBlade extends CustomRelic {

    private static int amount = 1;
    public static final String ID = Postac.makeID("GuinsoosRageBlade");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Guinsoos_Rageblade.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("GuinsoosRageBladeOutline.png"));

    public GuinsoosRageBlade() {
        super(ID, IMG,OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        AbstractPlayer p = AbstractDungeon.player;
        if(!this.grayscale && p.hasPower("Postac:NextTurnDamagePower") &&  p.getPower("Postac:NextTurnDamagePower").amount>=15 ) {
            this.grayscale = true;
            this.flash();
            }
        }

    public void atBattleStart() {
        this.grayscale = false;
    }


    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
