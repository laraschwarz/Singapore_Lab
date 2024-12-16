public class Node<E> {
  private E data;
  private Node<E> next;
  private Node<E> prev;

  public Node(E data) {
    this.data = data;
  }

  public E get() {
    return data;
  }

  public void set(E data) {
    this.data = data;
  }

  public Node<E> next() {
    return next;
  }

  public void setNext(Node<E> next) {
    this.next = next;
  }

  public Node<E> prev() {
    return prev;
  }

  public void setPrev(Node<E> prev) {
    this.prev = prev;
  }

  public String toString() {
    return (String) get();
  }
}