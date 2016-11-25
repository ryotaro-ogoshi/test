package com.nishinolab.imass.game.structure;

import com.nishinolab.imass.util.ID.ActionID;

public class StringAction implements Action {
	private String actionName;
	private int actionValue;

	// コンストラクタ
	public StringAction(String actionName, int actionValue) {
		this.actionName = actionName;
		this.actionValue = actionValue;
		// Game.allActions.put( actionName, this ); //←いらないのでは？
	}

	public StringAction(String actionName) {
		this(actionName, ActionID.registerID());
	}

	public int getActionValue() {
		return actionValue;
	}

	public double getNumericalValue() {
		return actionValue; // このクラスでは使わないのでactionValueを返すことにする
	}

	public String getActionName() {
		return actionName;
	}

	public boolean equals(String actionName) {
		if (actionName.equals(this.actionName))
			return true;
		else
			return false;
	}

	public String toString() {
		//return "String(" + actionName + ")";
		return this.actionName;
	}

}
