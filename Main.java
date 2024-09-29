import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static BigDecimal binomialFinder(int x, int n, float p) {
        BigDecimal ans = BigDecimal.ZERO;

        BigInteger nFact = factorial(BigInteger.valueOf(n));
        BigInteger xFact = factorial(BigInteger.valueOf(x));
        BigInteger nMinusXFact = factorial(BigInteger.valueOf(n - x));

        BigDecimal coefficient = new BigDecimal(nFact)
                .divide(new BigDecimal(xFact.multiply(nMinusXFact)));

        // Perform probability calculations using BigDecimal
        BigDecimal pValue = BigDecimal.valueOf(p);
        BigDecimal oneMinusP = BigDecimal.ONE.subtract(pValue);

        // Calculate binomial probability term using BigDecimal
        ans = coefficient.multiply(pValue.pow(x))
                .multiply(oneMinusP.pow(n - x));

        return ans;
    }

    static BigInteger factorial(BigInteger num) {
        if (num.equals(BigInteger.ZERO) || num.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }
        return num.multiply(factorial(num.subtract(BigInteger.ONE)));
    }

    public static void main(String[] args) {
        Queue<String> temp_queue = new LinkedList<>();
        Queue<String> mem_queue = new LinkedList<>();
        String state_of_program = "a";
        var scanner = new Scanner(System.in);
        int x = 0;
        int n = 0;
        float p = 0;

        System.out.println("This is a binomial distribution calculator");

        System.out.println("Enter a positive integer to find binomial probability at X:");
        x = scanner.nextInt();
        System.out.println("Enter number of trials n:");
        n = scanner.nextInt();
        System.out.println("Enter probability of success (should be less than 1) p:");
        p = scanner.nextFloat();

        System.out.println("Probability: " + binomialFinder(x, n, p)); //THIS IS BINOMIAL PROBABILITY P(X = x) ONLY

        mem_queue.add("b(" + x + "," + n + "," + p + ")= " + binomialFinder(x, n, p));
        System.out.println("\n");

        while (!state_of_program.equals("Q")) {
            System.out.println("Last Binomial Distribution saved into queue.");
            System.out.println(mem_queue.size() + "/5 saved. Doing more than 5 distributions will cause first to be lost.\n");

            System.out.println("E: Enter new Binomial Distribution\n" +
                    "V: View Binomial Distribution History\n" +
                    "Q: Quit\n");
            state_of_program = scanner.next();

            switch (state_of_program) {
                case "E":
                    System.out.println("Enter a positive integer to find binomial probability at X:");
                    x = scanner.nextInt();
                    System.out.println("Enter number of trials n:");
                    n = scanner.nextInt();
                    System.out.println("Enter probability of success (should be less than 1) p:");
                    p = scanner.nextFloat();

                    System.out.println("Probability: " + binomialFinder(x, n, p)); //THIS IS BINOMIAL PROBABILITY P(X = x) ONLY

                    if (mem_queue.size() > 5) {
                        mem_queue.poll(); // Remove the oldest element if the queue exceeds 5
                    }
                    mem_queue.add("b(" + x + "," + n + "," + p + ")= " + binomialFinder(x, n, p));
                    break;

                case "V":
                    System.out.println(mem_queue);

                case "Q":
                    break;
            }
        }
    }
}
