package com.example.cakemachine.cli.console;

import java.util.Scanner;

/**
 * @author zhangweijie, E-mail:zhangweijiepku@gmail.com
 */
public class ConsoleImpl {
    private Scanner scanner = new Scanner(System.in);

    public String readString(String text) {
        while (true){
            write(text);
            if (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if ("".equals(s)){
                    System.out.println("Leere Eingabe!");
                    continue;
                } else {
                    return s;
                }
            }
            scanner.nextLine();
        }
    }

    public int readInteger(String text) {
        while (true) {
            write(text);
            if (scanner.hasNextInt()){
                int integer = scanner.nextInt();
                scanner.nextLine();
                return integer;
            }
            System.out.println("Falsche Eingabe!");
            scanner.nextLine();
        }
    }

    public void write(String s) {
        System.out.print(s);
    }
}