/*
 * Name: Michael Ren
 * PID:  A16430317
 */

import java.util.*;

/**
 * Binary search tree implementation.
 *
 * @author Michael Ren
 * @since  8/29/24
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            /* TODO */
            this.left=left;
            this.right=right;
            this.dataList=dataList;
            this.key=key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            /* TODO */
            this.left=left;
			this.right=right;
			this.key=key;
            this.dataList=new LinkedList<>();
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            /* TODO */
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            /* TODO */
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            /* TODO */
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            /* TODO */
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            /* TODO */
            this.left=newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            /* TODO */
            this.right=newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            /* TODO */
            this.dataList=newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            /* TODO */
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            /* TODO */
            if (this.dataList.contains(data))
            {this.dataList.remove(data);
            return true;}
            else
            return false;
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        /* TODO */
        root=null;
		nelems=0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        /* TODO */
        if (nelems!=0)
        return this.root;
        else
        return null;

    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        /* TODO */
        return nelems;
    }

    /**
     * Insert a key into BST
     *
     * @param key
     * @return true if insertion is successful and false otherwise
     * @throws throws nullpointer exception if key is null
     */
    public boolean insert(T key) {
        /* TODO */
        if (key==null)
        {throw new NullPointerException("key is null");}
        if (this.findKey(key))
        {return false;}
        if (this.nelems==0)
		 {root=new BSTNode(null,null,new LinkedList<>(),key);
		  nelems++;
		  return true;
		 }
		 return inserthelper(root,key);
    }

    /*
     * Private helper method for insert helper
     * @param node
     * @param key
     * @return true if inserted, else false
     * 
     */
    private boolean inserthelper(BSTNode node, T key)
	 {
		if (key.compareTo(node.key)<0)
		{
			if (node.left!=null)
			{return inserthelper(node.left, key);}
			else {node.left=new BSTNode(null,null,new LinkedList<>(),key);
				nelems++;
				return true;}	
		}

		else if (key.compareTo(node.key)>0)
		{
            if (node.right!=null)
			{return inserthelper(node.right,key);}
			else {node.right=new BSTNode(null,null,new LinkedList<>(),key);
				nelems++;
				return true;}

		}
		
		else {return false;}
	 }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        /* TODO */
        if (nelems==0)
        {return false;}
        if (key==null)
        {throw new NullPointerException("key is null");}
        return findKeyhelper(root, key);
    }

    /**
     * Private helper method for findKey method
     *
     * @param key To be searched
     * @param node node in which findkeyhelper iterates over
     * @return True if the 'key' is found, false otherwise
     */
    private boolean findKeyhelper(BSTNode node, T key)
	{

		if (key.compareTo(node.key)<0)
		{
			if (node.left!=null)
			{return findKeyhelper(node.left, key);}
			else if(node.key.compareTo(key)==0)
				{return true;}
			else {return false;}	
		}

		else if (key.compareTo(node.key)>0)
		{
            if (node.right!=null)
			{return findKeyhelper(node.right,key);}
			else if(node.key.compareTo(key)==0)
				{return true;}
			else {return false;}

		}
		
		else {return true;}
	}

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If eaither key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        /* TODO */
        if (root==null)
        {return;}
        if (key==null||data==null)
        {throw new NullPointerException("key or data is null");}
        if (!this.findKey(key))
        {throw new IllegalArgumentException("key not found");}
        insertDatahelper(root, key, data);
    }

    /**
     * private helper method for insertData
     *
     * @param key To be searched
     * @param data To be inserteed
     * @param node at which recursion is applied
     * @return True if data is successfully inserted, false otherwise
     */
    private boolean insertDatahelper(BSTNode node, T key, T data)
	 {
 
		 if (key.compareTo(node.key)<0)
		 {
			 if (node.left!=null)
			 {return insertDatahelper(node.left, key,data);}
			 else {return false;}	
		 }
 
		 else if (key.compareTo(node.key)>0)
		 {
			 if (node.right!=null)
			 {return insertDatahelper(node.right,key,data);}
			 else {return false;}
 
		 }
		 
		 else {
			   node.dataList.add(data);
			   return true;
			}
	 }
    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        /* TODO */
        if (root==null)
        {return new LinkedList<>();}
        if (key==null)
        {throw new NullPointerException("key is null");}
        if (!this.findKey(key))
        {throw new IllegalArgumentException("key not found");}
        return findDataListhelper(root, key);
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @param node node corresponding to key
     * @return LinkedList of the node whose key value is 'key'
     */
    private LinkedList<T> findDataListhelper(BSTNode node, T key)
	 {
 
		 if (key.compareTo(node.key)<0)
		 {
			 if (node.left!=null)
			 {return findDataListhelper(node.left,key);}
			 else {return null;}	
		 }
 
		 else if (key.compareTo(node.key)>0)
		 {
			 if (node.right!=null)
			 {return findDataListhelper(node.right,key);}
			 else {return null;}
 
		 }
		 
		 else {
			   return node.dataList;
			}
	 }
 

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        /* TODO */
        if (this.root==null)
		 return -1;

		 return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        /* TODO */
        if (root==null)
		{return -1;}
		int leftSum=findHeightHelper(root.left);
		int rightSum=findHeightHelper(root.right); 

		return Math.max(leftSum, rightSum)+1;
    }

    /* * * * * BST Iterator * * * * */

    /*
     * Implements iterator using stack API, by pushing leftmost list after each pop
     * 
     */
    public class BSTree_Iterator implements Iterator<T> {
        
        Stack<BSTNode> stack;
        /*
         * Constructor for stack empty parameters
         * 
         */
        public BSTree_Iterator() {
            /* TODO */
             stack=new Stack<>();
             BSTNode node=root;
             while (node!=null)
             {
                stack.push(node);
                node=node.left;
             }
            
        }

         /*
         * private method for initializing left most path for stack
         * @return returns nothing
         * @param node takes in a node to begin the method. 
         */
        private void pushLeft(BSTNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

         /*
         * checks whether next element exists in iterator
         * @return returns true if element exists, else false
         */
        public boolean hasNext() {
            /* TODO */
            return stack.isEmpty();
        }

         /*
         * returns next element in iterator, deletes item from stack
         * @return returns the element at top of stack of type T
         */
        public T next() {
            /* TODO */
            if (!hasNext()) {
                throw new IllegalStateException("No next element");
            }

            BSTNode currentNode=stack.pop();
            T currentVal=currentNode.getKey();
            if(currentNode.right!=null)
            {pushLeft(currentNode.right);}

            return currentVal;
        }
    }

     /*
         * Constructor for iterator, allows for Junit test in BSTreeTester
         * returns iterator of type T
         */
    public Iterator<T> iterator() {
        /* TODO */
        return new BSTree_Iterator();
    
        
    }

    /* * * * * Extra Credit Methods * * * * */
     /*
         *Did not attempt
         * @return returns arraylist
         */
    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }
       /*
        *Did not attempt
        * @return returns element of Type T
        */
    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}