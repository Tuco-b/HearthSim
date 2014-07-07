package com.hearthsim.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.hearthsim.card.Card;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.concrete.BloodfenRaptor;
import com.hearthsim.util.BoardState;
import com.hearthsim.util.HearthTreeNode;

public class TestEvents {

	private HearthTreeNode<BoardState> board;
	private static final byte mana = 2;
	private static final byte attack0 = 2;
	private static final byte health0 = 2;
	private static final byte health1 = 7;

	@Before
	public void setup() {
		board = new HearthTreeNode<BoardState>(new BoardState());

		Minion minion0_0 = new Minion("" + 0, mana, attack0, health0, attack0, health0, health0);
		Minion minion0_1 = new Minion("" + 0, mana, attack0, health1, attack0, health1, health1);
		Minion minion1_0 = new Minion("" + 0, mana, attack0, health0, attack0, health0, health0);
		Minion minion1_1 = new Minion("" + 0, mana, attack0, health1, attack0, health1, health1);
		
		board.data_.placeMinion_p0(minion0_0);
		board.data_.placeMinion_p0(minion0_1);
		
		board.data_.placeMinion_p1(minion1_0);
		board.data_.placeMinion_p1(minion1_1);
				
	}
	
	
	@Test
	public void test0() {

		Minion minion = board.data_.getMinion_p0(0);
		HearthTreeNode<BoardState> ret = minion.attack(0, 1, 1, board, null);
		
		assertFalse("test0_0", ret == null);

	}

}
