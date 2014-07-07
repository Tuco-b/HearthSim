package com.hearthsim.card.minion;

import com.json.JSONObject;


public class Demon extends Minion {

	public Demon(
			String name,
			byte mana,
			byte attack,
			byte health,
			byte baseAttack,
			byte baseHealth,
			byte maxHealth,
			boolean taunt,
			boolean divineShield,
			boolean windFury,
			boolean charge,
			boolean hasAttacked,
			boolean hasWindFuryAttacked,
			boolean frozen,
			boolean isInHand,
			boolean hasBeenUsed) {
		super(name, mana, attack, health, baseAttack, baseHealth, maxHealth, taunt, divineShield, windFury, charge, hasAttacked, hasWindFuryAttacked, frozen, isInHand, hasBeenUsed);
	}
	
	public Demon(
			String name,
			byte mana,
			byte attack,
			byte health,
			byte baseAttack,
			byte baseHealth,
			byte maxHealth
			) {
		this(name, mana, attack, health, baseAttack, baseHealth, maxHealth, false, false, false, false, false, false, false, true, false);
	}
	
	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		json.put("type", "Demon");
		return json;
	}
}
