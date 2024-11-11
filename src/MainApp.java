import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws IOException {

        Cipher cipher = new Cipher(Cipher.ALPHABET);
        BruteForce bruteForce = new BruteForce();

        String way;
        int key;
        do {
            System.out.println("-------------------------");
            System.out.println("Меню");
            System.out.println("1. Шифрование с ключом");
            System.out.println("2. Расшифровка с ключом");
            System.out.println("3. Brute force");
            System.out.println("0. Выход");
            System.out.println("-------------------------");
            System.out.print("Выберите опцию меню: ");
            Scanner scanner = new Scanner(System.in);
            way = scanner.nextLine();

            String test_file;
            String encrypted_file = "src/text_en.txt";
            switch (way) {
                case "1":
                    System.out.print("Введите путь к файлу: ");
                    test_file = scanner.nextLine();
                    System.out.print("Введите ключ: ");
                    key = scanner.nextInt();
                    cipher.encryptToPath(test_file, key);
                    break;
                case "2":
                    System.out.print("Введите путь к файлу: ");
                    test_file = scanner.nextLine();
                    System.out.print("Введите ключ: ");
                    key = scanner.nextInt();
                    cipher.decryptToPath(encrypted_file, key);
                    break;
                case "3":
                    System.out.print("Введите путь к файлу: ");
                    test_file = scanner.nextLine();
                    bruteForce.decryptByBruteForce(encrypted_file, Cipher.ALPHABET);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Введена неверная опция");
                    break;
            }

        }
        while (!way.equals("0"));
    }
    }