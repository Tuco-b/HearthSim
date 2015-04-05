package com.hearthsim.test.card;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.MinionMock;
import com.hearthsim.card.spellcard.concrete.BlessingOfMight;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.BoardModel;
import com.hearthsim.model.PlayerModel;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestBlessingOfMight {

    private HearthTreeNode board;
    private PlayerModel currentPlayer;

    private static final byte mana = 2;
    private static final byte attack0 = 2;
    private static final byte health0 = 5;
    private static final byte health1 = 1;

    @Before
    public void setup() throws HSException {
        board = new HearthTreeNode(new BoardModel());
        currentPlayer = board.data_.getCurrentPlayer();

        Minion minion0 = new MinionMock("" + 0, mana, attack0, health0, attack0, health0, health0);
        Minion minion1 = new MinionMock("" + 0, mana, attack0, health0, attack0, health0, health0);
        Minion minion2 = new MinionMock("" + 0, mana, attack0, health1, attack0, health1, health1);
        Minion minion3 = new MinionMock("" + 0, mana, attack0, (byte)(health0-2), attack0, health0, health0);

        BlessingOfMight fb = new BlessingOfMight();
        currentPlayer.placeCardHand(fb);
        board.data_.placeMinion(PlayerSide.CURRENT_PLAYER, minion0);
        board.data_.placeMinion(PlayerSide.WAITING_PLAYER, minion1);
        board.data_.placeMinion(PlayerSide.WAITING_PLAYER, minion2);
        board.data_.placeMinion(PlayerSide.WAITING_PLAYER, minion3);

        currentPlayer.setMana((byte)10);
    }

    @Test
    public void test0() throws HSException {

        Card theCard = currentPlayer.getHand().get(0);
        HearthTreeNode res;

        res = theCard.useOn(PlayerSide.CURRENT_PLAYER, 1, board);
        assertNotNull(res);
        assertEquals(res.data_.getCurrentPlayer().getHand().size(), 0);
        assertEquals(res.data_.getCurrentPlayer().getNumMinions(), 1);
        assertEquals(res.data_.getWaitingPlayer().getNumMinions(), 3);
        assertEquals(res.data_.getCurrentPlayer().getMana(), 9);
        assertEquals(res.data_.getCurrentPlayer().getMinions().get(0).getHealth(), health0);
        assertEquals(res.data_.getCurrentPlayer().getMinions().get(0).getTotalAttack(), attack0 + 3);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(0).getHealth(), health0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(0).getTotalAttack(), attack0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(1).getHealth(), health1);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(1).getTotalAttack(), attack0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(2).getHealth(), health0-2);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(2).getTotalAttack(), attack0);
        assertEquals(res.data_.getCurrentPlayer().getHero().getHealth(), 30);
        assertEquals(res.data_.getWaitingPlayer().getHero().getHealth(), 30);
    }

    @Test
    public void test1() throws HSException {

        Card theCard = currentPlayer.getHand().get(0);
        HearthTreeNode res;

        res = theCard.useOn(PlayerSide.WAITING_PLAYER, 1, board);
        assertNotNull(res);
        assertEquals(res.data_.getCurrentPlayer().getHand().size(), 0);
        assertEquals(res.data_.getCurrentPlayer().getNumMinions(), 1);
        assertEquals(res.data_.getWaitingPlayer().getNumMinions(), 3);
        assertEquals(res.data_.getCurrentPlayer().getMana(), 9);
        assertEquals(res.data_.getCurrentPlayer().getMinions().get(0).getHealth(), health0);
        assertEquals(res.data_.getCurrentPlayer().getMinions().get(0).getTotalAttack(), attack0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(0).getHealth(), health0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(0).getTotalAttack(), attack0+3);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(1).getHealth(), health1);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(1).getTotalAttack(), attack0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(2).getHealth(), health0-2);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(2).getTotalAttack(), attack0);
        assertEquals(res.data_.getCurrentPlayer().getHero().getHealth(), 30);
        assertEquals(res.data_.getWaitingPlayer().getHero().getHealth(), 30);
    }

    @Test
    public void test2() throws HSException {

        Card theCard = currentPlayer.getHand().get(0);
        HearthTreeNode res;

        res = theCard.useOn(PlayerSide.WAITING_PLAYER, 2, board);
        assertNotNull(res);
        assertEquals(res.data_.getCurrentPlayer().getHand().size(), 0);
        assertEquals(res.data_.getCurrentPlayer().getNumMinions(), 1);
        assertEquals(res.data_.getWaitingPlayer().getNumMinions(), 3);
        assertEquals(res.data_.getCurrentPlayer().getMana(), 9);
        assertEquals(res.data_.getCurrentPlayer().getMinions().get(0).getHealth(), health0);
        assertEquals(res.data_.getCurrentPlayer().getMinions().get(0).getTotalAttack(), attack0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(0).getHealth(), health0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(0).getTotalAttack(), attack0);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(1).getHealth(), health1);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(1).getTotalAttack(), attack0+3);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(2).getHealth(), health0-2);
        assertEquals(res.data_.getWaitingPlayer().getMinions().get(2).getTotalAttack(), attack0);
        assertEquals(res.data_.getCurrentPlayer().getHero().getHealth(), 30);
        assertEquals(res.data_.getWaitingPlayer().getHero().getHealth(), 30);
    }
}
