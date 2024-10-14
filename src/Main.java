import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String caesarCipher(String text, int key, boolean encrypt)
    {
        key = encrypt ? key : ALPHABET.length() - key;
        StringBuilder result = new StringBuilder();

        for (char ch : text.toUpperCase().toCharArray()) {
            int idx = ALPHABET.indexOf(ch);
            if (idx != -1) {
                int shiftedIdx = (idx + key) % ALPHABET.length();
                result.append(ALPHABET.charAt(shiftedIdx));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    public static void writeFile(String filePath, String content) throws IOException {
        Files.writeString(Path.of(filePath), content);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите путь к входному файлу: ");
            String inputFile = scanner.nextLine();
            System.out.print("Введите путь к выходному файлу: ");
            String outputFile = scanner.nextLine();

            System.out.print("Введите ключ (0-25): ");
            int key = scanner.nextInt();
            System.out.print("Выберите режим (1 - Шифрование, 2 - Расшифровка): ");
            int mode = scanner.nextInt();


            String content = readFile(inputFile);
            boolean encrypt = (mode == 1);
            String result = caesarCipher(content, key, encrypt);

            writeFile(outputFile, result);
            System.out.println("Операция завершена успешно.");
        } catch (IOException e) {
            System.out.println("Ошибка работы с файлами: " + e.getMessage());
        }
    }
}