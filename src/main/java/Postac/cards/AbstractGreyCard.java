package Postac.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractGreyCard extends AbstractDefaultCard  {
    public boolean isFlankedOn;
    public int nextTurnDamage;

    public AbstractGreyCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    public AbstractGreyCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
    }

    public boolean isFlanked(AbstractCard card){
        if (card == AbstractDungeon.player.hand.getTopCard() || card == AbstractDungeon.player.hand.getBottomCard())
        {
            return isFlankedOn = true;
        }
        return isFlankedOn = false;
    }

    

}
