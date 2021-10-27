package com.gmail.gurik;


public class Main {
    public static void main(String[] args) {

        // Ініціалізація параметрів
        final double k = 4;                 // Передостання цифра студентського
        final double g = 1;                 // Остання цифра студентського
        final double a = (- k * g - 2);     // Початок інтервалу
        final double b = (k * g + 1);       // Кінець інтервалу

        final double accuracy = 0.1;        // Точність пошуку - для пошуку поділом навпіл
        final int points = 100;          // Кільксьть точок - для випадкового пошуку

        MyFunction function = new MyFunction(k, g);

        Result trueRes = new Result(-4,-12); //Точний результат

        Result result1 = function.minDevide(a, b, accuracy);
        Result result2 = function.minRandom(points, a, b);

        //-----------ВИВІД РЕЗУЛЬТАТІВ----------------
        trueRes.show("ТОЧНИЙ РЕЗУЛЬТАТ");

        result1.show("МЕТОД ПОДІЛУ");
        System.out.println("DELTA X: " + (result1.x - trueRes.x));
        System.out.println("DELTA F(x): " + (result1.fx - trueRes.fx) + "\n");

        result2.show("МЕТОД ВИПАДКОВОГО ПОШУКУ");
        System.out.println("DELTA X: " + (result2.x - trueRes.x));
        System.out.println("DELTA F(x): " + (result2.fx - trueRes.fx) + "\n");
    }
}
