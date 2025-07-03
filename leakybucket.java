import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        int i;
        int[] packets = new int[20];
        int bucketContent = 0;
        int bucketCapacity = 4;
        int outputRate = 3;
        int sent, received;

        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of packets:");
        int n = in.nextInt();

        System.out.println("Enter the packet sizes:");
        for (i = 1; i <= n; i++) {
            packets[i] = in.nextInt();
        }

        System.out.println("Clock\tPacket Size\tAccepted\tSent\tRemaining");
        for (i = 1; i <= n; i++) {
            // Check if packet is not empty
            if (packets[i] != 0) {
                if (bucketContent + packets[i] > bucketCapacity) {
                    received = -1; // packet dropped
                } else {
                    received = packets[i];
                    bucketContent += packets[i];
                }
            } else {
                received = 0;
            }

            // Determine how much can be sent
            if (bucketContent != 0) {
                if (bucketContent < outputRate) {
                    sent = bucketContent;
                    bucketContent = 0;
                } else {
                    sent = outputRate;
                    bucketContent -= outputRate;
                }
            } else {
                sent = 0;
            }

            // Output the result for this clock tick
            if (received == -1) {
                System.out.println(i + "\t\t" + packets[i] + "\tDropped\t\t" + sent + "\t" + bucketContent);
            } else {
                System.out.println(i + "\t\t" + packets[i] + "\t\t" + received + "\t\t" + sent + "\t" + bucketContent);
            }
        }

        in.close();
    }
}
