import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.nio.file.NoSuchFileException;

public class Cipher {
    public static char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    public Cipher(char[] alphabet) {
        this.ALPHABET = alphabet;
    }
    public String encrypt(String path, int shift) throws IOException {
        FileManager fileManager = new FileManager();
        String fromFile = "";
        if (!Validator.isValidKey(shift, ALPHABET)){
            shift = shift % ALPHABET.length;
        }

        try {
            fromFile = fileManager.readFile(path);
        }
        catch (NoSuchFileException e) {
            System.out.println("Файл не найден");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (fromFile.isEmpty()){
            System.out.println("Файл пустой");
            return fromFile;
        }
        System.out.println("\nЗашифровка...");
        System.out.println("Изначальная строка: " + fromFile);
        String encrypted = "";
        for (int i = 0; i < fromFile.length(); i++)
        {
            int index = 0;
            char symbol_str = fromFile.charAt(i);
            if (symbol_str == '\n') {
                encrypted += '\n';
                continue;
            }
            for (char symbol_alf : ALPHABET) {
                if (symbol_str == symbol_alf) {
                    index = (index + shift) % 40;
                    encrypted += ALPHABET[index];
                    break;
                }
                index += 1;
                if(index == ALPHABET.length) {
                    break;
                }
            }
        }
        System.out.println("Зашифрованная строка: " + encrypted);
        String way = "C:\\Users\\Дарья\\IdeaProjects\\com.javarush.golubtsova.cryptoanalyzer\\src\\text_en.txt";
        fileManager.writeFile(encrypted, way);
        return fromFile;
    }
    public String decrypt(String path, int shift) throws IOException {
        FileManager fileManager = new FileManager();
        String fromFile = "";
        if (!Validator.isValidKey(shift, ALPHABET)){
            shift = shift % ALPHABET.length;
        }

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

        String decrypted = "";
        for (int i = 0; i < fromFile.length(); i++)
        {
            int index = 0;
            char symbol_str = fromFile.charAt(i);
            if (symbol_str == '\n') {
                decrypted += '\n';
                continue;
            }
            for (char symbol_alf : ALPHABET) {
                if (symbol_str == symbol_alf) {
                    index = (index + ALPHABET.length - shift) % ALPHABET.length;
                    decrypted += ALPHABET[index];
                    break;
                }
                index += 1;
                if(index == ALPHABET.length) {
                    break;
                }
            }
        }
        System.out.println("Расшифрованная строка: " + decrypted);
        String way = "C:\\Users\\Дарья\\IdeaProjects\\com.javarush.golubtsova.cryptoanalyzer\\src\\text_de.txt";
        fileManager.writeFile(decrypted, way);
        return fromFile;
    }
}
