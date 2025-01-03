public class Project2 {

    public static void main(String[] args) {
        // Declare variables
        int intValue = 10;  // Primitive int
        double doubleValue = 9.5;  // Primitive double

        // Implicit casting (int to double)
        double implicitCast = intValue;  // Implicit casting

        // Explicit casting (double to int)
        int explicitCast = (int) doubleValue;  // Explicit casting

        // Print the results
        System.out.println("Implicit casting (int to double): " + implicitCast);
        System.out.println("Explicit casting (double to int): " + explicitCast);
    }
}
