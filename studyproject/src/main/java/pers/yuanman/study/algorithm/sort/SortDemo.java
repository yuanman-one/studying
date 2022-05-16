package pers.yuanman.study.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = genList();
        System.out.println("================未排序之前-------------------------");
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("================插入排序之后-------------------------");
        SimpleSort.insertSort(list);
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();

        list = genList();
        System.out.println("================未排序之前-------------------------");
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("================希尔排序之后-------------------------");
        SimpleSort.shellSort(list);
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();

        list = genList();
        System.out.println("================未排序之前-------------------------");
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("================选择排序之后-------------------------");
        SimpleSort.selectSort(list);
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();

        list = genList();
        System.out.println("================未排序之前-------------------------");
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("================冒泡排序之后-------------------------");
        SimpleSort.bubbleSort(list);
        list.forEach(s -> System.out.print(s + " "));
        System.out.println();

        int[] strings = genStrings();
        System.out.println("================未排序之前-------------------------");
        Arrays.stream(strings).forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("================堆排序之后-------------------------");
        SimpleSort.heapSort(strings);
        Arrays.stream(strings).forEach(s -> System.out.print(s + " "));
        System.out.println();

        strings = genStrings();
        System.out.println("================未排序之前-------------------------");
        Arrays.stream(strings).forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("================快速排序之后-------------------------");
        SimpleSort.quickSort(strings);
        Arrays.stream(strings).forEach(s -> System.out.print(s + " "));
        System.out.println();

        strings = genStrings();
        System.out.println("================未排序之前-------------------------");
        Arrays.stream(strings).forEach(s -> System.out.print(s + " "));
        System.out.println();
        System.out.println("================归并排序之后-------------------------");
        SimpleSort.mergeSort(strings);
        Arrays.stream(strings).forEach(s -> System.out.print(s + " "));
        System.out.println();


    }

    public static ArrayList<Integer> genList() {
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            int nextInt = random.nextInt(101);
            list.add(nextInt);
        }
        return list;
    }

    public static int[] genStrings() {
        Random random = new Random();
        int[] strings = new int[21];
        for (int i = 0; i < 21; i++) {
            int nextInt = random.nextInt(101);
            strings[i] = nextInt;
        }
        return strings;
    }
}
