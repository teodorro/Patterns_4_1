package ShopApp.ProductTools.Comparators;

import ShopApp.ProductTools.Productik;
import ShopApp.User;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRecommendComparator implements Comparator<Productik> {
    private final double MAX_RATING = 5;
    private final double MAX_USER_DIFFERENCE = 1.5;
    private final double MIN_RATING = 4;
    private SortedSet<Map.Entry<Productik, Double>> recommendedList;

    public ProductRecommendComparator prepare(User user, Set<User> otherUsers){
        Map<User, Double> mapUserCloseness = calcUserCloseness(user, otherUsers);
        Map<Productik, Map<User, Double>> productUserDoubleMap = getProductUserDoubleMap(user, otherUsers);
        recommendedList = createRecommendedList(mapUserCloseness, productUserDoubleMap);
//        Map<User, Map<Productik, Double>> mapUserProductCloseness = calcMapUserProductCloseness(user, mapUserCloseness);
//        recommendedList = getSingleClosenessesList(mapUserProductCloseness);
        return this;
    }

    private Map<User, Double> calcUserCloseness(User user, Set<User> otherUsers) {
        Map<User, Double> closenesses = new HashMap<>();
        Map<Productik, Double> userRatings = user.getRatings();
        for (User anotherUser : otherUsers){
            Map<Productik, Double> anotherUserRatings = anotherUser.getRatings();
            Set<Double> differences = userRatings.keySet().stream().filter(x -> anotherUserRatings.containsKey(x))
                    .map(y -> Math.abs(userRatings.get(y) - anotherUserRatings.get(y))).collect(Collectors.toSet());
            double closeness = differences.stream().reduce(0d, (x, y) -> x + y) / differences.size();
            closenesses.put(anotherUser, closeness);
        }
//        Double max = closenesses.values().stream().max(Comparator.naturalOrder()).get();
//        for (User u : closenesses.keySet())
//            closenesses.put(u, closenesses.get(u) / max);

//        for (User u : closenesses.keySet())
//            closenesses.put(u, closenesses.get(u) / MAX_RATING);

        return closenesses;
    }

    private Map<Productik, Map<User, Double>> getProductUserDoubleMap(User user, Set<User> otherUsers){
        Map<Productik, Map<User, Double>> productUserDoubleMap = new HashMap<>();
        Map<Productik, Double> userRating = user.getRatings();
        for (User anotherUser : otherUsers) {
            Map<Productik, Double> ratings = anotherUser.getRatings();
            for (Productik product : ratings.keySet()){
                if (userRating.containsKey(product))
                    continue;
                if (!productUserDoubleMap.containsKey(product))
                    productUserDoubleMap.put(product, new HashMap<>());
                productUserDoubleMap.get(product).put(anotherUser, ratings.get(product));
            }
        }

        return productUserDoubleMap;
    }

    private SortedSet<Map.Entry<Productik, Double>> createRecommendedList(Map<User, Double> closenesses, Map<Productik, Map<User, Double>> productUserDoubleMap){
        Map<Productik, Double> recommended = new HashMap<>();
        for (Productik product : productUserDoubleMap.keySet()){
            Set<Double> marks = new HashSet<>();
            for (User anotherUser : productUserDoubleMap.get(product).keySet()){
                if (closenesses.get(anotherUser) <= MAX_USER_DIFFERENCE) {
                    marks.add(productUserDoubleMap.get(product).get(anotherUser));
                }
            }
            if (marks.size() != 0){
                Double avg = marks.stream().reduce((x, y) -> x + y).get() / marks.size();
                if (avg >= MIN_RATING)
                    recommended.put(product, avg);
            }
        }
        return entriesSortedByValues(recommended);
    }

//    private Map<User, Map<Productik, Double>> getMapUserProductRate(User user, Map<User, Double> mapUserCloseness) {
//        Map<User, Map<Productik, Double>> closenesses = new HashMap<>();
//        for (User anotherUser : mapUserCloseness.keySet()){
//            Set<Productik> products = anotherUser.getRatings().keySet().stream().filter(x -> !user.getRatings().containsKey(x)).collect(Collectors.toSet());
//            Map<Productik, Double> closeness = products.stream().collect(Collectors.toMap(
//                    x -> x,
//                    x -> (anotherUser.getRatings().get(x))
//            ));
//            closenesses.put(anotherUser, closeness);
//        }
//        return closenesses;
//    }
//
//    private SortedSet<Map.Entry<Productik, Double>> getSingleClosenessesList(Map<User, Map<Productik, Double>> mapUserProductRate){
//        HashMap<Productik, Set<Double>> singleSetList = new HashMap<>();
//        for (User anotherUser : mapUserProductRate.keySet()) {
//            Map<Productik, Double> closeness = mapUserProductRate.get(anotherUser);
//            for (Productik product : closeness.keySet()) {
//                if (!singleSetList.containsKey(product))
//                    singleSetList.put(product, new HashSet<>());
//                singleSetList.get(product).add(closeness.get(product));
//            }
//        }
//        HashMap<Productik, Double> singleList = new HashMap<>();
//        for (Productik product : singleSetList.keySet()){
//            singleList.put(product, singleSetList.get(product).stream().reduce((x, y) -> x + y).get() / singleSetList.get(product).size());
//        }
//        return entriesSortedByValues(singleList);
//    }



//    private Map<User, Map<Productik, Double>> calcMapUserProductCloseness(User user, Map<User, Double> mapUserCloseness){
//        Map<User, Map<Productik, Double>> closenesses = new HashMap<>();
//        for (User anotherUser : mapUserCloseness.keySet()){
////            Set<Productik> products = user.getRatings().keySet().stream().filter(x -> anotherUser.getRatings().containsKey(x)).collect(Collectors.toSet());
//            Set<Productik> products = anotherUser.getRatings().keySet().stream().filter(x -> !user.getRatings().containsKey(x)).collect(Collectors.toSet());
////            Map<Productik, Double> closeness = products.stream().collect(Collectors.toMap(
////                    x -> x,
////                    x -> (Math.abs(user.getRatings().get(x) - anotherUser.getRatings().get(x))) * mapUserCloseness.get(anotherUser)
////            ));
//            Map<Productik, Double> closeness = products.stream().collect(Collectors.toMap(
//                    x -> x,
//                    x -> (anotherUser.getRatings().get(x)) * mapUserCloseness.get(anotherUser)
//            ));
//            closenesses.put(anotherUser, closeness);
//        }
//        return closenesses;
//    }

//    private SortedSet<Map.Entry<Productik, Double>> getSingleClosenessesList(Map<User, Map<Productik, Double>> mapUserProductCloseness){
//        HashMap<Productik, Double> singleList = new HashMap<>();
//        for (User anotherUser : mapUserProductCloseness.keySet()) {
//            Map<Productik, Double> closeness = mapUserProductCloseness.get(anotherUser);
//            for (Productik product : closeness.keySet()) {
//                if (singleList.containsKey(product)) {
//                    if (singleList.get(product) < closeness.get(product))
//                        singleList.put(product, closeness.get(product));
//                } else
//                    singleList.put(product, closeness.get(product));
//            }
//        }
//        return entriesSortedByValues(singleList);
//    }

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
        Optional<Map.Entry<Productik, Double>> b1 = recommendedList.stream().filter(x -> x.getKey() == product1).findFirst();
        Optional<Map.Entry<Productik, Double>> b2 = recommendedList.stream().filter(x -> x.getKey() == product2).findFirst();

//        Map.Entry<Productik, Double> entry1 = recommendedList.stream().filter(x -> x.getKey() == product1) != null ? recommendedList.stream().filter(x -> x.getKey() == product1).findFirst().get() : null;
//        Map.Entry<Productik, Double> entry2 = recommendedList.stream().filter(x -> x.getKey() == product2) != null ? recommendedList.stream().filter(x -> x.getKey() == product2).findFirst().get() : null;
//        if (b1.isEmpty() && b2.isEmpty())
//            return Integer.MIN_VALUE;
        int a1 = !b1.isEmpty() ? recommendedList.tailSet(b1.get()).size() : Integer.MIN_VALUE;
        int a2 = !b2.isEmpty() ? recommendedList.tailSet(b2.get()).size() : Integer.MIN_VALUE;
        return a1 - a2;
    }
}
