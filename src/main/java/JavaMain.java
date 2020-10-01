package main.java;

import main.kotlin.KotlinUtils;

public class JavaMain {

    public static void main(String[] args) {

        int a = 3;
        int b = 4;
        System.out.println("Llamando a método Kotlin: " + KotlinUtils.maximum(a, b));

        if (a > b) {
            System.out.println("a es mayor!!");
        } else {
            System.out.println("a no es mayor!!");
        }

    }

}