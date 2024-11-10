import java.io.IOException;
import java.util.Scanner;
import java.nio.*;

public class MainApp {
    public static void main(String[] args) throws IOException {
        String test_file = "C:\\Users\\Дарья\\IdeaProjects\\com.javarush.golubtsova.cryptoanalyzer\\src\\text.txt";
        String encrypted_file = "C:\\Users\\Дарья\\IdeaProjects\\com.javarush.golubtsova.cryptoanalyzer\\src\\text_en.txt";
        String decrypted = "C:\\Users\\Дарья\\IdeaProjects\\com.javarush.golubtsova.cryptoanalyzer\\src\\text_de.txt";

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


            switch (way) {
                case "1":
                    System.out.print("Введите путь к файлу: ");
                    test_file = scanner.nextLine();
                    System.out.print("Введите ключ: ");
                    key = scanner.nextInt();
                    cipher.encrypt(test_file, key);
                    break;
                case "2":
                    System.out.print("Введите путь к файлу: ");
                    test_file = scanner.nextLine();
                    System.out.print("Введите ключ: ");
                    key = scanner.nextInt();
                    cipher.decrypt(encrypted_file, key);
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