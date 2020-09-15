package ShopApp.ConsoleScenarios;


import java.util.Scanner;

public class BaseConsoleScenario {
    protected final String NAME = "Name";
    protected final String PRODUCER = "Producer";
    protected final String PRICE = "Price";
    protected final String RATING = "Rating";
    protected final int COLUMN_GAP = 3;
    protected final String ID = "Id";
    protected final String LAST_TIME_MODIFIED = "Last time modified";
    protected final String STATE = "State";

    protected String cellVal(int length, String content){
        return String.format("%-" + (length + COLUMN_GAP) + "s", content);
    }
    protected static Scanner scanner = new Scanner(System.in);

    protected String getAnswer(String question, boolean nullOrEmptyProhibited){
        while (true) {
            System.out.println(question);
            String answer = scanner.nextLine();
            if (nullOrEmptyProhibited && (answer == null || answer.trim().isEmpty()))
                continue;
            return answer;
        }
    }

    //TODO: К сожалению, не удалось заставить это работать
//    public <T extends Number & Comparable<T>> T getAnswer(String question, T min, T max){
//         while (true) {
//            try {
//                System.out.println(question);
////                T val = scanner.nextInt();
//                T val = null;
//                scanner.nextLine();
//
//                if (val < min || val > max){
//                    System.out.println("Value is out of bounds");
//                    continue;
//                }
//                return val;
//            } catch (Exception e) {
//                System.out.println("Input error. Try again");
//                continue;
//            }
//        }
//    }



    protected int getAnswer(String question, Integer min, Integer max){
        while (true) {
            try {
                System.out.println(question);
                int val = scanner.nextInt();
                scanner.nextLine();
                if ((min != null && val < min) || (max != null && val > max)){
                    System.out.println("Value is out of bounds");
                    continue;
                }
                return val;
            } catch (Exception e) {
                System.out.println("Input error. Try again");
                continue;
            }
        }
    }

    protected double getAnswer(String question, Double min, Double max){
        while (true) {
            try {
                System.out.println(question);
                int val = scanner.nextInt();
                scanner.nextLine();
                if ((min != null && val < min) || (max != null && val > max)){
                    System.out.println("Value is out of bounds");
                    continue;
                }
                return val;
            } catch (Exception e) {
                System.out.println("Input error. Try again");
                continue;
            }
        }
    }

    protected boolean getAnswerYesNo(String question){
        while (true) {
            try {
                System.out.println(question);
                String val = scanner.nextLine();
                if ("yes".equals(val))
                    return true;
                if ("no".equals(val))
                    return false;
                System.out.println("Value is out of bounds");
            } catch (Exception e) {
                System.out.println("Input error. Try again");
                continue;
            }
        }
    }

    protected void pressEnter(){
        System.out.println("Press enter...");
        scanner.nextLine();
    }

}
