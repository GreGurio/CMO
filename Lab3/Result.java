/**
 * Класс для здереження результатів пошуку локального мінімуму функції, що зберігає в собі значення змінних і самої
 * функції.
 * @author - Гуріненко Андрій, ТІ-91.
 */
public class Result {
    public final Point point;   // Точка або значення змінних х,у
    public final double f;      // Значення функції

    //Конструктор з параметрами
    public Result(Point point, double f) {
        this.point = point;
        this.f = f;
    }

    //Функція виводу результатів на екран

    /**
     * Метод для виводу результату на екран
     * @param message - Повідомлення, що буде виведено у заголовку.
     */
    public void show(String message) {
        Messages.yellow("========" + message + "========");
        System.out.println("Xmin = \t\t" + point.x + ";");
        System.out.println("Ymin = \t\t" + point.y + ";");
        System.out.println("F(Xmin) = \t" + f + ";\n");
    }

}

