import java.util.Random;

import static java.lang.Math.*;
/**
 * Реалізація інтерфейсу функції з двома параметрами та двома змінними.
 * f(x,y) = x^2 + (y - g)^2 - 2kgx + k
 * @author - Гуріненко Андрій, ТІ-91
 */

public class MyFunction implements Function {
    private final double g;   // Остання цифра студентського
    private final double k;   // Передостання цифра судентського

    // Конструктор з параметрами
    public MyFunction(double g, double k) {
        this.g = g;
        this.k = k;
    }

    /**
     * Метод обчислення заданої функції двох змінних.
     * @param x - перша змінна.
     * @param y - друга змінна.
     * @return - Повертає результат обчислення функції.
     */
    public double f(double x, double y) {
        return pow(x, 2) + pow((y - this.g), 2) - 2 * this.k * this.g * x + k;
    }

    /**
     *  Метод пошуку похідної функціх по .
     * @param x - змінна х.
     * @return - повертає значення похідної.
     */
    public double dx(double x) {
        return 2 * x - 8;
    }

    /**
     * Метод пошуку похідної по у.
     * @param y - змінна у.
     * @return - повертає значення похідної.
     */
    public double dy(double y) {
        return 2 * y - 2;
    }


    /**
     * Метод обчислення локального мінімуму заданої функції градієнтним методом з кроком поділу навпіл.
     * @param start - Початкова точка.
     * @param h - Початкове значення кроку.
     * @param e - Точність пошуку.
     * @return - Повертає резльтат зі значенням координат точки та значенням функції у ньому.
     */
    public Result gradient(Point start, double h, double e) {
        double pred_x = start.x;
        double pred_y = start.y;
        double x,y;

        do {
            x = pred_x - h * dx(pred_x);
            y = pred_y - h * dy(pred_y);

            h /= 2;

        } while((pow(dx(x), 2) + pow(dy(y), 2)) >= e);

        return new Result(new Point(x,y),f(x,y));
    }

    /**
     * Метод пошуку локального мінімуму методом адаптивного випадкового пошуку для багатовимірного випадку. Рандомно
     * шукаєтся одиничний вектор, що буде визначати напрямок пошуку, і при знаходженні потрібного напрямку значення
     * змінюється.
     *
     *  Джерело: https://studbooks.net/2113950/informatika/metody_sluchaynogo_poiska
     *
     * @param start - Початкова точка.
     * @param h - Крок.
     * @param loops - Кількість ітерацій рандомного пошуку
     * @return - Повертає значення координат точки та значення функції у ній.
     */
    public Result random(Point start, double h, int loops) {

        Point min = new Point(start.x,start.y);
        double x,y;

        for (int i = 0; i < loops; i++) {

            Point rand = randVector(min);   // Отримання рандомного одиничного вектора, що буде визначати напрямок
                                            // пошуку.

            x = min.x - h * dx(rand.x);
            y = min.y - h * dy(rand.y);

            if (f(x,y) < f(min.x, min.y)) {
                min = new Point(x, y);
            }
        }
        return new Result(min, f(min.x, min.y));
    }

    /**
     * Метод для отримання рандомного одиничного вектора.
     * @param start - Початкова точка.
     * @return - Повертає точку кінця вектора.
     */
    public Point randVector(Point start) {
        Random random = new Random();
        int angle = random.nextInt(361); // Кут вектора

        // Знаходження координат кінця вектора під знайденим кутом.
        double x = start.x + sin(angle * PI / 180);
        double y = start.y + cos(angle * PI / 180);
        return new Point(x,y);
    }
}
