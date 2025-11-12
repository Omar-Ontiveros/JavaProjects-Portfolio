package OOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class test {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(12, 493, 5, 87, 2, 65, 731, 9, 34, 576,
                18, 402, 7, 91, 23, 584, 11, 67, 305, 88,
                4, 472, 39, 56, 120, 9, 634, 77, 1, 258,
                46, 82, 390, 14, 509, 27, 71, 312, 6, 98,
                3, 405, 21, 59, 74, 180, 8, 267, 53, 400,
                36, 92, 15, 78, 240, 5, 67, 302, 44, 89,
                19, 501, 32, 61, 77, 134, 10, 298, 55, 470,
                20, 83, 310, 7, 498, 25, 70, 360, 4, 95,
                2, 413, 28, 60, 79, 165, 12, 289, 50, 430,
                22, 81, 317, 6, 479, 31, 68, 345, 9, 91));
        TwoSum();
        reversedWord();
        countVowels();
        duplicateNum();
        primo();
        sum(list);

        Random random = new Random();
        char letra = random.nextBoolean() ? 'A' : 'B';
        int numero = random.nextInt(1, 3);

        System.out.println(letra + " " + numero);
    }

    public static void TwoSum() {
        int nums[] = {2, 7, 11, 15};
        int target = 3;
        ArrayList<ArrayList<Integer>> indexs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                ArrayList subList = new ArrayList<>();
                if (sum == target) {
                    subList.add(i);
                    subList.add(j);
                    indexs.add(subList);
                }
            }

        }
        if (indexs.isEmpty()) {
            System.out.println("[[-1, -1]]");
        } else {
            System.out.println(indexs);
        }
    }

    public static void reversedWord() {
        String[] words = "welcome to IBM".split(" ");
        StringBuilder reversedWord = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedWord.append(words[i]).append(" ");
        }

        System.out.println(reversedWord.toString().trim());
    }

    public static void countVowels() {
        String word = "Programming";
        int count = 0;
        for (Character c : word.toCharArray()) {
            if ("aeiouAEIOU".indexOf(c) != -1) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void duplicateNum() {
        int[] nums = {1, 2, 2, 3, 4, 4, 5, 6, 6, 6, 6};
        int[] distincNums = Arrays.stream(nums).distinct().toArray();
        System.out.println(Arrays.toString(distincNums));
    }

    public static void primo() {
        int numeroPrimo = 4;
        boolean isPrimo = false;

        for (int i = 1; i < Math.sqrt(numeroPrimo); i++) {
            if (numeroPrimo % i == 0) {
                isPrimo = false;
                break;
            }
        }
        System.out.println(isPrimo);

    }

    public static void sum(List<Integer> arr) {
        long total = 0;

        // Convertimos la lista a una PriorityQueue (min-heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>(arr);

        while (pq.size() > 1) {
            int a = pq.poll(); // saca el menor
            int b = pq.poll(); // saca el siguiente menor
            long suma = (long) a + b; // usar long para evitar overflow
            total = suma;
            pq.add((int) suma); // agregamos la suma al heap
        }

        System.out.println(pq.peek()); // valor final
        System.out.println(total);     // última suma realizada
    }
}
