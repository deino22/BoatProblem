package utils;

import agent.State;
import searchmethods.Node;

import java.util.HashMap;
import java.util.LinkedList;

public class NodeLinkedList extends LinkedList<Node> implements NodeCollection {
    private final HashMap<State, Node> contents;

    public NodeLinkedList() {
        super();
        this.contents = new HashMap<>(128);
    }
    @Override
    public void add(int index, Node element) {
        super.add(index, element);
        contents.put(element.GetState(), element);
    }
    @Override
    public void clear() {
        super.clear();
        contents.clear();
    }
    @Override
    public boolean remove(Object o) {
        if (o instanceof Node) {
            Node no = (Node) o;
            contents.remove(no.GetState());
        }
        return super.remove(o);
    }
    @Override
    public Node poll() {
        Node n = super.poll();
        contents.remove(n.GetState());
        return n;
    }
    public boolean add(Node n) {
        contents.put(n.GetState(), n);
        return super.add(n);
    }
    @Override
    public void addFirst(Node e) {
        super.addFirst(e);
        contents.put(e.GetState(), e);
    }

    @Override
    public void addLast(Node e) {
        super.addLast(e);
        contents.put(e.GetState(), e);
    }
    @Override
    public Node removeLast() {
        Node no = super.removeLast();
        contents.remove(no.GetState());
        return no;
    }
    @Override
    public boolean ContainsState(State e) {
        return contents.containsKey(e);
    }
}
