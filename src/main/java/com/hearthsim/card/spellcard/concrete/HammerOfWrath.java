package com.hearthsim.card.spellcard.concrete;

import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.spellcard.SpellDamage;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.CardDrawNode;
import com.hearthsim.util.tree.HearthTreeNode;

public class HammerOfWrath extends SpellDamage {

    public HammerOfWrath() {
        super();
    }

    @Deprecated
    public HammerOfWrath(boolean hasBeenUsed) {
        this();
        this.hasBeenUsed = hasBeenUsed;
    }

    /**
     *
     * Use the card on the given target
     *
     * Deals 3 damage and draws a cards
     *
     *
     *
     * @param side
     * @param boardState The BoardState before this card has performed its action.  It will be manipulated and returned.
     *
     * @return The boardState is manipulated and returned
     */
    @Override
    protected HearthTreeNode use_core(
            PlayerSide side,
            Minion targetMinion,
            HearthTreeNode boardState, boolean singleRealizationOnly)
        throws HSException {
        HearthTreeNode toRet = super.use_core(side, targetMinion, boardState, singleRealizationOnly);
        if (toRet != null) {
            if (toRet instanceof CardDrawNode) {
                ((CardDrawNode) toRet).addNumCardsToDraw(1);
            } else {
                toRet = new CardDrawNode(toRet, 1);
            }
        }
        return toRet;
    }
}
