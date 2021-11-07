import static java.lang.Math.*;

public class Test {
    public static void main(String[] args) {
        MyFunction function = new MyFunction(0,0);

        // Початкова точка
        final double x = 4 * 1 + 2;
        final double y = 1 - 3;
        Point start = new Point(0,0);

        Point rand = function.randVector(start);

        start.show();
        rand.show();
    }
}
