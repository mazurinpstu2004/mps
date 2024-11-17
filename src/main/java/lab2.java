
import java.util.Scanner;
import java.util.Random;

public class lab2 {
    private static final Random r = new Random();
    private static final Scanner sc = new Scanner(System.in);


    private static final String[] stringOutput = {"Холодная вода", "Кипяток"};

    private static final int[] intOutput = {0, 0, 1, 1};

    private static final String[] stringState = {"Покой", "Работа"};

    private static final double[][] probability = {
            {0.05, 0.2, 0.2, 1.0},
            {0.8, 0.9, 0.9, 1.0},
            {0.05, 0.15, 0.2, 1.0},
            {0.05, 0.1, 0.85, 1.0},
    };

    public static void main(String[] args) {
        System.out.println("Метод вероятностных автоматов для моделирования поведения электрического чайника");
        System.out.println("Исходное состояние:");
        System.out.println("1 - Покой;\n2 - Работа");
        int initial_state = sc.nextInt() - 1;

        System.out.println("Требуется выбрать входной сигнал для электрического чайника:");
        System.out.println("1 - Включить;\n2 - Выключить");
        int input_signal = sc.nextInt() - 1;

        while (input_signal >= 0) {
            double p = r.nextDouble();
            System.out.println("Вероятность: " + p);
            int state = initial_state * 2 + input_signal;
            int nextState = 0;
            for (int i = 0; i < intOutput.length; i++) {
                if (p >= probability[state][i]) {
                    nextState = intOutput[i];
                }
            }
            System.out.println("Состояние: " + stringState[nextState]);
            System.out.println("Выходной сигнал: " + stringOutput[nextState]);
            input_signal = sc.nextInt() - 1;
            initial_state = nextState;
        }
    }
}
