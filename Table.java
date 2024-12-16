public class Table<E> {
    MyHashMap<E, Data<E>> table;
    int size;
    
    public Table() {
        table = new MyHashMap<E,Data<E>>();
        size = 0;
    }
    

    public void add(int distance, E current, E previous) {
        // if (previous == null) {
        //     table.put(current,new Data<E>(0));
        // } else {
        //     System.out.println("table.get(current): " + table.get(current));

        //     // table.get(current).add(distance,previous);
        //     table.put(current, new Data<E>(distance, previous));
        // }

        if(table.get(current) == null){
            table.put(current, new Data<E>(distance, previous));
            size++;
        }
        else{
            table.get(current).add(distance, previous);
        }
        //size++;
    }

    public Data<E> get(E current){
        return table.get(current);
    }

    public String toString(){
        String text = "";
        DLList<E> list = table.keySet().toDLList();

        for(int i = 0; i<list.size(); i++){
            text += list.get(i).toString() + " - " + table.get(list.get(i)) + "\n";
        }
        return text;
    }

    public int size(){
        return size;
    }

    

}
