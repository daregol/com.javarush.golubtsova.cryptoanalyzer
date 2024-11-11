import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Cipher {

    public static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    private char[] alphabet;
    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        String encrypted = "";
        for (int i = 0; i < text.length(); i++)
        {
            int index = 0;
            char symbol_str = text.charAt(i);
            if (symbol_str == '\n') {
                encrypted += '\n';
                continue;
            }
            for (char symbol_alf : alphabet) {
                if (symbol_str == symbol_alf) {
                    index = (index + shift) % alphabet.length;
                    encrypted += alphabet[index];
                    break;
                }
                index += 1;
                if(index == alphabet.length) {
                    break;
                }
            }
        }
        return encrypted;
    }

    public String encryptToPath(String path, int shift) throws IOException {
        FileManager fileManager = new FileManager();
        String fromFile = "";
        if (!Validator.isValidKey(shift, alphabet)){
            shift = shift % alphabet.length;
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
        String encrypted = encrypt(fromFile, shift);
        System.out.println("Зашифрованная строка: " + encrypted);
        String way = "C:\\Users\\Дарья\\IdeaProjects\\com.javarush.golubtsova.cryptoanalyzer\\src\\text_en.txt";
        fileManager.writeFile(encrypted, way);
        return fromFile;
    }

    public String decrypt(String encryptedText, int shift) {
        String decrypted = "";
        for (int i = 0; i < encryptedText.length(); i++)
        {
            int index = 0;
            char symbol_str = encryptedText.charAt(i);
            if (symbol_str == '\n') {
                decrypted += '\n';
                continue;
            }
            for (char symbol_alf : alphabet) {
                if (symbol_str == symbol_alf) {
                    index = (index + alphabet.length - shift) % alphabet.length;
                    decrypted += alphabet[index];
                    break;
                }
                index += 1;
                if(index == alphabet.length) {
                    break;
                }
            }
        }
        return decrypted;
    }

    public String decryptToPath(String path, int shift) throws IOException {
        FileManager fileManager = new FileManager();
        String fromFile = "";
        if (!Validator.isValidKey(shift, alphabet)){
            shift = shift % alphabet.length;
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

        String decrypted = decrypt(fromFile, shift);

        System.out.println("Расшифрованная строка: " + decrypted);
        String way = "C:\\Users\\Дарья\\IdeaProjects\\com.javarush.golubtsova.cryptoanalyzer\\src\\text_de.txt";
        fileManager.writeFile(decrypted, way);
        return fromFile;
    }
}
