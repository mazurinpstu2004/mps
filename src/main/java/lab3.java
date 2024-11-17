
import java.util.HashSet;

public class lab3 {

    private static final long M = (long) Math.pow(2, 32);
    private static final int K = 472308099;
    private static final int b = 0;
    private static double r = 1;
    private static double meGen = 0;
    private static double dGen = 0;

    public static void main(String[] args) {
        double[] arr = new double[5000000];
        double[] randomArr = new double[5000000];

        double p1 = 0;
        double p2 = 0.5;
        double p3 = 1;

        int countGen1 = 0;
        int countGen2 = 0;

        int countRand1 = 0;
        int countRand2 = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = genNumbers();
            if (arr[i] > p1 && arr[i] < p2) {
                countGen1++;
            } else if (arr[i] > p2 && arr[i] < p3) {
                countGen2++;
            }
        }
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = Math.random();
            if (randomArr[i] > p1 && randomArr[i] < p2) {
                countRand1++;
            } else if (randomArr[i] > p2 && randomArr[i] < p3) {
                countRand2++;
            }
        }

        System.out.println("\nГенерация линейным конгруэнтным методом\n");
        meGen = mathematicalExpectation(arr);
        System.out.println("Мат. ожидание: " + meGen);

        dGen = dispersion(arr, meGen);
        System.out.println("Дисперсия: " + dGen);

        double mseGen = meanSquaredError(dGen);
        System.out.println("Среднеквадратичное отклонение: " + mseGen);

        int periodGen = period(arr);
        System.out.println("Длина периода ГСПЧ: " + periodGen);

        double aGen = meGen - mseGen;
        double bGen = meGen + mseGen;
        double cGen = (bGen - aGen) * 100;
        System.out.println("Частотный тест: " + cGen + " %");

        System.out.println("Количество чисел на интервале (0, 0.5): " + countGen1);
        System.out.println("Количество чисел на интервале (0.5, 1): " + countGen2);

        meGen = 0;
        dGen = 0;

        System.out.println("\nГенерация с помощью Math.random\n");

        double meRand = mathematicalExpectation(randomArr);
        System.out.println("Мат. ожидание: " + meRand);

        double dRand = dispersion(randomArr, meRand);
        System.out.println("Дисперсия: " + dRand);

        double mseRand = meanSquaredError(dRand);
        System.out.println("Среднеквадратичное отклонение: " + mseGen);

        int periodRand = period(randomArr);
        System.out.println("Длина периода ГСПЧ: " + periodRand);

        double aRand = meRand - mseRand;
        double bRand = meRand + mseRand;
        double cRand = (bRand - aRand) * 100;
        System.out.println("Частотный тест: " + cRand + " %");

        System.out.println("Количество чисел на интервале (0, 0.5): " + countRand1);
        System.out.println("Количество чисел на интервале (0.5, 1): " + countRand2);


    }

    private static double genNumbers() {
        r = (K * r + b) % M;
        return r / M;
    }
    private static double mathematicalExpectation(double[] arr) {
        double p = (double) 1 / arr.length;
        int i = 0;
        while (i < arr.length) {
            meGen += p * arr[i];
            i++;
        }
        return meGen;
    }

    private static double dispersion(double[] arr, double me) {
        double p = (double) 1 / arr.length;
        double me_squared = 0;
        int i = 0;
        while (i < arr.length) {
            me_squared += p * Math.pow(arr[i], 2);
            i++;
        }
        dGen = me_squared - Math.pow(me, 2);
        return dGen;
    }

    private static double meanSquaredError(double dispersion) {
        return Math.sqrt(dispersion);
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
