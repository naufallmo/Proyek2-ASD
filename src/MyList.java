public class MyList<T> {
    private Object[] data;
    private int size;

    public MyList() {
        data = new Object[8];
        size = 0;
    }

    public void add(T item) {
        if (size == data.length) {
            Object[] baru = new Object[data.length * 2];

            for (int i = 0; i < size; i++) {
                baru[i] = data[i];
            }

            data = baru;
        }

        data[size] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (T) data[index];
    }

    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        data[index] = item;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T hasil = (T) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;

        return hasil;
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                if (item == null) {
                    return i;
                }
            } else if (data[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        String hasil = "[";

        for (int i = 0; i < size; i++) {
            hasil += data[i];

            if (i < size - 1) {
                hasil += ", ";
            }
        }

        hasil += "]";
        return hasil;
    }
}