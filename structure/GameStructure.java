package com.nishinolab.imass.game.structure;

import java.util.ArrayList;
import java.util.List;

public class GameStructure {
	private List<Node> nodes;
	private List<Edge> edges;
	private List<InformationSet> inforSets;
	private List<List<Node>> structure;

	// コンストラクタ1
	public GameStructure(List<Node> nodes, List<Edge> edges, List<InformationSet> infoSets) {
		this.nodes = nodes;
		this.edges = edges;
		this.inforSets = infoSets;
	}

	// コンストラクタ2
	public GameStructure(Node[] nodes, Edge[] edges, InformationSet[] infoSets) {
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
		this.inforSets = new ArrayList<InformationSet>();
		for (Node n : nodes)
			this.nodes.add(n);
		for (Edge e : edges)
			this.edges.add(e);
		for (InformationSet i : infoSets)
			this.inforSets.add(i);
	}

	// 第1引数のidが親Node，第2引数のidが子Node，第3引数がそれを結ぶEdge
	public void setRelation(int parentId, int childId, int edgeId) {
		Node parentNode = searchNode(parentId);
		Node childNode = searchNode(childId);
		Edge edge = searchEdge(edgeId);
		parentNode.addOutEdge(edge);
		childNode.setInEdge(edge);
		edge.setParentNode(parentNode);
		edge.setChildNode(childNode);
	}

	private Node searchNode(int nodeId) {
		for (Node n : nodes) {
			if (n.getId() == nodeId)
				return n;
		}
		return null;
	}

	private Edge searchEdge(int edgeId) {
		for (Edge e : edges) {
			if (e.getId() == edgeId)
				return e;
		}
		return null;
	}

	public List<Node> getAllNodes() {
		return nodes;
	}

	public List<Edge> getAllEdges() {
		return edges;
	}

	public List<List<Node>> getStructure() {
		return structure;
	}

	// 全部の設定が終わったあとで実行すること！
	public void structuring() {
		structure = new ArrayList<List<Node>>();
		// 親ノードを持たない場合＝始点ノードを探して格納する
		List<Node> temp = new ArrayList<Node>();
		for (Node n : this.nodes)
			if (n.hasInEdge() == false)
				temp.add(n);
		// 始点ノードから順々に階層(Decision stage)ごとにList<Node>として追加していく
		recursiveAdd(temp);
	}

	// ↓これで合ってそう！
	public void recursiveAdd(List<Node> nodes) {
		// 引数のnodesのサイズが1より大きい時にのみ実行, levelは階層levelを表し、始点ノードがlevel=0
		if (nodes.size() > 0) {
			structure.add(nodes);
			List<Node> temp = new ArrayList<Node>();
			for (Node n : nodes) {
				temp.addAll(n.getOutNodeList());
			}
			recursiveAdd(temp); // 1つ階層を深くして再帰
		}
	}

	/*
	 * public void setNodes( List<Node> nodes ){ this.nodes = nodes; }
	 * 
	 * public void setEdge( Node parentNode, Node childNode, Edge edge ){
	 * edge.setParentNode( parentNode ); edge.setChildNode( childNode ); }
	 */

}
