public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        System.out.println(bst.getRoot());
        bst.insert(6);
        System.out.println(bst.contains(6));
    }
}
