package sr.unasat.liquidMoney.graphs.oilExport;

import sr.unasat.liquidMoney.graphs.dataStructures.queue.Queue;
import sr.unasat.liquidMoney.graphs.dataStructures.stack.Stack;
import sr.unasat.liquidMoney.graphs.entities.Order;
import sr.unasat.liquidMoney.graphs.graphRequirements.DistanceToParent;
import sr.unasat.liquidMoney.graphs.graphRequirements.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphExport {

    private int MAX_VERTICES;
    private final int INFINITY = 7777777;
    private Vertex vertexList[];
    private int adjacencyMatrix[][];
    private int totalVertices;
    private int totalVertsInTree;
    private DistanceToParent shortestPathData[];
    private int currentVertex;
    private int startToCurrent;
    private Queue queue;
    private Stack stack;
    private int distances[];

    public GraphExport(int MAX_VERTICES) {
        this.MAX_VERTICES = MAX_VERTICES;
        vertexList = new Vertex[MAX_VERTICES];
        adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        totalVertices = 0;
        totalVertsInTree = 0;
        for (int index = 0; index < MAX_VERTICES; index++){
            for (int index1 = 0; index1 < MAX_VERTICES; index1 ++){
                adjacencyMatrix[index][index1] = INFINITY;
            }
        }
        shortestPathData = new DistanceToParent[MAX_VERTICES];

        queue = new Queue();
        stack = new Stack(77);
        distances = new int[21];

    }

    public void addVertex(String vertexLabel){
        vertexList[totalVertices++] = new Vertex(vertexLabel);
    }

    public void addEdge(int source, int destination, int distance){
        adjacencyMatrix[source][destination] = distance;
    }

    public void removeVertex(int vertexToRemove) {
        if (vertexToRemove > totalVertices) {
            System.out.println("Vertex not present!");
        }
        else {
            while (vertexToRemove < totalVertices) {

                for(int i = vertexToRemove; i < vertexList.length -1; i++){
                    vertexList[i] = vertexList[i + 1];
                }

                for (int index = 0; index < totalVertices; ++index) {
                    adjacencyMatrix[index][vertexToRemove] = adjacencyMatrix[index][vertexToRemove - 1];
                }
                for (int index = 0; index < totalVertices; ++index) {
                    adjacencyMatrix[vertexToRemove][index] = adjacencyMatrix[vertexToRemove - 1][index];
                }
                vertexToRemove++;
            }
            totalVertices--;
        }
    }


    public void removeEdge(int source, int destination){
        if (adjacencyMatrix[source][destination] == INFINITY){
            System.out.println("Edge doesn't exist !!!");
        }else {
            adjacencyMatrix[source][destination] = INFINITY;
            System.out.println("Deleted edge successfully !!!");
        }
    }

    public void displayVertices(){
        for (Vertex vertex : vertexList) {
            System.out.println(vertex.label + " ");
        }
    }

    public void displayVertex(int vertex){
        System.out.println(vertexList[vertex].label);
    }

    public void displayAdjacencyMatrix(){
        for (int index = 0; index < adjacencyMatrix.length; index++) {
            for (int index1 = 0; index1 < adjacencyMatrix.length; index1++) {
                System.out.println(vertexList[index].label + " to " + vertexList[index1].label +
                        " = " + adjacencyMatrix[index][index1]);
            }
        }
    }

    public void displayEdge(int source, int destination){
        if (adjacencyMatrix[source][destination] == INFINITY){
            System.out.println("Edge doesn't exist !!!");
        }else {
            System.out.println(vertexList[source].label + " to " + vertexList[destination].label + " = " +
                    adjacencyMatrix[source][destination]);
        }
    }

    public int verticesIndexes(String countryName){
        int vertexIndex;
        switch (countryName){
            case "Suriname":
                vertexIndex = 0;
                break;
            case "Guatemala":
                vertexIndex = 1;
                break;
            case "Frans-Guyana":
                vertexIndex = 2;
                break;
            case "Brazil":
                vertexIndex = 3;
                break;
            case "Guyana":
                vertexIndex = 4;
                break;
            case "Costa Rica":
                vertexIndex = 5;
                break;
            case "America":
                vertexIndex = 6;
                break;
            case "Canada":
                vertexIndex = 7;
                break;
            case "Honduras":
                vertexIndex = 8;
                break;
            case "Venezuela":
                vertexIndex = 9;
                break;
            case "Colombia":
                vertexIndex = 10;
                break;
            case "Peru":
                vertexIndex = 11;
                break;
            case "Bolivia":
                vertexIndex = 12;
                break;
            case "Paraguay":
                vertexIndex = 13;
                break;
            case "Uruguay":
                vertexIndex = 14;
                break;
            case "Argentine":
                vertexIndex = 15;
                break;
            case "Nicaragua":
                vertexIndex = 16;
                break;
            case "Panama":
                vertexIndex = 17;
                break;
            case "Ecuador":
                vertexIndex = 18;
                break;
            case "Chili":
                vertexIndex = 19;
                break;
            case "Mexico":
                vertexIndex = 20;
                break;
            default:
                vertexIndex = 0;
        }
        return vertexIndex;
    }


    //    Breadth First Search classic --------------------------------------------
    public void breadthFirstSearch(){
        vertexList[0].visited = true;
        displayVertex(0);
        queue.insert(0);
        int neighbourVertex;

        while (!queue.isEmpty()){
            int completedVertex = queue.remove();
            while ((neighbourVertex = getAdjacentUnvisitedVertex(completedVertex)) != -1){
                vertexList[neighbourVertex].visited = true;
                displayVertex(neighbourVertex);
                queue.insert(neighbourVertex);
            }
        }
        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++){
            vertexList[vertexIndex].visited = false;
        }
    }

    public int getAdjacentUnvisitedVertex(int vertex){
        for (int neighbour = 0; neighbour < totalVertices; neighbour++){
            if (adjacencyMatrix[vertex][neighbour] < INFINITY && !vertexList[neighbour].visited){
                return neighbour;
            }
        }
        return -1;
    }

    //    Depth First Search to check is there is a path from given source to destination -
    public void depthFirstSearchIsThereAPath(String destinationName){
        int destination = verticesIndexes(destinationName);

        vertexList[0].visited = true;
        stack.push(0);

        while (!stack.isEmpty()){
            int neighbour = getAdjacentUnvisitedVertex(stack.peek());
            if (neighbour == destination){
                System.out.println("There is an export route possible from " +
                        vertexList[0].label + " to " + vertexList[destination].label);
                return;
            }
            if (neighbour == -1){
                stack.pop();
            }else {
                vertexList[neighbour].visited = true;
                stack.push(neighbour);
            }
        }
        for (int vertex = 0; vertex < totalVertices; vertex++){
            vertexList[vertex].visited = false;
        }

    }

//    Dijkstra minimum path -------------------------------------------------
    public void minimumPathDijkstra(List<String> destinations) {
        int[] minimumWeights = new int[totalVertices];
        boolean[] visited = new boolean[totalVertices];

        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
            minimumWeights[vertexIndex] = Integer.MAX_VALUE;
            visited[vertexIndex] = false;
        }

        minimumWeights[0] = 0;
        int[] parents = new int[totalVertices];
        parents[0] = -1;

        for (int index = 0; index < totalVertices; index++) {
            int neighbour = -1;
            int minumum = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
                if (!visited[vertexIndex] && minimumWeights[vertexIndex] < minumum) {
                    neighbour = vertexIndex;
                    minumum = minimumWeights[vertexIndex];
                }
            }

            visited[neighbour] = true;

            for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[neighbour][vertexIndex];
                if (edgeDistance > 0 && ((minumum + edgeDistance) < minimumWeights[vertexIndex])) {
                    parents[vertexIndex] = neighbour;
                    minimumWeights[vertexIndex] = minumum + edgeDistance;
                }
            }
        }
        display(destinations, minimumWeights, parents);
    }

    private void display(List<String> destinations, int[] distances, int[] parents) {
        List<Integer> destinationIndexes = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();
        for (String destination : destinations) {
            int vertexIndex = verticesIndexes(destination);
            destinationIndexes.add(vertexIndex);
        }

        for (Integer destinationIndex : destinationIndexes) {
            weights.add(distances[destinationIndex]);
        }

        System.out.print("Shortest path from Suriname is ");
        int min = findMin(weights);

        for (int vertexIndex = 0; vertexIndex < distances.length; vertexIndex++) {
            if (distances[vertexIndex] == min) {
                System.out.print(distances[vertexIndex] + "km, and it leads to ");
                System.out.print(vertexList[vertexIndex].label + "\nThe path is: ");
                displayPath(vertexIndex, vertexIndex, parents);
            }
        }
    }

    public Integer findMin(List<Integer> list){
        return list.stream().reduce(Integer::min).get();
    }

    private void displayPath(int destination, int currentVertex, int[] parents) {
        if (currentVertex == -1) {
            return;
        }
        displayPath(destination, parents[currentVertex], parents);

        if (currentVertex == destination){
            System.out.print(vertexList[currentVertex].label + "\n");
        }else{
            System.out.print(vertexList[currentVertex].label + " - ");
        }
    }

//    Dijkstra with given destination --------------------------------------------
    public void minimumPathDijkstra(String destination) {
        int destinationIndex = verticesIndexes(destination);
        int[] minimumWeights = new int[totalVertices];
        boolean[] visited = new boolean[totalVertices];

        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
            minimumWeights[vertexIndex] = Integer.MAX_VALUE;
            visited[vertexIndex] = false;
        }

        minimumWeights[0] = 0;
        int[] parents = new int[totalVertices];
        parents[0] = -1;

        for (int index = 0; index < totalVertices; index++) {
            int neighbour = -1;
            int minumum = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
                if (!visited[vertexIndex] && minimumWeights[vertexIndex] < minumum) {
                    neighbour = vertexIndex;
                    minumum = minimumWeights[vertexIndex];
                }
            }

            visited[neighbour] = true;

            for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[neighbour][vertexIndex];
                if (edgeDistance > 0 && ((minumum + edgeDistance) < minimumWeights[vertexIndex])) {
                    parents[vertexIndex] = neighbour;
                    minimumWeights[vertexIndex] = minumum + edgeDistance;
                }
            }
        }
        display(destinationIndex, minimumWeights, parents);
    }

    private void display(int destination, int[] distances, int[] parents) {
        System.out.print("Shortest path from Suriname to ");

        for (int vertexIndex = 0; vertexIndex < distances.length; vertexIndex++) {
            if (vertexIndex == destination) {
                System.out.print(vertexList[vertexIndex].label + " is ");
                System.out.print(distances[vertexIndex] + "km \nThe path is: ");
                displayPath(vertexIndex, vertexIndex, parents);
            }
        }
    }

//    Dijkstra minimum path calculation -----------------------------------
    public void dijkstraPriceCalculation(Order order) {
        int destinationIndex = verticesIndexes(order.getCountry());
        int[] minimumWeights = new int[totalVertices];
        boolean[] visited = new boolean[totalVertices];

        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
            minimumWeights[vertexIndex] = Integer.MAX_VALUE;
            visited[vertexIndex] = false;
        }

        minimumWeights[0] = 0;
        int[] parents = new int[totalVertices];
        parents[0] = -1;

        for (int index = 0; index < totalVertices; index++) {
            int neighbour = -1;
            int minumum = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
                if (!visited[vertexIndex] && minimumWeights[vertexIndex] < minumum) {
                    neighbour = vertexIndex;
                    minumum = minimumWeights[vertexIndex];
                }
            }

            visited[neighbour] = true;

            for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[neighbour][vertexIndex];
                if (edgeDistance > 0 && ((minumum + edgeDistance) < minimumWeights[vertexIndex])) {
                    parents[vertexIndex] = neighbour;
                    minimumWeights[vertexIndex] = minumum + edgeDistance;
                }
            }
        }
        displayReceipt(destinationIndex, order, minimumWeights, parents);
    }

    private void displayReceipt(int destination, Order order, int[] distances, int[] parents) {
        System.out.println("Order date: " + order.getDate() + "\n" +
                "Destination: " + vertexList[destination].label + "\n" +
                "Amount: " + order.getAmount() + order.getUnit());


        for (int vertexIndex = 0; vertexIndex < distances.length; vertexIndex++) {
            if (vertexIndex == destination) {
                System.out.println("Price per liter: " + ((distances[vertexIndex])/50) * 0.25);
                System.out.println("Distance: " + distances[vertexIndex] + "km" + "\n" +
                        "Total: " + orderCalculations(order, distances[vertexIndex]) + "$");
                System.out.println("Route: ");
                displayPath(vertexIndex, vertexIndex, parents);
            }
        }
    }

    public double orderCalculations(Order order, int distance){
        double averagePrice = 0.25;
        int averageDistance = 50;
        double orderAmount = 0;
        double orderPriceCalculation = ((distance/averageDistance) * averagePrice);

        if (order.getUnit().toLowerCase().equals("barrel")){
            orderAmount = (order.getAmount()*160);
        }else {
            orderAmount = order.getAmount();
        }

        return orderAmount * orderPriceCalculation;
    }

//    Dijkstra maximum path ----------------------------------------------
    private int[][] matrix(int [][] graph){
        int[][] negAdjacencyMatrix = new int[totalVertices][totalVertices];
        for (int i = 0; i < totalVertices; i++) {
            for (int j = 0; j < totalVertices; j++) {
                if (graph[i][j] == INFINITY){
                    negAdjacencyMatrix[i][j] = INFINITY;
                }else {
                    negAdjacencyMatrix[i][j] = (graph[i][j] * -1);
                }
            }
        }
        return negAdjacencyMatrix;
    }

    //    Sum of items in a list -------------------------------------------------------
    public int sumOfItemsInList(List<Integer> list) {
        int total = 0;
        for (int index : list) {
            total += index;
        }
        return total;
    }

    public void maximumPath(String destinationName) {
        int destination = verticesIndexes(destinationName);
        int[][] adjacencymatrix = matrix(adjacencyMatrix);
        for (int node = 0; node < totalVertices; node++) {
            distances[node] = INFINITY;
        }
        distances[0] = 0;
        for (int node = 0; node < totalVertices; node++) {
            for (int sourcenode = 0; sourcenode < totalVertices; sourcenode++) {
                for (int destinationnode = 0; destinationnode < totalVertices; destinationnode++) {
                    if (adjacencymatrix[sourcenode][destinationnode] != INFINITY) {
                        if (distances[destinationnode] > distances[sourcenode]
                                + adjacencymatrix[sourcenode][destinationnode]) {
                            distances[destinationnode] = distances[sourcenode]
                                    + adjacencymatrix[sourcenode][destinationnode];
                        }
                    }
                }
            }
        }

        for (int vertex = 0; vertex < totalVertices; vertex++) {
            if (vertex == destination) {
                System.out.println("The maximum path from " + vertexList[0].label + " to "
                        + vertexList[vertex].label + " is " + (distances[vertex] * -1));

                int weight = (distances[vertex] * -1);
                Stack pathList = new Stack(totalVertices);
                pathList.push(0);
                displayMaxPath(0, destination, pathList, weight);
            }
        }
    }

    private void displayMaxPath(int unvisited, int destination, Stack path, int maxWeight) {
        int weight = 0;
        List<Integer> weights = new ArrayList<>();
        if (unvisited == destination) {
            for (int index = 0; index < path.size() - 1; index++) {
                weights.add(adjacencyMatrix[path.get(index)][path.get(index + 1)]);
            }
            weight = sumOfItemsInList(weights);
            if (weight == maxWeight){
                for (int index = 0; index < path.size(); index++) {
                    System.out.print(vertexList[path.get(index)].label + " -> ");
                }
                System.out.print("Distance: " + weight + "km");
                System.out.println();
            }
//            System.out.println(path);

            return;
        }
        vertexList[unvisited].visited = true;

        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
            if (adjacencyMatrix[unvisited][vertexIndex] != INFINITY && !vertexList[vertexIndex].visited) {
                path.push(vertexIndex);
                displayMaxPath(vertexIndex, destination, path, maxWeight);
                path.pop();
            }
        }
        vertexList[unvisited].visited = false;
    }






}
