public class Graph<E> {
    private MyHashMap<E, MyHashMap<E, Integer>> graph;
    private int size;
    private E start;
    private MyHashSet<E> visited = new MyHashSet<E>();

    public Graph() {
        graph = new MyHashMap<E, MyHashMap<E, Integer>>();
        size = 0;
        start = null;
    }

    public void add(E vertex) {
        graph.put(vertex, new MyHashMap<E, Integer>());
        size++;

        if (size == 1) {
            start = vertex;
        }
    }
    
    public MyHashSet<E> keySet(){
        return graph.keySet();
    }

    public void addEdge(E v1, E v2, int i) {
        graph.get(v1).put(v2, i);
        graph.get(v2).put(v1, i);
    }

    public MyHashMap<E, Integer> getEdges(E data){
        return graph.get(data);
    }

    public void remove(E vertex) {
        graph.remove(vertex);

        DLList<E> list = graph.keySet().toDLList();

        for (int i = 0; i < list.size(); i++) {
            MyHashSet<E> set = graph.get(list.get(i)).keySet();
            if (set.contains(vertex)) {
                graph.get(i).remove(vertex);
            }
        }

        size--;
    }

    public String toString() {
        String contents = "";

        DLList<E> list = graph.keySet().toDLList();

        for (int i = 0; i < list.size(); i++) {
            contents += list.get(i) + " -> " + graph.get(list.get(i)) + "\n";
        }

        return contents;
    }

    public String bsearch(E data) {
        MyHashSet<E> visited = new MyHashSet<E>();
        MyHashSet<E> to_visit = new MyHashSet<E>();

        to_visit.add(start);

        while (visited.size() < size) {

            DLList<E> list = to_visit.toDLList();

            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");

                if (list.get(i).equals(data)) {
                    return list.get(i) + ": " + graph.get(list.get(i));
                }
                visited.add(list.get(i));
            }

            MyHashSet<E> new_list = new MyHashSet<E>();

            for (int i = 0; i < list.size(); i++) {
                DLList<E> list2 = graph.get(list.get(i)).keySet().toDLList();
                for (int j = 0; j < list2.size(); j++) {
                    if (!visited.contains(list2.get(j)))
                        new_list.add(list2.get(j));
                }
            }

            to_visit = new_list;
        }

        return "not found";
    }

    public String dsearch(E search) {
        return dsearch(start, search);
    }

    private String dsearch(E current, E search) {

        System.out.print(current + " ");

        if (!visited.contains(current)) {
            visited.add(current);
            if (current.equals(search)) {
                return current + ": " + graph.get(current);
            } else {
                MyHashSet<E> to_visit = new MyHashSet<E>();

                DLList<E> list1 = graph.get(current).keySet().toDLList();

                for (int i = 0; i < list1.size(); i++) {
                    if (!visited.contains(list1.get(i)))
                        to_visit.add(list1.get(i));
                }

                DLList<E> list2 = to_visit.toDLList();

                for (int i = 0; i < list2.size(); i++) {
                    if (!dsearch(list2.get(i), search).equals(null)) {
                        return dsearch(list2.get(i), search);
                    }
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int getWeight(E first, E second) {
        return graph.get(first).get(second);
    }

    public DLList<E> shortestPath(E start, E end) {
        MyHashSet<E> visited = new MyHashSet<E>();
        DLList<E> to_visit = new DLList<>();
        Table<E> t = new Table<E>();
        DLList<E> backwardsPath = new DLList<E>();
        DLList<E> path = new DLList<E>();

        t.add(0, start, null);
        E current = start;
        to_visit.add(current);
        
        while(to_visit.size() != 0){
            visited.add(current);
            to_visit.remove(current);
            DLList<E> connections = graph.get(current).keySet().toDLList();
            
            for (int i = 0; i < connections.size(); i++) {
                E neighbor = connections.get(i);
                int directWeight = getWeight(current, neighbor);
                int currentWeight = t.get(current).key();
                int totalWeight = directWeight + currentWeight;

                t.add(totalWeight, neighbor, current);

                if (!visited.contains(neighbor) && !to_visit.contains(neighbor)) {
                    to_visit.add(neighbor);
                }
            }

            E lowest = null;
            for(int i = 0; i<to_visit.size(); i++){
                if(i == 0){
                    lowest = to_visit.get(i);
                }
                else{
                    int currentDistance = t.get(to_visit.get(i)).key();
                    int lowestDistance = t.get(lowest).key();
                    if(currentDistance < lowestDistance){
                        lowest = to_visit.get(i);
                    }
                }
            }
            current = lowest;
        }

        backwardsPath.add(end);
        E prev = t.get(end).value();

        while (prev != null) {
            backwardsPath.add(prev);
            prev = t.get(prev).value();
        }

        for (int i = backwardsPath.size() - 1; i >= 0; i--) {
            path.add(backwardsPath.get(i));
        }

        return path;
    }

    public int totalWeight(E first, E second, Table<E> t) {
        int weight = getWeight(first, second);
        E prev = t.get(first).value();

        while (prev != null) {
            weight += getWeight(prev, first);
            first = prev;
            prev = t.get(prev).value();
        }
        return weight;
    }

}