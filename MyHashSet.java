import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashSet {
    private final int keySpace;
    private final ArrayList<Bucket> buckets;

    public MyHashSet() {
        this.keySpace = 2069;
        this.buckets = new ArrayList<>();

        for (int i = 0; i < this.keySpace; i++) {
            this.buckets.add(new Bucket());
        }
    }

    public void add(int key) {
        int hash = key % this.keySpace;
        this.buckets.get(hash).add(key);
    }

    public void remove(int key) {
        int hash = key % this.keySpace;
        this.buckets.get(hash).remove(key);
    }

    public boolean contains(int key) {
        int hash = key % this.keySpace;
        return this.buckets.get(hash).contains(key);
    }

    private static class Bucket {
        private final LinkedList<Integer> linkedList;

        public Bucket() {
            linkedList = new LinkedList<>();
        }

        private void add(Integer key) {
            int index = linkedList.indexOf(key);

            if (index == -1) {
                linkedList.add(key);
            }
        }

        private void remove(Integer key) {
            linkedList.remove(key);
        }

        private boolean contains(Integer key) {
            int index = linkedList.indexOf(key);
            return index != -1;
        }
    }
}
