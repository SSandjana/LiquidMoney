package sr.unasat.liquidMoney.graphs.rapportages;

import sr.unasat.liquidMoney.graphs.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rapportages {

    public void topImporters(List<Order> orders, int year){
        Map<String, Integer> total = null;
        for (int index = 0; index < orders.size(); index++){
            if (orders.get(index).getDate().getYear() == year){
                total = orders.stream().collect(Collectors.groupingBy(Order::getCountry,
                        Collectors.summingInt(Order::getAmount)));
            }
        }

        assert total != null;
        List<String> countries = new ArrayList<>(total.keySet());
        List<Integer> orderAmounts = new ArrayList<>(total.values());
        List<Sales> sales = new ArrayList<>();

        for (int index = 0; index < countries.size(); index++){
            sales.add(new Sales(countries.get(index), orderAmounts.get(index)));
        }

        System.out.println("Top 3 importers in " + year + " are: ");
        insertionSortSales(sales);

    }

    public void insertionSortSales(List<Sales> sales) {
        for (int unsortedIndex = 1; unsortedIndex < sales.size(); ++unsortedIndex) {
            int sortedIndexValue = sales.get(unsortedIndex).getOrderAmount();
            String sortedIndexCountry = sales.get(unsortedIndex).getCountry();
            int newIndex = unsortedIndex - 1;

            while (newIndex >= 0 && sales.get(newIndex).getOrderAmount() < sortedIndexValue) {
                sales.get(newIndex + 1).setOrderAmount(sales.get(newIndex).getOrderAmount());
                sales.get(newIndex + 1).setCountry(sales.get(newIndex).getCountry());
                newIndex = newIndex - 1;
            }
            sales.get(newIndex + 1).setOrderAmount(sortedIndexValue);
            sales.get(newIndex + 1).setCountry(sortedIndexCountry);
        }
        for (int index = 0; index < 3; index++) {
            System.out.print(index+1 + ". " + sales.get(index) + " ");
            System.out.println();
        }
    }

    public void topSourcePerRefinery(List<Mining> minings, int year){
        Map<Integer, Integer> result = null;
        for (int index = 0; index < minings.size(); index++){
            if (minings.get(index).getDate().getYear() == year){
                result = minings.stream().collect(Collectors.groupingBy(Mining::getSource,
                        Collectors.summingInt(Mining::getAmount)));
            }
        }

        assert result != null;
        List<Integer> sources = new ArrayList<>(result.keySet());
        List<Integer> miningAmounts = new ArrayList<>(result.values());
        List<SourcesResult> sourcesResults = new ArrayList<>();

        for (int index = 0; index < sources.size(); index++){
            sourcesResults.add(new SourcesResult(sources.get(index), miningAmounts.get(index)));
        }

        insertionSortMining(sourcesResults);

    }

    public void insertionSortMining(List<SourcesResult> results) {
        for (int unsortedIndex = 1; unsortedIndex < results.size(); ++unsortedIndex) {
            int sortedIndexValue = results.get(unsortedIndex).getAmount();
            int sortedIndexSource = results.get(unsortedIndex).getSource();
            int newIndex = unsortedIndex - 1;

            while (newIndex >= 0 && results.get(newIndex).getAmount() < sortedIndexValue) {
                results.get(newIndex + 1).setAmount(results.get(newIndex).getAmount());
                results.get(newIndex + 1).setSource(results.get(newIndex).getSource());
                newIndex = newIndex - 1;
            }
            results.get(newIndex + 1).setAmount(sortedIndexValue);
            results.get(newIndex + 1).setSource(sortedIndexSource);
        }
        for (int index = 0; index < 3; index++) {
            System.out.print(index+1 + ". " + results.get(index) + " ");
            System.out.println();
        }
    }

    public void displayTopSources(List<Mining> miningResults, int year){
        List<Mining> nick = new ArrayList<>();
        List<Mining> parbo = new ArrayList<>();

        for (int index = 0; index < miningResults.size(); index++){
            if (miningResults.get(index).getRefinery().equals("Nickerie")){
                nick.add(miningResults.get(index));
            }else if (miningResults.get(index).getRefinery().equals("Paramaribo")){
                parbo.add(miningResults.get(index));
            }else {
                System.out.println("Invalid refinery");
            }
        }
        System.out.println("Top 3 sources for Nickerie in " + year + " : ");
        topSourcePerRefinery(nick, year);

        System.out.println("Top 3 sources for Paramaribo in " + year + " : ");
        topSourcePerRefinery(parbo, year);
    }

    public void mostOilSources(List<Pressure> list){
        Map<Integer, Integer> result = null;
        for (int index = 0; index < list.size(); index++){
            result = list.stream().collect(Collectors.groupingBy(Pressure::getSource,
                    Collectors.summingInt(Pressure::getPressure)));
        }

        assert result != null;
        List<Integer> sources = new ArrayList<>(result.keySet());
        List<Integer> totalPressure = new ArrayList<>(result.values());
        List<Pressure> pressures = new ArrayList<>();

        for (int index = 0; index < sources.size(); index++){
            pressures.add(new Pressure(sources.get(index), totalPressure.get(index)));
        }

        System.out.println("Top 3 sources with the most oil are: ");
        insertionSortPressure(pressures);

    }

    public void insertionSortPressure(List<Pressure> results) {
        for (int unsortedIndex = 1; unsortedIndex < results.size(); ++unsortedIndex) {
            int sortedIndexValue = results.get(unsortedIndex).getPressure();
            int sortedIndexSource = results.get(unsortedIndex).getSource();
            int newIndex = unsortedIndex - 1;

            while (newIndex >= 0 && results.get(newIndex).getPressure() < sortedIndexValue) {
                results.get(newIndex + 1).setPressure(results.get(newIndex).getPressure());
                results.get(newIndex + 1).setSource(results.get(newIndex).getSource());
                newIndex = newIndex - 1;
            }
            results.get(newIndex + 1).setPressure(sortedIndexValue);
            results.get(newIndex + 1).setSource(sortedIndexSource);
        }
        for (int index = 0; index < 3; index++) {
            System.out.print(index+1 + ". " + results.get(index) + " ");
            System.out.println();
        }
    }


}
