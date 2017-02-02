import java.io.File;
import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the file name with extention : ");
            File file = new File(input.nextLine());

            input = new Scanner(file);


            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}