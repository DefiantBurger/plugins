import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class TeachingEllington {

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        Person joe = new Person("Joe Mama", 49);
        joe.getJob().employ("Teacher", 15);
        String input = "HI";

        while (!input.equals("stop")) {
            input = s.nextLine();

            if (input.equals("name")) {
                System.out.println(joe.getName());
            } else if (input.equals("age")) {
                System.out.println(joe.getAge());
            } else if (input.equals("job")) {
                System.out.println(joe.getJob());
            }

        }

    }

}
