package com.hearthsim.event.battlecry;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.event.EffectMinionAction;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.BoardModel;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public abstract class BattlecryActionUntargetable extends EffectMinionAction<Minion> {

    protected boolean canEffectDead() { return false; }
    protected boolean canEffectEnemyHero() { return false; }
    protected boolean canEffectEnemyMinions() { return false; }
    protected boolean canEffectOwnHero() { return false; }
    protected boolean canEffectOwnMinions() { return false; }
    protected boolean canEffectSelf() { return false; }
    protected Minion.MinionTribe tribeFilter() { return null; }

    public boolean canEffect(PlayerSide originSide, Card origin, PlayerSide targetSide, Minion targetCharacter, BoardModel board) {
        if (!this.canEffectOwnHero() && targetCharacter.isHero() && originSide == targetSide) {
            return false;
        }

        if (!this.canEffectEnemyHero() && targetCharacter.isHero() && originSide != targetSide) {
            return false;
        }

        if (!this.canEffectOwnMinions() && !targetCharacter.isHero() && originSide == targetSide) {
            return false;
        }

        if (!this.canEffectEnemyMinions() && !targetCharacter.isHero() && originSide != targetSide) {
            return false;
        }

        if (!this.canEffectSelf() && targetCharacter == origin) {
            return false;
        }

        if (!this.canEffectDead() && !targetCharacter.isAlive()) {
            return false;
        }

        if (this.tribeFilter() != null && targetCharacter.getTribe() != this.tribeFilter()) {
            return false;
        }

        return true;
    }
}
