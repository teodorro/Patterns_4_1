package ShopApp;

import java.util.Scanner;

public class InputProcessor {
    protected static Scanner scanner = new Scanner(System.in);

    protected String getAnswer(String question, boolean notNullOrEmptyProhibited){
        while (true) {
            System.out.println(question);
            String answer = scanner.nextLine();
            if (notNullOrEmptyProhibited && (answer == null || answer.trim().isEmpty()))
                continue;
            return answer;
        }
    }

//    public <T extends Number & Comparable<T>> T getAnswer(String question, T min, T max){
//         while (true) {
//            try {
//                System.out.println(question);
////                T val = scanner.nextInt();
//                T val = null;
//                scanner.nextLine();
//
//                if (val < min || val > max){
//                    System.out.println("Значение вне допустимого диапазона");
//                    continue;
//                }
//                return val;
//            } catch (Exception e) {
//                System.out.println("Ошибка при вводе. Попробуйте еще раз");
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
                    System.out.println("Значение вне допустимого диапазона");
                    continue;
                }
                return val;
            } catch (Exception e) {
                System.out.println("Ошибка при вводе. Попробуйте еще раз");
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
                    System.out.println("Значение вне допустимого диапазона");
                    continue;
                }
                return val;
            } catch (Exception e) {
                System.out.println("Ошибка при вводе. Попробуйте еще раз");
                continue;
            }
        }
    }

    protected void pressEnter(){
        System.out.println("Нажмите enter...");
        scanner.nextLine();
    }
}
