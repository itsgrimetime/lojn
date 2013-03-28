package mapgen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class MapGenerator {

	public MapGenerator(int width, int height, int splits, String outputFileName) {
		
		Rectangle rect = new Rectangle(0, 0, width, height);
		
		BinaryTree<Rectangle> bspTree = new BinaryTree<Rectangle>(rect);
		
		splitNode(bspTree.getRoot(), 0, splits);
		
		LinkedList<BinaryTreeNode<Rectangle>> rooms = getLeaves(bspTree);
		
		File file = new File(outputFileName);
		
			PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening file " + file.getName());
			e.printStackTrace();
			System.exit(-1);
		}
		
		char[][] tileChars = new char[width][height];
		
		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++)
				tileChars[i][j] = '1';
		
		for (BinaryTreeNode<Rectangle> room : rooms) {
			Rectangle dims = room.getElement();
			System.out.println("Height: " + dims.getHeight() +
					" Width: " + dims.getWidth() + " x: " + dims.getX() +
					" y: " + dims.getY() + " Area: " + dims.getArea());
			
			for(int i = dims.getX() + 1; i < (dims.getX() - 1) + dims.getWidth(); i++) {
				for (int j = dims.getY() + 1; j < (dims.getY() - 1) + dims.getHeight(); j++) {
					tileChars[i][j] = '0';
				}
			}
			
		}
		
		System.out.println("width: " + width);
		System.out.println("height: " + height);
		
		pw.println(width);
		pw.println(height);
		
		for(int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pw.write(tileChars[i][j]);
			}
			pw.write('\n');
		}
		
		pw.close();
				
	}
	
	public void splitNode(BinaryTreeNode<Rectangle> node, int splitNum, int splitMax) {
		if (splitNum == splitMax) {
			return;
		} else {
			Rectangle rect = node.getElement();
			Rectangle[] halves = rect.split();
			node.setLeaf(false);
			BinaryTreeNode<Rectangle> left = new BinaryTreeNode<Rectangle>(halves[0]);
			BinaryTreeNode<Rectangle> right = new BinaryTreeNode<Rectangle>(halves[1]);
			node.setLeft(left);
			node.setRight(right);
			splitNum++;
			splitNode(left, splitNum, splitMax);
			splitNode(right, splitNum, splitMax);
		}
	}
	
	public static LinkedList<BinaryTreeNode<Rectangle>>
		getLeaves(BinaryTree<Rectangle> bsp) {
		LinkedList<BinaryTreeNode<Rectangle>> queue =
				new LinkedList<BinaryTreeNode<Rectangle>>();
		LinkedList<BinaryTreeNode<Rectangle>> ret =
				new LinkedList<BinaryTreeNode<Rectangle>>();
		
		int nodesInCurrentLevel = 1;
		int nodesInNextLevel = 0;
		
		queue.push(bsp.getRoot());
		while (!queue.isEmpty()) {
			BinaryTreeNode<Rectangle> cur = queue.pollLast();
			nodesInCurrentLevel--;
			if (cur != null) { 
				if (cur.isLeaf()) {
					ret.addFirst(cur);
				}
				queue.push(cur.getLeft());
				queue.push(cur.getRight());
				nodesInNextLevel += 2;
			}
			if (nodesInCurrentLevel == 0) {
				nodesInCurrentLevel = nodesInNextLevel;
				nodesInNextLevel = 0;
			}
		}
		return ret;		
	}
	
}
