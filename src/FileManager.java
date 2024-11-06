import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public String readFile(String filePath) throws IOException {
            //Path path = Paths.get(filePath);
            //byte[] bytes = Files.readAllBytes(path);
            //return new String(bytes, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String enc = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
              enc += line + '\n';
            }

            return enc;

        }

    public void writeFile(String content, String filePath) throws IOException {
       /* byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        try {
            Files.writeString(Path.of(filePath), content);

        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }*/

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}