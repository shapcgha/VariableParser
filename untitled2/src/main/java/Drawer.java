import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import static guru.nidi.graphviz.model.Factory.*;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import node.Node;

import java.io.File;
import java.io.IOException;

public class Drawer {
    static long id;
    static public void draw(Node root, String fileName) throws IOException {
        id = 0;
        MutableGraph g = mutGraph("res").setDirected(true).add(recursiveDraw(root));
        Graphviz.fromGraph(g).width(1600).render(Format.PNG).toFile(new File("result/" + fileName));
    }

    static private MutableNode recursiveDraw(Node node) {
        MutableNode mutNode = mutNode(Long.toString(id++)).add(Label.lines(node.val));
        if (node.ch != null) {
            node.ch.forEach(child -> mutNode.addLink(recursiveDraw(child)));
        }
        return mutNode;
    }
}
