
public class lab4 {
    public static void main(String[] args) {
        int N = 2;
        double processTime = 1.5;
        double a = 0.5;
        int workSchedule = 840;
        int iterations = 150000;
        int[] sumProcesses = new int[N + 1];
        for (int i = 0; i < iterations; i++) {
            int[] completeProcesses = new int[N + 1];
            double[] releaseTime = new double[N];
            double currentTime = 0;
            while (currentTime <= workSchedule) {
                currentTime += -1 / a * Math.log(Math.random());
                int line = -1;
                for (int j = 0; j < releaseTime.length; j++) {
                    if (releaseTime[j] <= currentTime) {
                        line = j;
                        break;
                    }
                }
                if (line == -1) {
                    completeProcesses[N]++;
                } else {
                    completeProcesses[line]++;
                    releaseTime[line] = currentTime + processTime;
                }
            }
            for (int j = 0; j < N + 1; j++) {
                sumProcesses[j] += completeProcesses[j];
            }
        }
        System.out.println("Среднее количество обслуженных клиентов по кассам:");
        for (int i = 0; i < N; i++) {
            System.out.println("Касса " + (i + 1) + ": " + (double) sumProcesses[i] / iterations);
        }
        System.out.println("Среднее количество потерянных клиентов: " + (double) sumProcesses[N] / iterations);
    }
}
