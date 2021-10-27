package com.gmail.gurik;

/**
 * Класс для збереження результатів локального мінімуму функції
 * @author - Гуріненко Андрій, група ТІ-91
 */

public class Result {
    public final double x;  // Аргумент
    public final double fx; // Функція

    //Конструктор з переметрами
    public Result(double x, double fx) {
        this.x = x;
        this.fx = fx;
    }

    //Функція виводу результатів на екран
    public void show(String message) {
        Messages.yellow("========" + message + "========");
        System.out.println("Xmin = \t\t" + x + ";");
        System.out.println("F(Xmin) = \t" + fx + ";\n");
    }
}
