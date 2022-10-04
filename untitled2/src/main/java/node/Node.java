package node;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public String val;
    public List<Node> ch;

    public Node(String val) {
        this.val = val;
        ch = new ArrayList<>();
    }

    public Node() {
    }
}
