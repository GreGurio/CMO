public class Main {
    public static void main(String[] args) {

        // Ініціалізація значень
        final double k = 4;     // Передостання цифра студентського
        final double g = 1;     // Остання цифра студентського

        // Початкова точка
        final double x = k * g + 2;
        final double y = g - 3;
        Point start = new Point(x,y);

        final double h = 1; // Початкове значення кроку
        final double e = 0.01; // Точність пошуку
        final int points = 10000000; // Количество точек
        final double h1 = 0.000001;

        Result result = new Result(new Point(4.0, 1.0), -12.0);

        Function function = new MyFunction(g, k);

        Result result1; // Результат градієнтного методу
        Result result2; // Результат випадкового методу

        result1 = ((MyFunction)function).gradient(start, h, e);
        result2 = ((MyFunction)function).random(start, h1, points);

        result.show("Точний результат");
        result1.show("Результат градієнтного методу");
        result2.show("Результат випадкового методу");

    }
}
