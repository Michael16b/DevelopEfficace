package datastruct;

public class BinaryTreeTable<E extends Comparable<E>, T> implements Table<E, T> {

	private Node root = null;


    public BinaryTreeTable() {
    	root = null;
    }

    @Override
    public T select(E key) {
        if(key == null) {
            throw new IllegalArgumentException("La clï¿½e est null");
        }
        Node n = findNode(root,key);
        T ret;
        if(n==null) {
            ret=null;
        } else {
            ret = n.theValue;
        }
        return ret;
    }

    private Node findNode ( Node theNode, E key ) {
        if(key == null) {
            throw new IllegalArgumentException("La clï¿½e est null");
        }
        Node n = null;
        if(theNode != null) {
            if(theNode.key == key) {
                n=theNode;
            } else if (theNode.key.compareTo(key)<0){
                n=findNode(theNode.rSon,key);
            } else {
                n=findNode(theNode.lSon,key);
            }
        }
        return n;
    }



    @Override
    public boolean insert(E key, T data) throws IllegalArgumentException {
        if(key == null) {
            throw new IllegalArgumentException("La cle est null");
        }
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
        while( (theNode.rSon != null || theNode.lSon != null) && theNode.key != key) {
            if (theNode.key.compareTo(key) < 0 ) {
                if (theNode.rSon == null) {
                    break;
                } else {
                    theNode = theNode.rSon;
                }
            } else {
                if (theNode.lSon == null) {
                    break;
                } else {
                    theNode = theNode.lSon;
                }
            }
        }
        if (theNode.key == key) {
            theNode.theValue = null;
        }
        return theNode;
    }


    @Override
    public boolean delete (E key) {
        if (root == null) {
            return false;
        }
        boolean result = false;
        Node theNode = findNode(this.root,key);
        if (theNode != root) {
            if (theNode.lSon == null && theNode.rSon == null) {
                if (theNode.father.lSon == theNode) {
                    theNode.father.lSon = null;
                } else {
                    theNode.father.rSon = null;
                }
                result = true;
            } else if(theNode.lSon == null || theNode.rSon == null) {
                if (theNode.lSon != null) {
                    if (theNode.father.lSon == theNode) {
                        theNode.father.lSon = theNode.rSon;
                    } else {
                        theNode.father.rSon = theNode.rSon;
                    }
                } else {
                    if (theNode.father.lSon == theNode) {
                        theNode.father.lSon = theNode.lSon;
                    } else {
                        theNode.father.rSon = theNode.lSon;
                    }
                    result = true;
                }

            } else {
                Node theNode2 = theNode.rSon;
                while (theNode2.lSon != null) {
                    theNode2 = theNode2.lSon;
                }
                theNode.key = theNode2.key;
                theNode.theValue = theNode2.theValue;
                if (theNode2.father.lSon == theNode2) {
                    theNode2.father.lSon = theNode2.rSon;
                } else {
                    theNode2.father.rSon = theNode2.rSon;
                }
                result = true;
            }
        } else {
            if (root.lSon == null && root.rSon == null) {
                root = null;
                result = true;
            } else if (root.lSon == null || root.rSon == null) {
                if (root.lSon != null) {
                    root = root.lSon;
                } else {
                    root = root.rSon;
                }
                result = true;
            } else {
                Node theNode2 = root.rSon;
                while (theNode2.lSon != null) {
                    theNode2 = theNode2.lSon;
                }
                root.key = theNode2.key;
                root.theValue = theNode2.theValue;
                if (theNode2.father.lSon == theNode2) {
                    theNode2.father.lSon = theNode2.rSon;
                } else {
                    theNode2.father.rSon = theNode2.rSon;
                }
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String ret = this.getInfo(this.root) ;
        return ret;
    }

    private String getInfo ( Node theN ) {
        String infosLNode, infosRNode, infosNode ;
        String ret ;
        if ( theN != null ) {
            infosLNode = getInfo ( theN.lSon ) ;
            infosRNode = getInfo ( theN.rSon ) ;
            infosNode = new String ( "\nclé=" + theN.key.toString() + "\tdata=" +
                    theN.theValue.toString() ) ;
            ret = new String ( infosLNode + infosNode + infosRNode ) ;
        }
        else ret = new String ("") ;
        return ret ;
    }

    // Utilisez computeClone() pour copier les noeuds de l'arbre
    public BinaryTreeTable<E, T> clone() {
        BinaryTreeTable<E, T> ret = new BinaryTreeTable<E, T>();
        computeClone(this.root, ret.root);
        return ret;
    }

	private void computeClone(Node nodeToCopy, Node newFather) {
        if (nodeToCopy != null) {
            Node newNode = new Node(nodeToCopy.theValue, nodeToCopy.key);
            newNode.father = newFather;
            if (nodeToCopy.theValue != null && newFather != null) {
                if (nodeToCopy.lSon != null ) {
                    newNode.lSon =  new Node(nodeToCopy.lSon.theValue, nodeToCopy.lSon.key);
                } else if (nodeToCopy.rSon != null) {
                    newNode.rSon = new Node(nodeToCopy.rSon.theValue, nodeToCopy.rSon.key);
                }
            }
            if (nodeToCopy.lSon != null || nodeToCopy.rSon != null) {
                computeClone(nodeToCopy.lSon, newNode);
                computeClone(nodeToCopy.rSon, newNode);
            } else {
                if (newFather.lSon != null) {
                    newFather.lSon = newNode;
                } else {
                    newFather.rSon = newNode;
                }
                computeClone(null, newNode);

            }
        }

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