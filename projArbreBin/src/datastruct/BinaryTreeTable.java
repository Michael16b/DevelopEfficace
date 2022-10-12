package datastruct;

import java.util.Objects;

public class BinaryTreeTable<E extends Comparable<E>, T> implements Table<E, T> {

	private Node root = null;


    public BinaryTreeTable() {
    	root = null;
    }

    @Override
    public T select(E key) {
        if (root == null) {
            return null;
        }
        Node theNode = findNode(root,key);
        if (theNode != null) {
            if (theNode.key == key) {
                return theNode.theValue;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


	private Node findNode (Node theNode, E key) {
        if (key.equals(theNode.key)) {
            return theNode;
        }
        if (theNode.lSon != null && theNode.rSon != null) {
            return null;
        }

        if (theNode.lSon != null) {
            if (key.equals(theNode.lSon.key)) {
                return findNode(theNode.lSon, key);
            }
        }
        if (theNode.rSon != null) {
            if (key.equals(theNode.rSon.key)) {
                return findNode(theNode.rSon, key);
            }
        }

        if (theNode.lSon != null && theNode.rSon != null) {
            if (key.compareTo(theNode.lSon.key) > 0 && key.compareTo(theNode.rSon.key) < 0) {
                return findNode(theNode.lSon, key);

            } else {
                if (key.compareTo(theNode.lSon.key) > 0 && key.compareTo(theNode.rSon.key) > 0) {
                    if (theNode.lSon.key.compareTo(theNode.rSon.key) > 0) {
                        return findNode(theNode.lSon, key);
                    } else {
                        return findNode(theNode.rSon, key);
                    }
                }
            }
        }
        return null;
    }



    @Override
    public boolean insert(E key, T data) {
    	boolean ans = false;
    	if (root == null) {
    		root = new Node(data,key);
    		ans = true;
    	} else {
    		Node nodeFather = seekFather(key);
            if (nodeFather.theValue != null) {
                Node nNode = new Node(data,key);
                if (nodeFather.rSon == null) {
                    nodeFather.rSon = nNode;
                } else {
                    nodeFather.lSon = nNode;
                }
                ans = true;
            }
    	}
    	return ans;
    }
    
	private Node seekFather (E key) {
    	Node theNode = root;
    	while (theNode.rSon != null && theNode.lSon != null) {
    		if ((theNode.lSon.key.compareTo(key) > 0 && theNode.rSon.key.compareTo(key) > 0) || (theNode.lSon.key.compareTo(key) < 0 && theNode.rSon.key.compareTo(key) < 0)) {
    			if (theNode.rSon.key.compareTo(theNode.lSon.key) > 0) {
                    if (theNode.rSon.key.compareTo(key) == 0) {
                        theNode = new Node(null, key);
                    } else {
                        theNode = theNode.rSon;
                    }
                } else {
                    if (theNode.lSon.key.compareTo(key) == 0) {
                        theNode = new Node(null, key);
                    } else {
    				    theNode = theNode.lSon;
                    }
    			}
    		}
    		else if (theNode.lSon.key.compareTo(key) > 0 && theNode.rSon.key.compareTo(key) < 0) {
    			theNode = theNode.lSon;
    		} else {
    			theNode = theNode.rSon;
    		}
    	}
        if (theNode != null) {
            if (theNode.key.compareTo(key) == 0) {
                theNode = new Node(null, key);
            }
        }
    	return theNode;
    	
    } 
    
    @Override
    public boolean delete (E key) {
        if (root == null) {
            return false;
        }
        Node theNode = findNode(this.root,key);
        theNode = null;
        return true;
    }
    
    
    public BinaryTreeTable<E, T> clone() {
    	return this;
    }
    
    @SuppressWarnings("unused")
	private void computeClone(Node nodeToCopy, Node newFather) {
        T theValue = null ; 
        E key = null ;
    	Node node = new Node(theValue, key);
    }
    
    public void showTree() {
    	
    }
    
    
   public class Node {
        // Attributs
        private Node lSon ; 
        private Node rSon ; 
        private Node father ; 
        private T theValue ; 
        private E key ; 


        public Node (T theValue, E key) {
            this.lSon = null;
            this.rSon = null;
            this.father = null;
            this.theValue = theValue;
            this.key = key;
        }
        // Accesseurs
        public String getLabel() {
            return this.key.toString();
        }
        public Node getLeft() {
            return this.lSon;
        }
        public Node getRight() {
            return this.rSon;
        }

        public Node clone() {
            Node node = new Node(this.theValue, this.key);
            node.father = this.father;
            node.lSon = this.lSon;
            node.rSon = this.rSon;
            return node;
        }

    }
}