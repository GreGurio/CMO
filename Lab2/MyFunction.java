package com.gmail.gurik;

import java.util.Random;
import static java.lang.Math.*;

/**
 * Реалізація інтерфейсу функції з двома параметрами та однією змінною
 * x^2 + 2kgx + k
 * @author - Гуріненко Андрій, ТІ-91
 */

public class MyFunction implements Function {
    private final double k;   // Передостання цифра студентського
    private final double g;   // Остання цифра студентського

    // Конструтор з параметрами
    public MyFunction(double k, double g) {
        this.k = k;
        this.g = g;
    }

    /**
     * Обрахунок результату функції.
     * @param x - незалежна змінна.
     * @return - результат обчислення функції.
     */
    public double execute(double x) {
        return pow(x, 2) + 2 * this.k * this.g * x + this.k;
    }

    /**
     * Метод для пошуку локального мінімуму функції методом зменшення інтервалу поділом навпіл. Див. методичні
     * рекомендації.
     * @param a - початок інтервалу.
     * @param b - кінець інтервалу.
     * @param accuracy - точність пошуку.
     * @return - повертає локальний мінімум функції.
     */
    public Result minDevide(double a, double b, double accuracy) {
        double L = 0;
        double xm = 0, x1 = 0, x2 = 0;
        double fxm = 0, fx1 = 0, fx2 = 0;

        do {
            // Крок 1
            xm = (a + b) / 2;
            L = b - a;
            fxm = execute(xm);

            // Крок 2
            x1 = a + L / 4;
            x2 = b - L / 4;
            fx1 = execute(x1);
            fx2 = execute(x2);

            // Крок 3
            if (fx1 < fxm) {
                b = xm;
                xm = x1;
            } else {

                // Крок 4
                if (fx2 < fxm) {
                    a = xm;
                    xm = x2;
                } else {
                    a = x1;
                    b = x2;
                }
            }

            // Крок 5
            L = b - a;

        } while (abs(L) > accuracy);

        //Поиск минимума
        double min = execute(x1);
        double minx = x1;
        if (execute(x2) < min) {
            min = execute(x2);
            minx = x2;
        }
        if (execute(xm) < min) {
            min = execute(xm);
            minx = xm;
        }
        return new Result(minx, min);
    }

    /**
     * Метод для розрахунку локального мінімуму функції методом випадкового пошуку.
     * @param points - кількість точок.
     * @param a - початок інтервалу.
     * @param b - кінець інтервалу.
     * @return - повертає об'єкт з локальним мінімумом.
     */
    public Result minRandom(int points, double a, double b) {
        Random random = new Random();
        double[] array = new double[points];
        double min = 0;
        double minx = 0;
        for (int i = 0; i < points; i++) {
            array[i] = a + random.nextDouble()*(b - a);

            if(i == 0) {
                min = execute(array[i]);
                minx = array[i];
            }

            if (array[i] < min) {
                min = execute(array[i]);
                minx = array[i];
            }
        }
        return new Result(minx, min);
    }
}
