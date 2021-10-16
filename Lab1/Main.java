package com.gmail.gurik;

import static com.gmail.gurik.Messages.*;
import static java.lang.Math.*;

//Студент Гуріненко Андрій Русланович, група ТІ-91, ТЕФ 2021
// Номер студака - 12648241
//
// Последняя цифра g = 1
// Предпоследняя цифра k = 4

public class Main {


    public static void main(String[] args) {
        //Точные значения
        final double x = 6.33577607806101;
        final double y = -7.699456382927064;

        //Объявление параметров системы уравнений
        int g = 1, k = 4;
        int x0 = 0, y0 = 0;
        int a = 0, b = 1;
        double h = 0.1;
        double accuracy = 0.1;

        //Создание объекта уравнения
        Equation myEquation = new Equation(g, k);

        //Подсчёт результатов
        double[][] result = myEquation.methodEuler(x0, y0, a, b, h);
        double[][] result2 = myEquation.methodRungheCutta4(x0, y0, a, b, h);
        double[][] result3 = myEquation.accurateEuler(x0, y0, a, b, h, accuracy);
        double[][] result4 = myEquation.accurateRungheCutta(x0, y0, a, b, h, accuracy);

        //Вывод результатов
        showResult(result, h, "Метод Ейлера");
        showResult(result2, h, "Метод Рунге-Кутта 4 Порядка");

        showFinalResult(result3, "Уточнённый метод Ейлера");
        showFinalResult(result4, "Уточнённый метод Рунге-Кутта 4 Порядка");
        showFinalResult(new double[][]{{x},{y}}, "Точный результат");

        showErrors(result, result2, result3, result4, new double[][]{{x},{y}});
    }

    //Вывод последних значений
    static public void showFinalResult(double[][] result, String heading) {
        warn("RESULT");
        warn("[" + heading + "]");
        warn("----------------------------------------");
        System.out.println("x = " + result[0][result[0].length - 1]);
        System.out.println("y = " + result[1][result[1].length - 1]);
        warn("----------------------------------------");
    }

    //Вывод всех результатов на экран
    static public void showResult(double[][] result, double h, String heading) {

        double[] x = result[0];
        double[] y = result[1];

        float t = 0;

        warn("RESULT");
        warn("[" + heading + "]");
        warn("----------------------------------------");
        for (int i = 0; i < x.length; i++) {
            System.out.println(i + ". x(" + String.format("%.1f", t) + ") = " + String.format("%.3f" , x[i]) + ", \t" +
                    " y(" + String.format("%.1f", t) + ") = " + String.format("%.3f", y[i]) + ";");
            t += h;
        }
        warn("----------------------------------------");
    }

    public static void showErrors(
            double[][] resE, // Метод Ейлера
            double[][] resRC, // Метод Рунге-Кутта 4 Пордяка
            double[][] resAE, // Уточнённый метод Ейлера
            double[][] resARC, // Уточнённый метод Рунге-Кутта
            double[][] resA) // Точные значения
    {

        warn("ERRORS");
        warn("----------------------------------------");
        warn("[Метод Ейлера]:");

        System.out.println("Delta X = " + String.format("%.7f", abs(resA[0][resA[0].length-1]-resE[0][resE[0].length-1])) + "...");
        System.out.println("Delta Y = " + String.format("%.7f", abs(resA[1][resA[1].length-1]-resE[1][resE[1].length-1])) + "...");
        warn("[Метод Рунге-Кутта 4 Порядка]:");
        System.out.println("Delta X = " + String.format("%.7f", abs(resA[0][resA[0].length-1]-resRC[0][resRC[0].length-1])) + "...");
        System.out.println("Delta Y = " + String.format("%.7f", abs(resA[1][resA[1].length-1]-resRC[1][resRC[1].length-1])) + "...");
        warn("[Уточнённый Метод Ейлера]:");
        System.out.println("Delta X = " + String.format("%.7f", abs(resA[0][resA[0].length-1]-resAE[0][resAE[0].length-1])) + "...");
        System.out.println("Delta Y = " + String.format("%.7f", abs(resA[1][resA[1].length-1]-resAE[1][resAE[1].length-1])) + "...");
        warn("[Уточнённый Метод Рунге-Кутта 4 Порядка]:");
        System.out.println("Delta X = " + String.format("%.7f", abs(resA[0][resA[0].length-1]-resARC[0][resARC[0].length-1])) + "...");
        System.out.println("Delta Y = " + String.format("%.7f", abs(resA[1][resA[1].length-1]-resARC[1][resARC[1].length-1])) + "...");
        warn("----------------------------------------");
    }

}
