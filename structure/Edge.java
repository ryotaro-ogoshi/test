package com.nishinolab.imass.game.structure;

import com.nishinolab.imass.game.mas.Agent;
import com.nishinolab.imass.gui.modelStructure.GraphicsEdge;
import com.nishinolab.imass.util.ID.EdgeID;

public class Edge {
	private final String name;
	private final int id;
	private final Agent agent; // この行動のOwnerとなるAgentをメンバ変数として持っておく
	private final Action action; // このEdgeが対応する行動
	private Node parentNode, childNode; // このEdgeが繋がっている親ノードと子ノード (finalにしたい！）
	private GraphicsEdge graphicsEdge; // 描画用Edge

	// コンストラクタ
	public Edge(String name, int id, Agent agent, Action action) {
		this.name = name;
		this.id = id;
		this.agent = agent;
		this.action = action;
	}

	public Edge(String name, Agent agent, Action action) {
		this(name, EdgeID.registerID(), agent, action);
	}

	public void setParentNode(Node node) {
		this.parentNode = node;
	}

	public void setChildNode(Node node) {
		this.childNode = node;
	}

	public void setGraphicsEdg(GraphicsEdge graphicsEdge) {
		this.graphicsEdge = graphicsEdge;
	}

	public Node getChildNode() {
		return childNode;
	}

	public GraphicsEdge getGraphicsEdge() {
		return graphicsEdge;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public Action getAction() {
		return action;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public String toString(){
		return "Edge("+name +")";
	}

}
