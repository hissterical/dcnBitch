import java.util.Scanner;

public class TDM {
    public static void main(String args[]) {
        int n, i, qt, count = 0, temp, sq = 0;
        int[] bt = new int[10];
        int[] wt = new int[10];
        int[] tat = new int[10];
        int[] rem_bt = new int[10];
        float awt = 0, atat = 0;

        Scanner s = new Scanner(System.in);

        System.out.print("Enter the number of stations (maximum 10): ");
        n = s.nextInt();

        System.out.println("Enter the processing time for each station:");
        for (i = 0; i < n; i++) {
            System.out.print("S" + i + " = ");
            bt[i] = s.nextInt();
            rem_bt[i] = bt[i];
        }

        System.out.print("Enter the frame size (time quantum): ");
        qt = s.nextInt();

        while (true) {
            count = 0;
            for (i = 0; i < n; i++) {
                if (rem_bt[i] == 0) {
                    count++;
                    continue;
                }

                temp = (rem_bt[i] > qt) ? qt : rem_bt[i];
                rem_bt[i] -= temp;
                sq += temp;
                if (rem_bt[i] == 0) {
                    tat[i] = sq;
                }
            }

            if (count == n) {
                break;
            }
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Station\tProcessing Time\tCompletion Time\tWaiting Time");
        System.out.println("------------------------------------------------------------");

        for (i = 0; i < n; i++) {
            wt[i] = tat[i] - bt[i];
            awt += wt[i];
            atat += tat[i];
            System.out.println("S" + i + "\t\t" + bt[i] + "\t\t" + tat[i] + "\t\t\t" + wt[i]);
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("Average Waiting Time = %.2f\n", awt / n);
        System.out.printf("Average Turnaround Time = %.2f\n", atat / n);

        s.close();
    }
}
