package exercises;

import java.util.ArrayList;
import java.util.List;

public class QuadTree<T extends Boundable> {

    public static final int DEFAULT_MAX_DEPTH = 5;

    private int maxDepth;

    private Node<T> root;

    private Rectangle bounds;

    private int count;

    public QuadTree(int width, int height) {
        this(width, height, DEFAULT_MAX_DEPTH);
    }

    public QuadTree(int width, int height, int maxDepth) {
        this.root = new Node<T>(0, 0, width, height);
        this.bounds = this.root.getBounds();
        this.maxDepth = maxDepth;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    private void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getCount() {
        return this.count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public boolean insert(T item) {
        if (!item.getBounds().isInside(this.bounds)) {
            return false;
        }

        int depth = 1;
        Node<T> crntNode = this.root;
        while (crntNode.getChildren() != null) {
            int quadrant = getQuadrant(crntNode, item.getBounds());
            if (quadrant == -1) {
                break;
            }

            crntNode = crntNode.getChildren()[quadrant];
            depth++;
        }

        crntNode.getItems().add(item);
        this.split(crntNode, depth);
        this.count++;

        return true;
    }

    private void split(Node<T> crntNode, int depth) {
        if (!(crntNode.shouldSplit() && depth < this.maxDepth)) {
            return;
        }

        int leftWidth = crntNode.getBounds().getWidth() / 2;
        int rightWidth = crntNode.getBounds().getWidth() - leftWidth;
        int topHeight = crntNode.getBounds().getHeight() / 2;
        int bottomHeight = crntNode.getBounds().getHeight() - topHeight;

        crntNode.setChildren(new Node[4]);
        crntNode.getChildren()[0] = new Node<T>(crntNode.getBounds().getMidX(), crntNode.getBounds().getY1(), rightWidth, topHeight);
        crntNode.getChildren()[1] = new Node<T>(crntNode.getBounds().getX1(), crntNode.getBounds().getY1(), leftWidth, topHeight);
        crntNode.getChildren()[2] = new Node<T>(crntNode.getBounds().getX1(), crntNode.getBounds().getMidY(), leftWidth, bottomHeight);
        crntNode.getChildren()[3] = new Node<T>(crntNode.getBounds().getMidX(), crntNode.getBounds().getMidY(), rightWidth, bottomHeight);

        for (int i = 0; i < crntNode.getItems().size(); i++) {
            T item = crntNode.getItems().get(i);
            int quadrant = getQuadrant(crntNode, item.getBounds());
            if (quadrant != -1) {
                crntNode.getItems().remove(i);
                crntNode.getChildren()[quadrant].getItems().add(item);
                i--;
            }
        }

        for (Node<T> child : crntNode.getChildren()) {
            this.split(child, depth + 1);
        }
    }

    public List<T> report(Rectangle bounds) {
        List<T> collisionCandidates = new ArrayList<T>();

        this.getCollisionCandidates(this.root, bounds, collisionCandidates);

        return collisionCandidates;
    }

    private void getCollisionCandidates(Node<T> crntNode, Rectangle bounds, List<T> collisionCandidates) {
        int quadrant = getQuadrant(crntNode, bounds);

        if (quadrant == -1) {
            getSubtreeContents(crntNode, bounds, collisionCandidates);
        } else {
            if (crntNode.getChildren() != null) {
                getCollisionCandidates(crntNode.getChildren()[quadrant], bounds, collisionCandidates);
            }
            collisionCandidates.addAll(crntNode.getItems());
        }
    }

    private void getSubtreeContents(Node<T> crntNode, Rectangle bounds, List<T> collisionCandidates) {
        if (crntNode.getChildren() != null) {
            for (Node<T> child : crntNode.getChildren()) {
                if (child.getBounds().intersects(bounds)) {
                    this.getSubtreeContents(child, bounds, collisionCandidates);
                }
            }
        }
        collisionCandidates.addAll(crntNode.getItems());
    }

    private int getQuadrant(Node<T> crntNode, Rectangle bounds) {
        if (crntNode.getChildren() == null)
        {
            return -1;
        }

        for (int quadrant = 0; quadrant < 4; quadrant++)
        {
            if (bounds.isInside(crntNode.getChildren()[quadrant].getBounds()))
            {
                return quadrant;
            }
        }

        return -1;
    }
}

