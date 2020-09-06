package ShopApp.ProductTools.Comparators;

import ShopApp.ProductTools.Productik;
import ShopApp.User;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRecommendComparator implements Comparator<Productik> {
    private User user;

    private Map<User, Double> mapUserCloseness;
    private Map<User, Map<Productik, Double>> mapUserProductCloseness;
    private SortedSet<Map.Entry<Productik, Double>> recommendedList;

    private Map<User, Double> calcOrderedUsers(Set<User> users) {
        Map<User, Double> closenesses = new TreeMap<>();
        Map<Productik, Double> userRatings = user.getRatings();
        for (User anotherUser : users){
            Map<Productik, Double> anotherUserRatings = anotherUser.getRatings();
            Set<Double> differences = userRatings.keySet().stream().filter(x -> anotherUserRatings.containsKey(x))
                    .map(y -> Math.abs(userRatings.get(y) - anotherUserRatings.get(y))).collect(Collectors.toSet());
            double closeness = differences.stream().reduce(0d, (x, y) -> x + y) / differences.size();
            closenesses.put(anotherUser, closeness);
        }
        return closenesses;
    }

    private Map<User, Map<Productik, Double>> calcMapUserProductCloseness(){
        Map<User, Map<Productik, Double>> closenesses = new HashMap<>();
        for (User anotherUser : mapUserCloseness.keySet()){
            Set<Productik> products = user.getRatings().keySet().stream().filter(x -> anotherUser.getRatings().containsKey(x)).collect(Collectors.toSet());
            Map<Productik, Double> closeness = products.stream().collect(Collectors.toMap(
                    x -> x,
                    x -> (Math.abs(user.getRatings().get(x) - anotherUser.getRatings().get(x))) * mapUserCloseness.get(anotherUser)
            ));
            closenesses.put(anotherUser, closeness);
        }
        return closenesses;
    }

    private SortedSet<Map.Entry<Productik, Double>> getSingleClosenessesList(){
        HashMap<Productik, Double> recommended = new HashMap<>();
        for (User anotherUser : mapUserProductCloseness.keySet()) {
            Map<Productik, Double> closeness = mapUserProductCloseness.get(anotherUser);
            for (Productik product : closeness.keySet()) {
                if (recommended.containsKey(product)) {
                    if (recommended.get(product) < closeness.get(product))
                        recommended.put(product, closeness.get(product));
                } else
                    recommended.put(product, closeness.get(product));
            }
        }
        return entriesSortedByValues(recommended);
    }

    static <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }


    @Override
    public int compare(Productik product1, Productik product2) {
        Map.Entry<Productik, Double> entry1 = recommendedList.stream().filter(x -> x.getKey() == product1).findFirst().get();
        Map.Entry<Productik, Double> entry2 = recommendedList.stream().filter(x -> x.getKey() == product2).findFirst().get();
        return recommendedList.tailSet(entry1).size() - recommendedList.tailSet(entry2).size();
    }
}
