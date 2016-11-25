package com.nishinolab.imass.game.structure;

import java.util.ArrayList;
import java.util.List;

import com.nishinolab.imass.game.mas.Agent;
import com.nishinolab.imass.gui.modelStructure.GraphicsNode;
import com.nishinolab.imass.util.ID.NodeID;

public class Node {
	private final int id;
	private String name;
	private Agent agent; // このNodeで手番を行うAgent
	private List<Edge> out;
	private Edge in; // 入ってくるEdgeは必ず1つであるためListにしていない
	private InformationSet informationSet; // このNodeが所属する情報集合 private
	// ↑できれば最後の3つもfinalで定義したい！
	private List<Action> actionList; // このNodeの行動の選択肢
	private GraphicsNode graphicsNode; // 描画用ノード

	// 終端ノードは out.size()=0 で判別 or agent=null で判別？

	public Node(String name, int id, Agent agent) {
		this.name = name;
		this.id = id;
		this.agent = agent;
		this.in = null; // 最初はnullにセット
		this.out = new ArrayList<Edge>();
		this.informationSet = null; // 最初はnullにセット
		actionList = new ArrayList<Action>();
	}

	/** 
	 * GameTemplate用コンストラクタ
	 * @param name ノードの名前
	 * @param agent このノードで意思決定するエージェント
	 */
	public Node(String name, Agent agent) {
		this(name, NodeID.registerID(), agent);
	}

	public void addOutEdge(Edge edge) {
		this.out.add(edge);
		actionList.add(edge.getAction());
	}

	public void setInEdge(Edge in) {
		this.in = in;
	}

	public void setInformationSet(InformationSet informationSet) {
		this.informationSet = informationSet;
	}

	public void setGraphicsNode(GraphicsNode graphicsNode) {
		this.graphicsNode = graphicsNode;
	}

	public GraphicsNode getGraphicsNode() {
		return graphicsNode;
	}

	public List<Edge> getOutEdges() {
		return out;
	}

	public Edge getInEdge() {
		return in;
	}

	public List<Action> getActionList() {
		return actionList;
	}

	public boolean hasInEdge() {
		if (in == null)
			return false;
		else
			return true;
	}

	// ↓描画で使う
	public List<Node> getOutNodeList() {
		List<Node> outNodeList = new ArrayList<Node>();
		for (Edge e : out)
			outNodeList.add(e.getChildNode());
		return outNodeList;
	}

	// ↓描画で使う
	public Agent getAgent() {
		if (agent == null)
			return null;
		else
			return agent;
	}

	// ↓描画で使う
	public InformationSet getInformationSet() {
		return informationSet;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String toString(){
		return "Node("+name+")";
	}

}
