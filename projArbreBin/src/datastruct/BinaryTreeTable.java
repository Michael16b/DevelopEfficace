public class Node {
    // Attributs
    private Node lSon ; // fils gauche (null si pas de fils gauche)
    private Node rSon ; // fils droit (null si pas de fils droit)
    private Node father ; // père (null si le nœud est root)
    private T theValue ; // donnée stockée
    private E key ; // clé unique


    public Node (T theValue, E key) {
        this.lSon = null;
        this.rSon = null;
        this.father = null;
        this.theValue = theValue;
        this.key = key;
    }
    // Accesseurs
    public String getLabel() {
        return this.key;
    }
    public Node getLeft() {
        return this.lSon;
    }
    public Node getRight() {
        return this.rSon;
    }

    public Node clone() {
        Node node = New Node(this.theValue, this.key);
        node.father = this.father;
        node.lSon = this.lSon;
        node.rSon = this.rSon;
    }

}