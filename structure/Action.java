package com.nishinolab.imass.game.structure;

public interface Action {
    public int getActionValue();
    public double getNumericalValue();
    public String getActionName();
	public boolean equals( String actionName );
    public String toString();
}