import java.util.Scanner;

 class Runner1 {
    public static void main(String[] args){
        Graph<String> graph = new Graph<String>();
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.add("D");
        graph.add("E");

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 3);
        graph.addEdge("C", "B", 1);
        graph.addEdge("C", "E", 9);
        graph.addEdge("B", "E", 11);
        graph.addEdge("B", "D", 2);
        graph.addEdge("D", "E", 1);

        while(run){
            // System.out.println("\nPick an option: \n1: View graph \n2: Add Node \n3: Add Edge \n4: Print shortest Path \n5: Quit");
            // int choice = sc.nextInt();

            // System.out.println();

            // if(choice == 1){
            //     System.out.println(graph);
            // }
            // else if(choice == 2){
            //     System.out.print("Enter Node to add: ");
            //     String add = sc.next();
            //     graph.add(add);
            // }
            // else if(choice == 3){
            //     System.out.print("Enter first node: ");
            //     String first = sc.next();
            //     System.out.print("Enter second node: ");
            //     String second = sc.next();
            //     System.out.print("Enter weight: ");
            //     int weight = sc.nextInt();
            //     graph.addEdge(first, second, weight);
            // }   
            // else if(choice == 4){
            //     System.out.print("Enter start node: ");
            //     String start = sc.next();
            //     System.out.print("Enter end node: ");
            //     String end = sc.next();
                
            //     System.out.println("Shortest Path: " + graph.shortestPath(start, end));
            // }
            // else if(choice == 5){
            //     run = false;
            // }
            System.out.print("Enter start node: ");
                String start = sc.next();
                System.out.print("Enter end node: ");
                String end = sc.next();
                
                System.out.println("Shortest Path: " + graph.shortestPath(start, end));
            System.out.println();
        }





        sc.close();
    }
}
