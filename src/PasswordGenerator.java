import java.util.Random;
public class PasswordGenerator {

    private int numGenerated;
    private String prefix;
    private int numNumbers;

    public PasswordGenerator(int numNumbers, String prefix) {
        this.numNumbers = numNumbers;
        this.prefix = prefix;
        this.numGenerated = 0;
    }

    public PasswordGenerator(int numNumbers) {
        this.numNumbers = numNumbers;
        this.prefix = "A";
        this.numGenerated = 0;
    }

    public int pwCount() {
        return numGenerated;
    }

    public String pwGen() {
        Random r = new Random();
        StringBuilder secondHalf = new StringBuilder();
        for (int i = 0; i < numNumbers; i++) {
            secondHalf.append(r.nextInt(10));
        }
        String password = this.prefix + "." + secondHalf.toString();
        numGenerated++;
        return password;
    }


}
