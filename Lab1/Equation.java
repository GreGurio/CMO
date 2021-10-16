package com.gmail.gurik;

import java.util.Objects;
import static java.lang.Math.*;

public class Equation {
    int g; //Последняя цифра студентского
    int k; //Предпоследняя цифра студентского

    //Конструктор
    public Equation(int g, int k) {
        this.k = k;
        this.g = g;
    }

    //Метод решения задачи Коши методом Ейлера
    public double[][] methodEuler(int x0, int y0, int a, int b, double h) {
        //Последние известные х,у
        double x = (double) x0;
        double y = (double) y0;

        //Количество итераций
        int n =  (int) ( (b - a) / h);
        double t = 0.0;

        //Массивы результатов
        double[] resX = new double[n + 1];
        double[] resY = new double[n + 1];

        //Начальные значения
        resX[0] = x0;
        resY[0] = y0;


        //Нахождение результатов
        for(int i = 0; i < n; i++) {
            resX[i + 1] = x + h * dx(t, x, y);
            resY[i + 1] = y + h * dy(t, x, y);

            x = resX[i + 1];
            y = resY[i + 1];

            t += h;
        }

        return new double[][]{resX, resY};
    }

    //Метод решения задачи Коши методом Рунге-Кутта 4 порядка
    public double[][] methodRungheCutta4(int x0, int y0, int a, int b, double h) {
//Последние известные х,у
        double x = (double) x0;
        double y = (double) y0;

        //Количество итераций
        int n =  (int) ( (b - a) / h);
        double t = 0.0;

        //Массивы результатов
        double[] resX = new double[n + 1];
        double[] resY = new double[n + 1];

        //Начальные значения
        resX[0] = x0;
        resY[0] = y0;


        //Нахождение результатов
        for(int i = 0; i < n; i++) {
            double k0_x, k1_x, k2_x, k3_x;
            double k0_y, k1_y, k2_y, k3_y;

            //Коефициенты
            k0_x = h * dx(t, x, y);
            k0_y = h * dy(t, x, y);
            k1_x = h * dx(t + h / 2, x + k0_x / 2, y + k0_y / 2);
            k1_y = h * dy(t + h / 2, x + k0_x / 2, y + k0_y / 2);
            k2_x = h * dx(t + h / 2, x + k1_x / 2, y + k1_y / 2);
            k2_y = h * dy(t + h / 2, x + k1_x / 2, y + k1_y / 2);
            k3_x = h * dx(t + h, x + k2_x, y + k2_y);
            k3_y = h * dy(t + h, x + k2_x, y + k2_y);

            //Заполнение результата
            resX[i + 1] = x + (k0_x + 2 * k1_x + 2 * k2_x + k3_x) / 6;
            resY[i + 1] = y + (k0_y + 2 * k1_y + 2 * k2_y + k3_y) / 6;

            x = resX[i + 1];
            y = resY[i + 1];

            t += h;
        }

        return new double[][]{resX, resY};
    }

    //Уточнённый метод решения задачи Коши методом Ейлера
    public double[][] accurateEuler(int x0, int y0, int a, int b, double h, double accuracy) {
        double[] prevX, prevY;
        double[] resX = null, resY = null;
        double globalDelta = 10;
        do {
            prevX = resX;
            prevY = resY;
            double[][] res = methodEuler(x0, y0, a, b, h);
            resX = res[0];
            resY = res[1];
            if (prevX != null && prevY != null) {
                double[] deltaX = absErr(prevX, resX);
                double[] deltaY = absErr(prevY, resY);
                globalDelta = max(avgSqrErr(deltaX), avgSqrErr(deltaY));
            }
            h /= 2;
        } while(globalDelta >= accuracy);
        return new double[][]{resX, resY};
    }

    //Уточнённый метод решения задачи Коши методом Рунге-Кутта 4 Порядка
    public double[][] accurateRungheCutta(int x0, int y0, int a, int b, double h, double accuracy) {
        boolean firstIteration = true;
        double[] prevX, prevY; //Предыдущая итерация
        double[] resX = null, resY = null; //Текущие значения
        double globalDelta = accuracy + 1; //Глобальная погрешность

        do {
            prevX = resX;
            prevY = resY;

            double[][] res = methodRungheCutta4(x0, y0, a, b, h);

            resX = res[0];
            resY = res[1];

            if (!firstIteration) {
                double[] deltaX = absErr(prevX, resX);
                double[] deltaY = absErr(prevY, resY);
                globalDelta = max(avgSqrErr(deltaX), avgSqrErr(deltaY));
            }

            h /= 2;
            firstIteration = false;

        } while(globalDelta >= accuracy);
        return new double[][]{resX, resY};
    }



    //dx/dt - Производная х по t
    public double dx(double t, double x, double y) {
        return this.k * t + x - y + this.g;
    }
    //dy/dt - Производная y по t
    public double dy(double t, double x, double y) {
        return -x + this.k * y;
    }



    //Вычисление абсолютной погрешности
    public static double[] absErr(double[] arr1, double[] arr2) { //arr1 - приблизительное, arr2 - точное
        double [] delta = new double[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            delta[i] = abs(arr2[i * 2] - arr1[i]);
        }
        return delta;
    }

    //Вычисление средней квадратичной погрешности
    public static double avgSqrErr(double[] delta) {
        double sum = 0;
        int k = delta.length;
        for (double v : delta) {
            sum += pow(v, 2);
        }
        return sqrt(sum / (k - 1));
    }



    //Геттеры и сеттеры
    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    //Виртуальные методы
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return g == equation.g && k == equation.k;
    }

    @Override
    public int hashCode() {
        return Objects.hash(g, k);
    }

    @Override
    public String toString() {
        return "Equation{" +
                "g=" + g +
                ", k=" + k +
                '}';
    }
}
