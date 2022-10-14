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
        if(ans && root != null) {
            this.balanceTheTree(root);
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
                result = isResult(theNode);
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
                result = isResult(root);
            }
        }
        if(result && root != null) {
            this.balanceTheTree(root);
        }
        return result;
    }

    private boolean isResult(Node theNode) {
        boolean result;
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
        this.computeClone(this.root,this.root.father);
        return this;
    }

	private void computeClone(Node nodeToCopy, Node newFather) {
        if( nodeToCopy != null) {

            //create a new node
            Node newNode = nodeToCopy.clone();
            //set the new father
            newNode.father = newFather;

            //check if the node is the root
            if( nodeToCopy == this.root) {
                this.root = newNode;
            }
            //check if the node is the leftSon of the father
            else if( nodeToCopy == nodeToCopy.father.lSon) {
                newNode.father.lSon = newNode;
            }
            //check if the node is the rightSon of the father
            else if( nodeToCopy == nodeToCopy.father.rSon) {
                newNode.father.rSon = newNode;
            }

            //go to the left
            computeClone(nodeToCopy.lSon,newNode);
            //go to the right
            computeClone(nodeToCopy.rSon,newNode);
        }
    }

    public void showTree() {
        TreeDraw<E, T> treeD = new TreeDraw<E, T>(this.root);
        treeD.drawTree();
    }


    //------------------------

    //------------------------
    private int computeH ( Node theN ) {
        int hLNode, hRNode, hNode ;
        if ( theN != null ) {
            hLNode = computeH ( theN.lSon ) ;
            hRNode = computeH ( theN.rSon ) ;
            hNode = 1 + Math.max ( hLNode, hRNode ) ;
        }
        else hNode = 0 ;
        return hNode ;

    }
    private void rightRotation ( Node theN ){
        Node theN2 = theN.lSon;
        theN.lSon = theN2.rSon;
        if (theN2.rSon != null) {
            theN2.rSon.father = theN;
        }
        theN2.rSon = theN;
        ChangeNodeRotation(theN, theN2);

    }

    //------------------------

    //------------------------
    private void leftRotation ( Node theN ){
        Node theN2 = theN.rSon;
        theN.rSon = theN2.lSon;
        if (theN2.lSon != null) {
            theN2.lSon.father = theN;
        }
        theN2.lSon = theN;
        ChangeNodeRotation(theN, theN2);

    }

    private void ChangeNodeRotation(Node theN, Node theN2) {
        theN2.father = theN.father;
        theN.father = theN2;
        if (theN2.father != null) {
            if (theN2.father.lSon == theN) {
                theN2.father.lSon = theN2;
            } else {
                theN2.father.rSon = theN2;
            }
        } else {
            this.root = theN2;
        }
    }

    //------------------------

    //------------------------
    private void leftRightRotation (Node theN){
        leftRotation(theN.lSon);
        rightRotation(theN);
    }
    //------------------------

    private void rightLeftRotation (Node theN){
        rightRotation(theN.rSon);
        leftRotation(theN);

    }

    //------------------------
    private void balanceTheTree ( Node theN ) {
        if ( theN != null ) {
            int hL = computeH(theN.lSon);
            int hR = computeH(theN.rSon);
            if ( hL - hR > 1 ) {
                int hLL = computeH(theN.lSon.lSon);
                int hLR = computeH(theN.lSon.rSon);
                if ( hLL >= hLR ) {
                    rightRotation(theN);
                }
                else {
                    leftRightRotation(theN);
                }
            }
            else if ( hR - hL > 1 ) {
                int hRL = computeH(theN.rSon.lSon);
                int hRR = computeH(theN.rSon.rSon);
                if ( hRR >= hRL ) {
                    leftRotation(theN);
                }
                else {
                    rightLeftRotation(theN);
                }
            }
            balanceTheTree(theN.father);
        }
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
            node.father = null;
            node.lSon = null;
            node.rSon = null;
            return node;
        }

    }
}