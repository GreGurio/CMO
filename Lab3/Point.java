/**
 * Класс, що зберігає в собі значення деякої точки.
 * @author - Гуріненко Андрій, ТІ-91
 */
public class Point {
    public final double x;
    public final double y;

    // Конструктор з параметрами
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Виведення значення точки на екран
    public void show() {
        System.out.println("( " + this.x  + ", " + this.y + " )");
    }

    // Виведення значення точки на екран з заголовком
    public void show(String message) {
        System.out.println("========="  + message + "точка========");   // Заголовок
        System.out.println("( " + this.x  + ", " + this.y + " )");
    }
}
