import java.util.Scanner;

public class lab1 {
    private static final Scanner sc = new Scanner(System.in);

    private static final String[][] stringState = {{"Переход в состояние «Ожидание ПИН-кода»", "Ничего не произошло", "Ничего не произошло", "Ничего не произошло"},
            {"Ничего не произошло", "Переход в состояние «Выбор операции»", "Ничего не произошло", "Ничего не произошло"},
            {"Остается в том же состоянии", "Ничего не произошло", "Переход в состояние «Выполнение операции»", "Ничего не произошло"},
            {"Остается в том же состоянии", "Ничего не произошло", "Переход в состояние «Выполнение операции»", "Ничего не произошло"},
            {"Ничего не произошло", "Переход в состояние «Ожидание карты»", "Переход в состояние «Ожидание карты»", "Переход в состояние «Ожидание карты»"}};

    private static final int[][] intState = {
            {1, -1, -1, -1},
            {-1, 2, -1, -1},
            {-1, -1, 3, -1},
            {-1, -1, 3, -1},
            {0, 0, 0, 0}
    };
    public static void main(String[] args) {
        System.out.println("Метод конечных автоматов для моделирования поведения банкомата");
        System.out.println("Требуется выбрать исходное состояние банкомата:");
        System.out.println("1 - ожидание карты;\n2 - ожидание ввода ПИН-кода;\n3 - ожидание выбора операции;\n4 - выполнение операции.");
        int initial_state = sc.nextInt() - 1;

        System.out.println("Требуется выбрать входной сигнал для банкомата:");
        System.out.println("1 - вставка или прикладывание карты;\n2 - ввод ПИН-кода;\n3 - нажатие на кнопку «Пополнить»;\n4 - нажатие на кнопку «Снять»;\n5 - нажатие на кнопку «Выход».");
        int input_signal = sc.nextInt() - 1;


        while (input_signal >= 0) {
            System.out.println(stringState[input_signal][initial_state]);
            int temp_state = intState[input_signal][initial_state];
            if (temp_state != -1) {
                initial_state = temp_state;
            }
            input_signal = sc.nextInt() - 1;
        }
    }
}
