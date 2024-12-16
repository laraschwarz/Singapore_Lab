public class Data<E> {

    private int key;
    private E value;

    public Data(int newKey, E previous) {
        key = newKey;
        value = previous;
    }

    public void add(int newKey, E previous) {
        if (newKey < key) {
            this.key = newKey;
            value = previous;
        }
    }

    public int key() {
        return key;
    }

    public E value() {
        return value;
    }
    

    public String toString(){
        return key + " - " + value;
    }
}
