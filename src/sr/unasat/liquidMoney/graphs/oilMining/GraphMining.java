package sr.unasat.liquidMoney.graphs.oilMining;

import sr.unasat.liquidMoney.graphs.dataStructures.queue.Queue;
import sr.unasat.liquidMoney.graphs.dataStructures.stack.Stack;
import sr.unasat.liquidMoney.graphs.entities.RouteDetails;
import sr.unasat.liquidMoney.graphs.graphRequirements.DistanceToParent;
import sr.unasat.liquidMoney.graphs.graphRequirements.Vertex;

import java.util.ArrayList;
import java.util.List;

public class GraphMining {

    private int MAX_VERTICES;
    private final int INFINITY = 7777777;
    private final Vertex[] vertexList;
    private final int[][] adjacencyMatrix;
    private int totalVertices;
    private int totalVertsInTree;
    private DistanceToParent shortestPathData[];
    private int currentVertex;
    private int startToCurrent;
    private final Queue queue;
    private final Stack stack;
    private final int[] distances;

    public GraphMining(int MAX_VERTICES) {
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
        distances = new int[23];

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
        System.out.print(vertexList[vertex].label + " - ");
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

//    Get index of given vertex name -----------------------------------------------
    public int verticesIndexes(String vertexName){
        int vertexIndex;
        switch (vertexName){
            case "Bron 1":
                vertexIndex = 0;
                break;
            case "Bron 2":
                vertexIndex = 1;
                break;
            case "Bron 3":
                vertexIndex = 2;
                break;
            case "Bron 4":
                vertexIndex = 3;
                break;
            case "Bron 5":
                vertexIndex = 4;
                break;
            case "Nickerie":
                vertexIndex = 21;
                break;
            case "Paramaribo":
                vertexIndex = 22;
                break;
            default:
                vertexIndex = 0;
        }
        return vertexIndex;
    }

//    Breadth First Search with given source (classic) ----------------------------
    public void breadthFirstSearch(String source){
        int sourceIndex = verticesIndexes(source);

        vertexList[sourceIndex].visited = true;
        displayVertex(sourceIndex);
        queue.insert(sourceIndex);

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

//    Get adjacent unvisited vertex for dfs and bfs ----------------------------
    public int getAdjacentUnvisitedVertex(int vertex){
        for (int neighbour = 0; neighbour < totalVertices; neighbour++){
            if (adjacencyMatrix[vertex][neighbour] != INFINITY && !vertexList[neighbour].visited){
                return neighbour;
            }
        }
        return -1;
    }

//    Breadth First Search for max flow including residual graph ---------------
    boolean bfsMaxFlow(int[][] residualGraph, int source, int sink, int[] parent){
        boolean[] visited = new boolean[MAX_VERTICES];
        for (int i = 0; i < MAX_VERTICES; ++i) {
            visited[i] = false;
        }

        queue.insert(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int unvisitedNeighbour = queue.remove();

            for (int vertex = 0; vertex < MAX_VERTICES; vertex++) {
                if (!visited[vertex] && residualGraph[unvisitedNeighbour][vertex] > 0) {
                    if (vertex == sink) {
                        parent[vertex] = unvisitedNeighbour;
                        return true;
                    }
                    queue.insert(vertex);
                    parent[vertex] = unvisitedNeighbour;
                    visited[vertex] = true;
                }
            }
        }
        return false;
    }

    public String fordFulkerson(String sourceName, String sinkName) {
        int source = verticesIndexes(sourceName);
        int sink = verticesIndexes(sinkName);

        String display = "Max flow from " + vertexList[source].label + " to " +
                vertexList[sink].label + " is: ";
        int unvisitedNeighbour, vertex;

        int[][] residualGraph = new int[MAX_VERTICES][MAX_VERTICES];

        for (unvisitedNeighbour = 0; unvisitedNeighbour < MAX_VERTICES; unvisitedNeighbour++)
            for (vertex = 0; vertex < MAX_VERTICES; vertex++) {
                if (adjacencyMatrix[unvisitedNeighbour][vertex] != INFINITY) {
                    residualGraph[unvisitedNeighbour][vertex] = adjacencyMatrix[unvisitedNeighbour][vertex];
                }
            }
        int[] parent = new int[MAX_VERTICES];

        int maxFlow = 0;

        while (bfsMaxFlow(residualGraph, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (vertex = sink; vertex != source; vertex = parent[vertex]) {
                unvisitedNeighbour = parent[vertex];
                pathFlow = Math.min(pathFlow, residualGraph[unvisitedNeighbour][vertex]);
            }
            for (vertex = sink; vertex != source; vertex = parent[vertex]) {
                unvisitedNeighbour = parent[vertex];
                residualGraph[unvisitedNeighbour][vertex] -= pathFlow;
                residualGraph[vertex][unvisitedNeighbour] += pathFlow;
            }

            maxFlow += pathFlow;
        }

        return display + maxFlow;
    }

//    Get unvisited adjacent per level ----------------------------------------
    public int generalAdjacent(int vertex, int start, int to){
        for (int neighbour = start; neighbour < to; neighbour++){
            if (adjacencyMatrix[vertex][neighbour] != INFINITY && !vertexList[neighbour].visited){
                return neighbour;
            }
        }
        return -1;
    }

//    Specify levels ------------------------------------------------------------
    public int getAdjacentUnvisitedVertexLevels(int vertex, int level, int source){
        if (source == 0){
            if (level == 1){
                return generalAdjacent(vertex, source, 10);
            }else if (level == 2){
                return generalAdjacent(vertex, source, 11);
            }else if (level == 3){
                return generalAdjacent(vertex, source, 13);
            }else if (level == 4){
                return generalAdjacent(vertex, source, 23);
            }
        }else if (source == 1){
            if (level == 1){
                int [] list = {1, 9, 13};
                for (int neighbour = source; neighbour < list.length; neighbour++){
                    if (adjacencyMatrix[vertex][list[neighbour]] != INFINITY &&
                            !vertexList[list[neighbour]].visited){
                        return list[neighbour];
                    }
                }
            }else if (level == 2){
                int [] list = {1, 8, 9, 10, 12, 13};
                for (int neighbour = source; neighbour < list.length; neighbour++){
                    if (adjacencyMatrix[vertex][list[neighbour]] != INFINITY &&
                            !vertexList[list[neighbour]].visited){
                        return list[neighbour];
                    }
                }
            }else if (level == 3){
                int [] list = {1, 8, 9, 10, 11, 12, 13, 22};
                for (int neighbour = source; neighbour < list.length; neighbour++){
                    if (adjacencyMatrix[vertex][list[neighbour]] != INFINITY &&
                            !vertexList[list[neighbour]].visited){
                        return list[neighbour];
                    }
                }
            }else if (level == 4){
                int [] list = {1, 8, 9, 10, 11, 12, 13, 21, 22};
                for (int neighbour = source; neighbour < list.length; neighbour++){
                    if (adjacencyMatrix[vertex][list[neighbour]] != INFINITY &&
                            !vertexList[list[neighbour]].visited){
                        return list[neighbour];
                    }
                }
            }
        }else if (source == 2){
            if (level == 1){
                int [] list = {16, 18};
                for (int neighbour : list) {
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY && !vertexList[neighbour].visited) {
                        return neighbour;
                    }
                }
            }else if (level == 2){
                for (int neighbour = 14; neighbour < 19; neighbour++){
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY && !vertexList[neighbour].visited){
                        return neighbour;
                    }
                }
            }else if (level == 3){
                for (int neighbour = 13; neighbour < 23; neighbour++){
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY && !vertexList[neighbour].visited){
                        return neighbour;
                    }
                }
            }else if (level == 4){
                for (int neighbour = 12; neighbour < 23; neighbour++){
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY && !vertexList[neighbour].visited){
                        return neighbour;
                    }
                }
            }
        }else if (source == 3){
            if (level == 1){
                int [] list = {20};
                for (int neighbour : list) {
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY &&
                            !vertexList[neighbour].visited) {
                        return neighbour;
                    }
                }
            }else if (level == 2){
                int [] list = {17, 19, 20};
                for (int neighbour : list) {
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY &&
                            !vertexList[neighbour].visited) {
                        return neighbour;
                    }
                }
            }else if (level == 3){
                int [] list = {14, 17, 18, 19, 20};
                for (int neighbour : list) {
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY &&
                            !vertexList[neighbour].visited) {
                        return neighbour;
                    }
                }
            }else if (level == 4){
                for (int neighbour = 13; neighbour < 23; neighbour++){
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY && !vertexList[neighbour].visited){
                        return neighbour;
                    }
                }
            }else if (level == 5){
                for (int neighbour = 12; neighbour < 23; neighbour++){
                    if (adjacencyMatrix[vertex][neighbour] != INFINITY && !vertexList[neighbour].visited){
                        return neighbour;
                    }
                }
            }
        }if (source == 4) {
            if (level == 1) {
                return generalAdjacent(vertex, source, 8);
            } else if (level == 2) {
                return generalAdjacent(vertex, source, 11);
            } else if (level == 3) {
                return generalAdjacent(vertex, source, 13);
            } else if (level == 4) {
                return generalAdjacent(vertex, source, 23);
            }
        }
        return -1;
    }

//    Breadth First Search from source to given level -------------------------
    public void breadthFirstSearchLevels(String source, int level){
        int sourceIndex = verticesIndexes(source);
        if (sourceIndex == 0 || sourceIndex == 1 || sourceIndex == 2 || sourceIndex == 4){
            if (level > 4){
                System.out.println("Level out of range for " + vertexList[sourceIndex].label);
                System.out.println("Max level for " + vertexList[sourceIndex].label + " is level 4!!!");
                return;
            }
        }else if (sourceIndex == 3){
            if (level > 5){
                System.out.println("Level out of range for " + vertexList[sourceIndex].label);
                System.out.println("Max level for " + vertexList[sourceIndex].label + "is level 5!!!");
                return;
            }
        }

        vertexList[sourceIndex].visited = true;
        displayVertex(sourceIndex);
        queue.insert(sourceIndex);

        int neighbourVertex;

        while (!queue.isEmpty()){
            int completedVertex = queue.remove();
            while ((neighbourVertex = getAdjacentUnvisitedVertexLevels(completedVertex, level, sourceIndex)) != -1){
                vertexList[neighbourVertex].visited = true;
                displayVertex(neighbourVertex);
                queue.insert(neighbourVertex);
            }
        }
        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++){
            vertexList[vertexIndex].visited = false;
        }
    }

//    Depth First Search to check is there is a path from given source to destination -
    public boolean depthFirstSearchIsThereAPath(String sourceName, String destinationName){
        int source = verticesIndexes(sourceName);
        int destination = verticesIndexes(destinationName);

        vertexList[source].visited = true;
        stack.push(source);

        while (!stack.isEmpty()){
            int neighbour = getAdjacentUnvisitedVertex(stack.peek());
            if (neighbour == destination){
                System.out.println("There is a path possible from " +
                        vertexList[source].label + " to " + vertexList[destination].label);
                return true;
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

        System.out.println(vertexList[destination].label + " is unreachable from " +
                vertexList[source].label);
        return false;

    }

//    Sum of items in a list -------------------------------------------------------
    public int sumOfItemsInList(List<Integer> list) {
        int total = 0;
        for (int index : list) {
            total += index;
        }
        return total;
    }

//    All paths from given source to destination ----------------------------------
    public void dfsPrintPaths(String sourceName, String destinationName) {
        int source = verticesIndexes(sourceName);
        int destination = verticesIndexes(destinationName);

        List<RouteDetails> routes = new ArrayList<>();
        routes.add(new RouteDetails("Bron 1", "Nickerie"));
        routes.add(new RouteDetails("Bron 1", "Paramaribo"));
        routes.add(new RouteDetails("Bron 2", "Nickerie"));
        routes.add(new RouteDetails("Bron 2", "Paramaribo"));
        routes.add(new RouteDetails("Bron 3", "Paramaribo"));
        routes.add(new RouteDetails("Bron 4", "Paramaribo"));
        routes.add(new RouteDetails("Bron 5", "Nickerie"));
        routes.add(new RouteDetails("Bron 5", "Paramaribo"));

        for (int index = 0; index < routes.size(); index++){
            if (routes.get(index).getSource().equals(sourceName) &&
                    routes.get(index).getDestination().equals(destinationName)){
                Stack pathList = new Stack(totalVertices);
                pathList.push(source);
                depthFirstSearchFindPaths(source, destination, pathList);
                return;
            }
        }
        System.out.println(destinationName + " is unreachable from " + sourceName);

    }

    private void depthFirstSearchFindPaths(int unvisited, int destination, Stack path) {
        int weight = 0;
        List<Integer> weights = new ArrayList<>();
        if (unvisited == destination) {
            for (int index = 0; index < path.size(); index++) {
                System.out.print(vertexList[path.get(index)].label + " -> ");
            }
            for (int index = 0; index < path.size() - 1; index++) {
                weights.add(adjacencyMatrix[path.get(index)][path.get(index + 1)]);
            }
            weight = sumOfItemsInList(weights);
            System.out.print("Distance: " + weight + "km");
//            System.out.println(path);
            System.out.println();
            return;
        }
        vertexList[unvisited].visited = true;

        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
            if (adjacencyMatrix[unvisited][vertexIndex] != INFINITY && !vertexList[vertexIndex].visited) {
                path.push(vertexIndex);
                depthFirstSearchFindPaths(vertexIndex, destination, path);
                path.pop();
            }
        }
        vertexList[unvisited].visited = false;
    }

//    Dijkstra minimum path with source ------------------------------------------
    public void minimumPathDijkstra(String source) {
        int sourceIndex = verticesIndexes(source);
        int[] minimumWeights = new int[totalVertices];
        boolean[] visited = new boolean[totalVertices];

        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
            minimumWeights[vertexIndex] = Integer.MAX_VALUE;
            visited[vertexIndex] = false;
        }

        minimumWeights[sourceIndex] = 0;
        int[] parents = new int[totalVertices];
        parents[sourceIndex] = -1;

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
        displayMinimum(sourceIndex, minimumWeights, parents);
    }


    private void displayMinimum(int source, int[] distances, int[] parents) {
        System.out.print("The minimum path from ");
        int min = Math.min(distances[21], distances[22]);

        for (int vertexIndex = 0; vertexIndex < distances.length; vertexIndex++) {
            if (distances[vertexIndex] == min) {
                System.out.print(vertexList[source].label + " is ");
                System.out.print(distances[vertexIndex] + "km and it leads to " +
                        vertexList[vertexIndex].label + "\n" + "The path is: ");
                displayPath(vertexIndex, parents);
            }
        }
    }

    private void displayPath(int currentVertex, int[] parents) {
        if (currentVertex == -1) {
            return;
        }
        displayPath(parents[currentVertex], parents);
        if (currentVertex == 21 || currentVertex == 22){
            System.out.print(vertexList[currentVertex].label + " \n ");
        }else{
            System.out.print(vertexList[currentVertex].label + " - ");
        }
    }

//    Dijkstra minimum path with source and destination ----------------------------
    public void minimumPathDijkstra(String source, String destination) {
        int sourceIndex = verticesIndexes(source);
        int destinationIndex = verticesIndexes(destination);
        int[] minimumWeights = new int[totalVertices];
        boolean[] visited = new boolean[totalVertices];

        for (int vertexIndex = 0; vertexIndex < totalVertices; vertexIndex++) {
            minimumWeights[vertexIndex] = Integer.MAX_VALUE;
            visited[vertexIndex] = false;
        }

        minimumWeights[sourceIndex] = 0;
        int[] parents = new int[totalVertices];
        parents[sourceIndex] = -1;

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
        displayMinimum(sourceIndex, destinationIndex, minimumWeights, parents);
    }


    private void displayMinimum(int source, int destination, int[] distances, int[] parents) {
        int min = distances[destination];

        if (min != INFINITY){
            for (int vertexIndex = 0; vertexIndex < distances.length; vertexIndex++) {
                if (distances[vertexIndex] == min) {
                    System.out.print("The minimum path from ");
                    System.out.print(vertexList[source].label + " to " +
                            vertexList[vertexIndex].label + " is ");
                    System.out.print(distances[vertexIndex] + "km \n" + "The path is: ");
                    displayPath(vertexIndex, parents);
                }
            }
        }else {
            System.out.println(vertexList[destination].label + " is unreachable from " +
                    vertexList[source].label);
        }

    }

//    Dijkstra maximum path -----------------------------------------------------
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

    public void maximumPath(String sourceName, String destinationName) {
        int source = verticesIndexes(sourceName);
        int destination = verticesIndexes(destinationName);
        int[][] adjacencymatrix = matrix(adjacencyMatrix);
        for (int node = 0; node < totalVertices; node++) {
            distances[node] = INFINITY;
        }
        distances[source] = 0;
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
                System.out.println("The maximum path from " + vertexList[source].label + " to "
                        + vertexList[vertex].label + " is " + (distances[vertex] * -1));

                int weight = (distances[vertex] * -1);
                Stack pathList = new Stack(totalVertices);
                pathList.push(source);
                displayMaxPath(source, destination, pathList, weight);
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
