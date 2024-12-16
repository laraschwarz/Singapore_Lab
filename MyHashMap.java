public class MyHashMap<K, V> {
  private Object[] hashArray;
  private int size;
  private MyHashSet<K> keySet;

  public MyHashMap() {
    hashArray = new Object[1000000];
    size = 0;
    keySet = new MyHashSet<K>();
  }

  @SuppressWarnings("unchecked")
  public V put(K key, V value) {
    V value2 = (V) hashArray[key.hashCode()];
    hashArray[key.hashCode()] = value;
    // System.out.println(hashArray[key.hashCode()]);
    // System.out.println(key);
    keySet.add(key);
    // System.out.println(keySet);
    size++;
    return value2;
  }

  @SuppressWarnings("unchecked")
  public V get(Object o) {
    K key = (K) o;
    return (V) hashArray[key.hashCode()];
  }

  @SuppressWarnings("unchecked")
  public V remove(Object o) {
    K key = (K) o;
    if (key.hashCode() < hashArray.length) {
      V value = (V) hashArray[key.hashCode()];
      hashArray[key.hashCode()] = null;
      size--;
      keySet.remove(key);
      return value;
    }
    return null;
  }

  public int size() {
    return size;
  }

  public MyHashSet<K> keySet() {
    return keySet;
  }

  // public String toString1() {
  //   String result = "";

  //   for (int i = 0; i < size - 1; i++) {
  //     if (size == 0) {
  //       return "[]";
  //     }
  //     if (hashArray[i] != null) {
  //       result += hashArray[i] + ", ";
  //     }
  //   }
  //   if (size > 0) {// last item shouldn't have a comma
  //     result += hashArray[size - 1];
  //   }
  //   return "[" + result + "]";
  // }

  public String toString() {
    String result = "";

    for (int i = 0; i < size; i++) {
      result += keySet().toDLList().get(i) + ", ";
    }
    
    return "[" + result + "]";
  }

  

  

}