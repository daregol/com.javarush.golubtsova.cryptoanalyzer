import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class BruteForce {
    public String decryptByBruteForce(String path, char[] alphabet) throws IOException {

        System.out.println(" ");
        FileManager fileManager = new FileManager();
        String fromFile = "";

        try {
            fromFile = fileManager.readFile(path);
        }
        catch (NoSuchFileException e) {
            System.out.println("Файл не найден");
        }
        catch (IOException e) {}

        if (fromFile.isEmpty()){
            System.out.println("Файл пустой");
            return fromFile;
        }
        System.out.println("\nРасшифровка...");
        System.out.println("Зашифрованная строка: " + fromFile);
        String decrypted_res = "";
        String decrypted = "";
        for (int shift = 1; shift < Cipher.ALPHABET.length; shift++) {
            decrypted = "";
            for (int i = 0; i < fromFile.length(); i++)
            {
                int index = 0;
                char symbol_str = fromFile.charAt(i);
                for (char symbol_alf : Cipher.ALPHABET) {
                    if (symbol_str == symbol_alf) {
                        index = (index + Cipher.ALPHABET.length - shift) % Cipher.ALPHABET.length;
                        decrypted += Cipher.ALPHABET[index];
                        break;
                    }
                    index += 1;
                    if(index == Cipher.ALPHABET.length) {
                        break;
                    }
                }
            }
            System.out.println("Расшифрованная строка: " + decrypted + "   ключ: " + shift);
            decrypted_res += "\n" + decrypted;
        }
        String way = "C:\\Users\\Дарья\\IdeaProjects\\com.javarush.golubtsova.cryptoanalyzer\\src\\text_de.txt";
        fileManager.writeFile(decrypted_res, way);
        return fromFile;
    }
}