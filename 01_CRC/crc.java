import java.util.*;

/*
 * Experiment 1: Error detection using CRC (Cyclic Redundancy Check)
 *
 * Sender  : appends (divisor length - 1) zeros to the data, divides by the
 *           divisor using XOR, and the remainder is the CRC.
 * Receiver: divides the received bits by the same divisor.
 *           Remainder all zeros -> no error, otherwise -> error.
 *
 * Note: for CRC-CCITT (16-bit), the divisor is the 17-bit polynomial
 * 1 0001 0000 0010 0001 (0x11021). Any divisor can be typed in here.
 */
public class crc
{
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        int n;

        // ---- Read the data bits ----
        System.out.println("enter the size of the data:");
        n = s.nextInt();
        int data[] = new int[n];
        System.out.println("enter the data ,bit by bit:");
        for (int i = 0; i < n; i++)
            data[i] = s.nextInt();

        // ---- Read the divisor bits ----
        System.out.println("enter ther size of the divisor:");
        n = s.nextInt();
        int divisor[] = new int[n];
        System.out.println("enter the divisor,bit by bit:");
        for (int i = 0; i < n; i++)
            divisor[i] = s.nextInt();

        // ---- Sender side: compute the CRC ----
        int remainder[] = divide(data, divisor);

        // Codeword = original data followed by the CRC bits
        System.out.println("\n the crc code generated is:");
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i]);
        for (int i = 0; i < remainder.length - 1; i++)
            System.out.print(remainder[i]);
        System.out.println();

        // ---- Receiver side: type the received codeword ----
        // (type it with errors to test error detection)
        int sent_data[] = new int[data.length + remainder.length - 1];
        System.out.println("enter the data to be sent:");
        for (int i = 0; i < sent_data.length; i++)
            sent_data[i] = s.nextInt();

        receive(sent_data, divisor);
    }

    // Binary (modulo-2) long division. Returns the remainder.
    static int[] divide(int old_data[], int divisor[])
    {
        int remainder[], i;

        // Copy data into a bigger array; the extra slots stay 0
        // (these are the appended zeros)
        int data[] = new int[old_data.length + divisor.length];
        System.arraycopy(old_data, 0, data, 0, old_data.length);

        System.out.println("message bits after appending divisor_length-1 0's:");
        for (i = 0; i < data.length - 1; i++)
            System.out.print(data[i] + " ");
        System.out.println();

        // Start with the first 'divisor length' bits
        remainder = new int[divisor.length];
        System.arraycopy(data, 0, remainder, 0, divisor.length);

        for (i = 0; i < old_data.length; i++)
        {
            if (remainder[0] == 1)
            {
                // Leading bit is 1 -> XOR with the divisor and shift left
                for (int j = 1; j < divisor.length; j++)
                    remainder[j - 1] = exor(remainder[j], divisor[j]);
            }
            else
            {
                // Leading bit is 0 -> XOR with zeros (just shift left)
                for (int j = 1; j < divisor.length; j++)
                    remainder[j - 1] = exor(remainder[j], 0);
            }
            // Bring down the next bit of the data
            remainder[divisor.length - 1] = data[i + divisor.length];
        }
        return remainder;
    }

    // XOR of two bits
    static int exor(int a, int b)
    {
        if (a == b)
            return 0;
        return 1;
    }

    // Receiver: divide again, remainder must be all zeros
    static void receive(int data[], int divisor[])
    {
        int remainder[] = divide(data, divisor);
        for (int i = 0; i < remainder.length; i++)
        {
            if (remainder[i] != 0)
            {
                System.out.println("there is an error in recieved data");
                return;
            }
        }
        System.out.println("data was recieved without any error");
    }
}
