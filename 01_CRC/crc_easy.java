import java.util.*;

/*
 * Experiment 1: Error detection using CRC (Cyclic Redundancy Check)
 *
 * Sender  : appends (divisor length - 1) zeros to the data, divides by the
 *           divisor using XOR, and the remainder is the CRC.
 * Receiver: divides the received bits by the same divisor.
 *           Remainder all zeros -> no error, otherwise -> error.
 *
 * Bits are stored in Strings (like "1011001") to keep the logic simple.
 */
public class crc
{
    static Scanner s = new Scanner(System.in);

    // Reads n bits, one by one, and joins them into a String
    static String readBits(int n)
    {
        String bits = "";
        for (int i = 0; i < n; i++)
            bits = bits + s.nextInt();
        return bits;
    }

    // Prints a bit string with a space between the bits
    static void printSpaced(String bits)
    {
        for (int i = 0; i < bits.length(); i++)
            System.out.print(bits.charAt(i) + " ");
        System.out.println();
    }

    // Modulo-2 (XOR) long division. Returns the remainder as a String.
    static String divide(String bits, String divisor)
    {
        int d = divisor.length();

        // Append (divisor length - 1) zeros to the bits
        String padded = bits + "0".repeat(d - 1);
        System.out.println("message bits after appending divisor_length-1 0's:");
        printSpaced(padded);

        char r[] = padded.toCharArray();   // working copy

        // Slide the divisor along the bits, one position at a time
        for (int i = 0; i <= r.length - d; i++)
        {
            // Only divide when the leading bit is 1
            if (r[i] == '1')
            {
                // XOR the divisor into the current window
                for (int j = 0; j < d; j++)
                {
                    if (r[i + j] == divisor.charAt(j))
                        r[i + j] = '0';
                    else
                        r[i + j] = '1';
                }
            }
        }

        // The remainder is the last (d - 1) bits
        return new String(r).substring(r.length - (d - 1));
    }

    public static void main(String args[])
    {
        // ---- Read the data bits ----
        System.out.println("enter the size of the data:");
        int n = s.nextInt();
        System.out.println("enter the data ,bit by bit:");
        String data = readBits(n);

        // ---- Read the divisor bits ----
        System.out.println("enter ther size of the divisor:");
        n = s.nextInt();
        System.out.println("enter the divisor,bit by bit:");
        String divisor = readBits(n);

        // ---- Sender side: compute the CRC ----
        String crcBits = divide(data, divisor);

        // Codeword = original data followed by the CRC bits
        System.out.println("\n the crc code generated is:");
        System.out.println(data + crcBits);

        // ---- Receiver side: type the received codeword ----
        // (type it with errors to test error detection)
        System.out.println("enter the data to be sent:");
        String received = readBits(data.length() + crcBits.length());

        String remainder = divide(received, divisor);

        // Any '1' left in the remainder means an error
        if (remainder.contains("1"))
            System.out.println("there is an error in recieved data");
        else
            System.out.println("data was recieved without any error");
    }
}
