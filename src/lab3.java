import java.util.HashSet;

public class lab3 {

    private static final long M = (long) Math.pow(2, 32);
    private static final int K = 472308099;
    private static final int b = 0;
    private static double r = 1;
    private static double me = 0;
    private static double d = 0;

    public static void main(String[] args) {
        double[] arr = new double[10000000];

        double p1 = 0;
        double p2 = 0.5;
        double p3 = 1;

        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = genNumbers();
            if (arr[i] > p1 && arr[i] < p2) {
                count1++;
            } else if (arr[i] > p2 && arr[i] < p3) {
                count2++;
            }
        }

        System.out.println();
        me = mathematicalExpectation(arr);
        System.out.println("Мат. ожидание: " + me);

        d = dispersion(arr);
        System.out.println("Дисперсия: " + d);

        double mse = meanSquaredError();
        System.out.println("Среднеквадратичное отклонение: " + mse);

        int period = period(arr);
        System.out.println("Длина периода ГСПЧ: " + period);

        double a = me - mse;
        double b = me + mse;
        double c = (b - a) * 100;
        System.out.println("Частотный тест: " + c + " %");

        System.out.println("Количество чисел на интервале (0, 0.5): " + count1);
        System.out.println("Количество чисел на интервале (0.5, 1): " + count2);
    }

    private static double genNumbers() {
        r = (K * r + b) % M;
        return r / M;
    }
    private static double mathematicalExpectation(double[] arr) {
        double p = (double) 1 / arr.length;
        int i = 0;
        while (i < arr.length) {
            me += p * arr[i];
            i++;
        }
        return me;
    }

    private static double dispersion(double[] arr) {
        double p = (double) 1 / arr.length;
        double me_squared = 0;
        int i = 0;
        while (i < arr.length) {
            me_squared += p * Math.pow(arr[i], 2);
            i++;
        }
        d = me_squared - Math.pow(me, 2);
        return d;
    }

    private static double meanSquaredError() {
        return Math.sqrt(d);
    }
    private static int period(double[] arr) {
        HashSet<Double> numb = new HashSet<>();
        for (double value : arr) {
            if (numb.contains(value)) {
                return numb.size();
            }
            numb.add(value);
        }
        return 0;
    }
}