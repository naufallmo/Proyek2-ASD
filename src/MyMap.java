public class MyMap<K, V> {
     private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyMap() {
        buckets = (Node<K, V>[]) new Node[16];
        size = 0;
    }

    public void put(K key, V value) {
        int index = indexFor(key);

        Node<K, V> current = buckets[index];

        while (current != null) {
            if (keyEquals(current.key, key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        buckets[index] = new Node<>(key, value, buckets[index]);
        size++;

        if (size > buckets.length * 0.75) {
            resize();
        }
    }

    public V get(K key) {
        Node<K, V> current = findNode(key);

        if (current == null) {
            return null;
        }

        return current.value;
    }

    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    public V remove(K key) {
        int index = indexFor(key);

        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (keyEquals(current.key, key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }

                size--;
                return current.value;
            }

            prev = current;
            current = current.next;
        }

        return null;
    }

    public MyList<K> keys() {
        MyList<K> hasil = new MyList<>();

        for (int i = 0; i < buckets.length; i++) {
            Node<K, V> current = buckets[i];

            while (current != null) {
                hasil.add(current.key);
                current = current.next;
            }
        }

        return hasil;
    }

    public MyList<V> values() {
        MyList<V> hasil = new MyList<>();

        for (int i = 0; i < buckets.length; i++) {
            Node<K, V> current = buckets[i];

            while (current != null) {
                hasil.add(current.value);
                current = current.next;
            }
        }

        return hasil;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<K, V> findNode(K key) {
        int index = indexFor(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (keyEquals(current.key, key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private boolean keyEquals(K a, K b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    private int indexFor(K key) {
        int hash = 0;

        if (key != null) {
            hash = key.hashCode();
        }
        return (hash & 0x7fffffff) % buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] lama = buckets;
        buckets = (Node<K, V>[]) new Node[lama.length * 2];

        for (int i = 0; i < lama.length; i++) {
            Node<K, V> current = lama[i];

            while (current != null) {
                Node<K, V> next = current.next;
                int index = indexFor(current.key);
                current.next = buckets[index];
                buckets[index] = current;

                current = next;
            }
        }
    }
}