
import org.knowm.xchart.*;

public class lab5 {
    public static void main(String[] args) {
        int N = 50000;
        double[] v = new double[N];
        double[] alpha = new double[N];
        int L = 1000;
        double mv = 110;
        double m_alpha = 30;
        double sigmaV = 5;
        double sigmaAlpha = 5;
        double delta = 30;
        for (int i = 0; i < N; i++) {
            v[i] = genRandN(mv, sigmaV);
        }
        for (int i = 0; i < N; i++) {
            alpha[i] = genRandN(m_alpha, sigmaAlpha);
        }
        int count = 0;
        double[] x = new double[N];
        for (int i = 0; i < N; i++) {
            x[i] = (Math.pow(v[i], 2) * Math.sin(Math.toRadians(2 * alpha[i]))) / 10;
            if (x[i] >= L - delta && x[i] <= L + delta) {
                count++;
            }
        }
        double p = (double) count/N;
        System.out.println("Вероятность попадания в мишень: " + p);
        double[] M = new double[N / 100];
        double[] D = new double[N / 100];
        double[] nValues = new double[N / 100];
        int i = 0;
        int n = 100;
        while (n <= N) {
            double m = calculateM(x, n);
            double d = calculateD(x, n, m);
            M[i] = m;
            D[i] = d;
            nValues[i] = n;
            i++;
            n += 100;
        }
        int L1 = 400;
        int L2 = 1600;
        int step = 50;
        int[] hits = new int[(L2 - L1) / step];
        double[] probability = new double[(L2 - L1) / step];
        double[] steps = new double[(L2 - L1) / step];
        for (i = 0; i < N; i++) {
            for (int j = 0; j < hits.length; j++) {
                if (x[i] >= L1 + j * step && x[i] < L1 + (j + 1) * step) {
                    hits[j]++;
                    break;
                }
            }
        }
        for (i = 0; i < probability.length; i++) {
            probability[i] = (double) hits[i] / N;
        }
        for (i = 0; i < hits.length; i++) {
            steps[i] = L1 + i * step;
        }
        graphicM(nValues, M);
        graphicD(nValues, D);
        histogram(steps, probability);
    }
    private static double genRandN(double m, double sigma) {
        double rn = 0;
        for (int i = 0; i < 12; i++) {
            rn += Math.random();
        }
        rn -= 6;
        return m + sigma * rn;
    }
    private static double calculateM(double[] x, int n) {
        double M = 0;
        for (int i = 0; i < n; i++) {
            M += x[i];
        }
        return M / n;
    }
    private static double calculateD(double[] x, int n, double m) {
        double D = 0;
        for (int i = 0; i < n; i++) {
            D += Math.pow(x[i] - m, 2);
        }
        return D / (n - 1);
    }
    private static void graphicM(double[] nValues, double[] M) {
        XYChart chart = QuickChart.getChart("", "N", "M", "M(N)", nValues, M);
        new SwingWrapper<>(chart).displayChart();
    }
    private static void graphicD(double[] nValues, double[] D) {
        XYChart chart = QuickChart.getChart("", "N", "D", "D(N)", nValues, D);
        new SwingWrapper<>(chart).displayChart();
    }
    private static void histogram(double[] steps, double[] probability) {
        CategoryChart chart = new CategoryChartBuilder().width(1200).height(600).xAxisTitle("Step").yAxisTitle("Probability").build();
        chart.addSeries(" ", steps, probability);
        new SwingWrapper<>(chart).displayChart();
    }
}
