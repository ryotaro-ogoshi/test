package com.nishinolab.imass.game.structure;

public class NumericalAction implements Action {
	private String actionName;
	private int actionValue;
	private double numericalValue;

	// コンストラクタ
	public NumericalAction(String actionName, int actionValue, double numericalValue) {
		this.actionName = actionName;
		this.actionValue = actionValue;
		this.numericalValue = numericalValue;
		// Game.allActions.put( actionName, this ); //←いらないのでは？
	}

	public NumericalAction(String actionName, double numericalValue) {
		this.actionName = actionName;
		this.numericalValue = numericalValue;
	}

	public double getNumericalValue() {
		return numericalValue;
	}

	public int getActionValue() {
		return actionValue;
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
		//return "Numerical(" + actionName + ")";
		return String.valueOf(this.numericalValue);
	}

}
