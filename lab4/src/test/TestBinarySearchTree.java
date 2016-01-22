package test;

import bst.BSTVisualizer;
import bst.BinarySearchTree;

public class TestBinarySearchTree {

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		BSTVisualizer visualizer = new BSTVisualizer("Tree", 600, 600);
		tree.add(1);
		tree.add(3);
		tree.add(5);
		tree.add(7);
		tree.add(9);
		tree.add(11);
		tree.add(13);
		visualizer.drawTree(tree);
		System.out.println(tree.height());
		tree.rebuild(); 
		System.out.println(tree.height());
		visualizer.drawTree(tree);
	}
}