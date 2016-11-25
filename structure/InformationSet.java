package com.nishinolab.imass.game.structure;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

import com.nishinolab.imass.game.mas.Agent;
import com.nishinolab.imass.util.ID.InformationSetID;

public class InformationSet extends ArrayList<Action> {
	private final int serial; // 情報集合のID番号（作成順に0から付与） ←これいらない？
	private final String name; // この情報集合の名称; (e.g.) agent 1 の3番目の情報集合なら 1-3 など．
								// ←自動化できないか？
	// public final int agentID; //この情報集合のオーナーエージェントのID
	private final Agent agent; // この情報集合のオーナーエージェント
	public List<Node> nodeList; // この情報集合に含まれるノードのリスト

	public InformationSet(int serial, String name, Agent agent) {
		super();
		this.serial = serial;
		this.name = name;
		this.agent = agent;
		nodeList = new ArrayList<Node>();
	}

	public InformationSet(String name, Agent agent) {
		this(InformationSetID.registerID(), name, agent);
	}

	public void add(Node node) {
		nodeList.add(node);
		node.setInformationSet(this); // nodeにもinformationSetをセットしておく
	}

	public void checkActions() {
		// 情報集合内では各ノードで行動は一致していないといけないのでチェック！
		nodeList.forEach(node -> {
			if (checkConsistencyWithOtherNodes(node) == false) {
				System.out.println("Error!: 情報集合内の各ノードの選択肢が一致しません");
				System.out.println("追加Node: " + node.getActionList());
				System.out.println("登録済Node: " + nodeList.get(0).getActionList());
				System.exit(1); // 終了させる
			}
		});
		nodeList.get(0).getActionList().forEach(action -> super.add(action));
	}

	public String getName() {
		return name;
	}

	// このメソッドはNashEquilibriumクラスで使っている
	public int getAgentID() {
		return agent.getID();
	}

	// このメソッドはGameTemplateクラスで使っている
	public Agent getAgent() {
		return this.agent;
	}

	// 情報集合内では各ノードで行動は一致していないといけないのでチェックするためのメソッド
	private boolean checkConsistencyWithOtherNodes(Node node) {
		boolean flg = true;
		if (nodeList.size() != 0) {
			int size = nodeList.get(0).getOutEdges().size(); // このInformationSetの選択肢の数
			if (size != node.getOutEdges().size()) {
				flg = false;
				System.out.println("here1");
			} else {
				for (Action action : node.getActionList())
					if (nodeList.get(0).getActionList().indexOf(action) == -1) {
						flg = false;
						System.out.println("here2");
						break;
					}
			}
		}
		return flg;
	}

	public String toString() {
		List<String> list = new ArrayList<>();
		for (Node n : nodeList)
			list.add(n.getName());
		return "[" + String.join(",", list) + "]";
	}

}
