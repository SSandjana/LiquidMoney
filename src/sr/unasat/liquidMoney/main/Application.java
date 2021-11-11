package sr.unasat.liquidMoney.main;

import sr.unasat.liquidMoney.graphs.entities.Mining;
import sr.unasat.liquidMoney.graphs.entities.Order;
import sr.unasat.liquidMoney.graphs.entities.Pressure;
import sr.unasat.liquidMoney.graphs.entities.Sales;
import sr.unasat.liquidMoney.graphs.oilExport.GraphExport;
import sr.unasat.liquidMoney.graphs.oilMining.GraphMining;
import sr.unasat.liquidMoney.graphs.rapportages.Rapportages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        GraphMining graphMining = new GraphMining(23);
        GraphExport graphExport = new GraphExport(21);
        Rapportages rapportages = new Rapportages();

        graphMining.addVertex("Bron 1");      //0
        graphMining.addVertex("Bron 2");      //1
        graphMining.addVertex("Bron 3");      //2
        graphMining.addVertex("Bron 4");      //3
        graphMining.addVertex("Bron 5");      //4
        graphMining.addVertex("A");           //5
        graphMining.addVertex("B");           //6
        graphMining.addVertex("C");           //7
        graphMining.addVertex("D");           //8
        graphMining.addVertex("E");           //9
        graphMining.addVertex("F");           //10
        graphMining.addVertex("G");           //11
        graphMining.addVertex("H");           //12
        graphMining.addVertex("I");           //13
        graphMining.addVertex("J");           //14
        graphMining.addVertex("K");           //15
        graphMining.addVertex("L");           //16
        graphMining.addVertex("M");           //17
        graphMining.addVertex("N");           //18
        graphMining.addVertex("O");           //19
        graphMining.addVertex("P");           //20
        graphMining.addVertex("Nickerie");    //21
        graphMining.addVertex("Paramaribo");  //22

        graphMining.addEdge(4, 5, 3);
        graphMining.addEdge(4, 6, 2);
        graphMining.addEdge(4, 7, 3);
        graphMining.addEdge(5, 6, 1);
        graphMining.addEdge(5, 8, 6);
        graphMining.addEdge(6, 8, 5);
        graphMining.addEdge(7, 10, 7);
        graphMining.addEdge(0, 8, 1);
        graphMining.addEdge(0, 9, 1);
        graphMining.addEdge(9, 8, 3);
        graphMining.addEdge(8, 10, 2);
        graphMining.addEdge(9, 10, 1);
        graphMining.addEdge(10, 11, 3);
        graphMining.addEdge(11, 21, 1);
        graphMining.addEdge(10, 12, 17);
        graphMining.addEdge(1, 9, 20);
        graphMining.addEdge(1, 16, 2);
        graphMining.addEdge(1, 13, 3);
        graphMining.addEdge(13, 12, 5);
        graphMining.addEdge(12, 22, 7);
        graphMining.addEdge(16, 15, 8);
        graphMining.addEdge(2, 16, 7);
        graphMining.addEdge(15, 13, 2);
        graphMining.addEdge(2, 18, 6);
        graphMining.addEdge(18, 15, 1);
        graphMining.addEdge(14, 13, 3);
        graphMining.addEdge(18, 14, 2);
        graphMining.addEdge(19, 18, 3);
        graphMining.addEdge(14, 22, 5);
        graphMining.addEdge(17, 14, 3);
        graphMining.addEdge(20, 19, 1);
        graphMining.addEdge(20, 17, 2);
        graphMining.addEdge(3, 20, 1);

        graphExport.addVertex("Suriname");      //0
        graphExport.addVertex("Guatemala");     //1
        graphExport.addVertex("Frans-Guyana");  //2
        graphExport.addVertex("Brazil");        //3
        graphExport.addVertex("Guyana");        //4
        graphExport.addVertex("Costa Rica");    //5
        graphExport.addVertex("America");       //6
        graphExport.addVertex("Canada");        //7
        graphExport.addVertex("Honduras");      //8
        graphExport.addVertex("Venezuela");     //9
        graphExport.addVertex("Colombia");      //10
        graphExport.addVertex("Peru");          //11
        graphExport.addVertex("Bolivia");       //12
        graphExport.addVertex("Paraguay");      //13
        graphExport.addVertex("Uruguay");       //14
        graphExport.addVertex("Argentine");     //15
        graphExport.addVertex("Nicaragua");     //16
        graphExport.addVertex("Panama");        //17
        graphExport.addVertex("Ecuador");       //18
        graphExport.addVertex("Chili");         //19
        graphExport.addVertex("Mexico");        //20

        graphExport.addEdge(0, 1, 269);
        graphExport.addEdge(0, 2, 31);
        graphExport.addEdge(0, 3, 21);
        graphExport.addEdge(0, 4, 13);
        graphExport.addEdge(0, 5, 152);
        graphExport.addEdge(1, 6, 72);
        graphExport.addEdge(1, 7, 55);
        graphExport.addEdge(2, 8, 109);
        graphExport.addEdge(2, 9, 35);
        graphExport.addEdge(3, 10, 77);
        graphExport.addEdge(3, 11, 32);
        graphExport.addEdge(3, 12, 41);
        graphExport.addEdge(3, 13, 36);
        graphExport.addEdge(3, 14, 11);
        graphExport.addEdge(4, 15, 83);
        graphExport.addEdge(5, 16, 88);
        graphExport.addEdge(5, 17, 45);
        graphExport.addEdge(7, 6, 25);
        graphExport.addEdge(8, 1, 125);
        graphExport.addEdge(9, 10, 58);
        graphExport.addEdge(10, 18, 29);
        graphExport.addEdge(12, 19, 17);
        graphExport.addEdge(13, 19, 5);
        graphExport.addEdge(13, 15, 19);
        graphExport.addEdge(14, 13, 9);
        graphExport.addEdge(14, 15, 43);
        graphExport.addEdge(16, 20, 56);
        graphExport.addEdge(17, 16, 32);
        graphExport.addEdge(19, 15, 23);
        graphExport.addEdge(20, 7, 23);

//        graphMining.displayVertices();
//        graphMining.displayAdjacencyMatrix();

//        graphExport.displayVertices();
//        graphExport.displayAdjacencyMatrix();

        //Remove an edge ----------------------------------------------------------
//        graphMining.removeEdge(4, 5);
//        graphMining.displayEdge(4, 5);

//        graphExport.removeEdge(3, 12);
//        graphExport.displayEdge(3, 12);

        //Remove a vertex ---------------------------------------------------------
//        graphMining.removeVertex(4);
//        graphMining.displayEdge(4, 5);

//        graphExport.removeVertex(2);
//        graphExport.displayEdge(1, 6);

        //BreadthFirstSearches ----------------------------------------------------
        //Breadth First Search with given source -----------------
//        graphMining.breadthFirstSearch("Bron 5");

        //Breadth First Search classic ---------------------------
//        graphExport.breadthFirstSearch();

        //Breadth First Search with Edmond Karp max flow ---------
        //(Combination of Ford Fulkerson with BFS)
//        System.out.println(graphMining.fordFulkerson("Bron 5", "Paramaribo") + "km/s");
//        System.out.println(graphMining.fordFulkerson("Bron 1", "Nickerie") + "km/s");
//        System.out.println(graphMining.fordFulkerson("Bron 2", "Paramaribo") + "km/s");

        //Breadth First Search source to given level -------------
//        graphMining.breadthFirstSearchLevels("Bron 5", 3);
//        graphMining.breadthFirstSearchLevels("Bron 5", 5);

        //DepthFirstSearches -------------------------------------------------------
        //Is there a path possible from source to destination ----
//        graphMining.depthFirstSearchIsThereAPath("Bron 1", "Paramaribo");
//        graphMining.depthFirstSearchIsThereAPath("Bron 3", "Nickerie");

        //Print all paths ----------------------------------------
//        graphMining.dfsPrintPaths("Bron 1", "Paramaribo");

        //Is there a path possible from source to given country --
//        graphExport.depthFirstSearchIsThereAPath("Mexico");

        //Dijkstra Algorithm -------------------------------------------------------
        //Minimum path (Best case) with source -------------------
//        graphMining.minimumPathDijkstra("Bron 5");

        //Minimum path (Best case) with source and destination ---
//        graphMining.minimumPathDijkstra("Bron 5", "Paramaribo");

        //Minimum path (Best case) overall , best of best --------
//        List<String> exportCountries = new ArrayList<>();
//        exportCountries.add("Mexico");
//        exportCountries.add("Canada");
//        exportCountries.add("America");
//        exportCountries.add("Ecuador");
//        exportCountries.add("Argentine");
//        graphExport.minimumPathDijkstra(exportCountries);

        //Minimum path (Best case) with destination --------------
//        graphExport.minimumPathDijkstra("Argentine");

        //Minimum path price calculation -------------------------
//        graphExport.dijkstraPriceCalculation(new Order("Mexico", 10, "barrel",
//                LocalDate.of(2021, 7, 11)));

        //Maximum path (Worst case) ------------------------------
//        graphMining.maximumPath("Bron 1", "Nickerie");

        //Maximum path (Worst case) ------------------------------
//        graphExport.maximumPath("Canada");





        //Rapportages --------------------------------------------------------------
        //Top 3 importeurs jaarlijks -------------------------------------
//        Order order1 = new Order("Mexico", 100, "Barrel",
//                LocalDate.of(2021,1,1));
//        Order order2 = new Order("Canada", 200, "barrel",
//                LocalDate.of(2021,2,3));
//        Order order3 = new Order("Mexico", 300, "barrel",
//                LocalDate.of(2021, 3, 7));
//        Order order4 = new Order("Canada", 777, "barrel",
//                LocalDate.of(2021, 3, 5));
//        Order order5 = new Order("America", 299, "barrel",
//                LocalDate.of(2021, 3, 5));
//        Order order6 = new Order("Ecuador", 344, "barrel",
//                LocalDate.of(2021, 4, 5));
//        Order order7 = new Order("Argentine", 777, "Barrel",
//                LocalDate.of(2021, 7, 7));
//        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5, order6, order7);
//        rapportages.topImporters(orders, 2021);

        //Top 3 sources per refineries -------------------------------
//        Mining result1 = new Mining(1, 233, "Nickerie",
//                LocalDate.of(2021, 3, 2));
//        Mining result2 = new Mining(1, 392, "Nickerie",
//                LocalDate.of(2021, 3, 2));
//        Mining result3 = new Mining(1, 221, "Nickerie",
//                LocalDate.of(2021, 3, 2));
//        Mining result4 = new Mining(2, 233, "Nickerie",
//                LocalDate.of(2021, 3, 2));
//        Mining result5 = new Mining(3, 392, "Nickerie",
//                LocalDate.of(2021, 3, 2));
//        Mining result6 = new Mining(5, 221, "Nickerie",
//                LocalDate.of(2021, 3, 2));
//        Mining result7 = new Mining(1, 689, "Paramaribo",
//                LocalDate.of(2021, 3, 2));
//        Mining result8 = new Mining(2, 233, "Paramaribo",
//                LocalDate.of(2021, 3, 2));
//        Mining result9 = new Mining(3, 846, "Paramaribo",
//                LocalDate.of(2021, 3, 2));
//        Mining result10 = new Mining(4, 738, "Paramaribo",
//                LocalDate.of(2021, 3, 2));
//        List<Mining> minings = Arrays.asList(result1, result2, result3, result4, result5, result6, result7,
//                result8, result9, result10);
//        rapportages.displayTopSources(minings, 2021);

        //Top 3 sources with the most oil ------------------------------
//        Pressure pressure1 = new Pressure(1, 28);
//        Pressure pressure2 = new Pressure(2, 3);
//        Pressure pressure3 = new Pressure(3, 21);
//        Pressure pressure4 = new Pressure(4, 41);
//        Pressure pressure5 = new Pressure(5, 2);
//        Pressure pressure6 = new Pressure(1, 12);
//        Pressure pressure7 = new Pressure(2, 34);
//        Pressure pressure8 = new Pressure(3, 14);
//        Pressure pressure9 = new Pressure(4, 49);
//        Pressure pressure10 = new Pressure(5, 13);
//        List<Pressure> pressures = Arrays.asList(pressure1, pressure2, pressure3, pressure4, pressure5,
//                pressure6, pressure7, pressure8, pressure9, pressure10);
//        rapportages.mostOilSources(pressures);


    }
}
