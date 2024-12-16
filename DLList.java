public class DLList<E> {
  private Node<E> head;
  private Node<E> tail;
  private int size;

  public DLList() {
    head = new Node<E>(null);
    tail = new Node<E>(null);
    head.setNext(tail);
    tail.setPrev(head);
    size = 0;
  }

  public Node<E> getNode(int index) {
    Node<E> current = head.next();
    for (int i = 0; i < index; i++) {
      current = current.next();
    }
    return current;
  }

  public void clear() {
    head = new Node<E>(null);
    tail = new Node<E>(null);
    head.setNext(tail);
    tail.setPrev(head);
    size = 0;
  }

  public E get(int index) {
    Node<E> node = getNode(index);
    if (node == null) {
      return null;
    }
    return node.get();
  }

  public void add(int index, E element) {
    Node<E> current = getNode(index);
    Node<E> node = new Node<E>(element);

    Node<E> before = current.prev();
    Node<E> after = current;

    before.setNext(node);
    node.setPrev(before);
    after.setPrev(node);
    size++;

  }

  public void add(E element) {
    Node<E> before = tail.prev();
    Node<E> after = tail;
    Node<E> node = new Node<E>(element);
    before.setNext(node);
    node.setPrev(before);
    after.setPrev(node);
    node.setNext(after);
    size++;
  }

  public int size() {
    return size;
  }

  public String toString() {
    String text = "";
    Node<E> current = head.next();
    while (current.next() != null) {
      text += current.get() + ", ";
      current = current.next();
    }
    return "[" + text + "]";
  }

  public void remove(Node<E> node) {
    node.prev().setNext(node.next());
    node.next().setPrev(node.prev());
    size--;
  }

  public E remove(int index) {
    Node<E> node = getNode(index);
    remove(node);
    return node.get();
  }

  public void remove(E element) {
    Node<E> current = head.next();
    while (current.next() != null) {
      if (current.get().toString().equals(element.toString())) {
        remove(current);
        return;
      }
      current = current.next();
    }
  }

  public boolean contains(E search) {
    Node<E> data = head;
    for (int i = 0; i < size; i++) {
      if (data.get() == search) {
        return true;
      }
      data = data.next();

    }
    return false;
  }

  public void set(int i, E element) {
    getNode(i).set(element);
  }

  public void scramble() {
    // go through nodes, for each node replace swap set next with a random one
    Node<E> current = head.next();
    while (current.get() != null) {
      int randex = (int) (Math.random() * size);// make rand int
      E temp = current.get();// make temp var
      current.set(this.get(randex));// set current to obj at rand int
      this.set(randex, temp);// move temp var to rand int
      current = current.next(); // increment
    }
  }
}
