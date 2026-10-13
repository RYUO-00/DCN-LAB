import java.util.*;

/*
 * Experiment 2: Congestion control using the Leaky Bucket algorithm
 *
 * - Every second a random number of packets arrives.
 * - The bucket can hold at most 'bucket size' packets; extra packets are dropped.
 * - Every second at most 'output rate' packets leak out of the bucket.
 */
public class leaky_bucket
{
    public static void main(String[] args)
    {
        int drop = 0, mini, i, o_rate, b_size, nsec, p_remain = 0;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the bucket size:\n");
        b_size = in.nextInt();
        System.out.print("Enter output rate:\n");
        o_rate = in.nextInt();
        System.out.print("Enter the number of seconds to simulate:\n");
        nsec = in.nextInt();

        // Random number of packets arriving in each second (0 to 999)
        int packet[] = new int[nsec];
        Random rand = new Random();
        for (i = 0; i < nsec; i++)
            packet[i] = rand.nextInt(1000);

        System.out.println("seconds\tpacket received\tpacket sent\tpackets left\tpackets dropped\n");

        // ---- Simulate each second ----
        for (i = 0; i < nsec; i++)
        {
            // New packets join whatever is already in the bucket
            p_remain += packet[i];

            // Bucket overflow -> extra packets are dropped
            if (p_remain > b_size)
            {
                drop = p_remain - b_size;
                p_remain = b_size;
            }

            // Leak: send min(packets in bucket, output rate)
            mini = Math.min(p_remain, o_rate);
            p_remain = p_remain - mini;

            System.out.println((i + 1) + "\t" + packet[i] + "\t\t" + mini + "\t\t" + p_remain + "\t\t" + drop);
            drop = 0;
        }

        // ---- No more arrivals: empty the bucket ----
        System.out.println("\nDraining the bucket (no new packets):");
        System.out.println("in bucket\tsent\tleft\tdropped");
        while (p_remain != 0)
        {
            mini = Math.min(p_remain, o_rate);
            System.out.print(p_remain + "\t\t" + mini);
            p_remain = p_remain - mini;
            System.out.println("\t" + p_remain + "\t" + drop);
        }
    }
}
