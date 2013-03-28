package mapgen;

public class BinaryTree<T> {

	private BinaryTreeNode<T> root;
	
	public BinaryTree(T rootElement) {
		this.root = new BinaryTreeNode<T>(rootElement);
		root.setLeaf(true);
	}
	
	public BinaryTreeNode<T> getRoot() { return root; }
	
}