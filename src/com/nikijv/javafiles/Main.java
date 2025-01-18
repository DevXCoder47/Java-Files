package com.nikijv.javafiles;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final String CURRENT_PATH = System.getProperty("user.dir");
    public static void main(String[] args) throws IOException {
//        task1();
//        task2();
//        task3();
        task4();
    }

    public static void task1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first path: ");
        String firstPath = scanner.nextLine();
        System.out.print("Enter second path: ");
        String secondPath = scanner.nextLine();

        Path filePath1 = Paths.get(CURRENT_PATH, firstPath);
        Path filePath2 = Paths.get(CURRENT_PATH, secondPath);

        try(BufferedReader reader1 = new BufferedReader(new FileReader(filePath1.toFile()));
            BufferedReader reader2 = new BufferedReader(new FileReader(filePath2.toFile()))
            ) {

            String line1;
            String line2;

            while((line1 = reader1.readLine()) != null){
                line2 = reader2.readLine();

                if(!line1.equals(line2)){
                    System.out.println("file1: " + line1 + " file2: " + line2);
                }

            }

        }

        catch(IOException e){
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    public static void task2() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path: ");
        String path = scanner.nextLine();
        Path filePath = Paths.get(CURRENT_PATH, path);

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            List<String> lines = new ArrayList<>();

            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

            int max = 0;

            for(String item : lines){

                if(item.length() > max){
                    max = item.length();
                    line = item;
                }

            }
            System.out.println("The longest line: " + line + "\nLength: " + max);
        }

        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    public static void task3() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path: ");
        String path = scanner.nextLine();
        Path filePath = Paths.get(CURRENT_PATH, path);

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            List<List<Integer>> arrays = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\d+");

            while((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                List<Integer> list = new ArrayList<>();

                while(matcher.find()) {
                    list.add(Integer.parseInt(matcher.group()));
                }

                arrays.add(list);
            }

            info(arrays);
        }

        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    public static void task4() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter path: ");
        String path = scanner.nextLine();
        Path filePath = Paths.get(CURRENT_PATH, path);
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        scanner.nextLine();
        List<Integer> array = new ArrayList<>(size);

        for(int i = 0; i < size; i++){
            System.out.print("Enter number: ");
            array.add(scanner.nextInt());
            scanner.nextLine();
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))){
            for(Integer item : array){
                writer.write(String.valueOf(item) + " ");
            }

            writer.newLine();

            for(Integer item : array){

                if(item % 2 == 0)
                    writer.write(String.valueOf(item) + " ");

            }

            writer.newLine();

            for(Integer item : array){

                if(item % 2 != 0)
                    writer.write(String.valueOf(item) + " ");

            }

            writer.newLine();

            List<Integer> reversedArray = array.reversed();

            for(Integer item : reversedArray){
                writer.write(String.valueOf(item) + " ");
            }
        }

        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

    private static void info(List<List<Integer>> arrays) {
        int totalSum = 0;
        for(List<Integer> array : arrays){
            Integer sum = 0;
            Integer max = array.getFirst();
            Integer min = array.getFirst();
            for(Integer i : array){
                System.out.print(i + " ");
                sum += i;

                if(i > max)
                    max = i;

                if(i < min)
                    min = i;
            }
            System.out.print("              Sum - " + sum + ", max - " + max + ", min - " + min);
            totalSum += sum;
            System.out.println();
        }
        System.out.println("Total sum: " + totalSum);
    }
}
