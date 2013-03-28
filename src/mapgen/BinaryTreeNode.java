package mapgen;

public class BinaryTreeNode<T> {
	
	private BinaryTreeNode<T> left, right, parent;
	private T element;
	private boolean isLeaf;
	
	public BinaryTreeNode(T element) {
		this.element = element;
		this.isLeaf = true;
	}
	
	public T getElement() { return element; }
	
	public void setLeft(BinaryTreeNode<T> node) {
		this.left = node;
	}  
	
	public void setRight(BinaryTreeNode<T> node) {
		this.right = node;
	}  
	
	public void setParent(BinaryTreeNode<T> node) {
		this.parent = node;
	}  
	
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public boolean isLeaf() {
		return this.isLeaf;
	}
	
	public BinaryTreeNode<T> getLeft() { return left; }
	public BinaryTreeNode<T> getRight() { return right; }
	public BinaryTreeNode<T> getParent() { return parent; }
	
}
