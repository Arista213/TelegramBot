import com.sun.jdi.connect.Connector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Класс для чтения ответов из папки Messages по названию комманды
 */
public class CommandReader {
    public static String readCommand(String command) {
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + "\\src\\main\\java\\Messages\\" + command + ".txt";

        try {

            BufferedReader br = new BufferedReader(new FileReader(
                    filePath));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                    if (line != null) {
                        sb.append(System.lineSeparator());
                    }
                }
                return sb.toString();
            } finally {
                br.close();
            }
        } catch (IOException e) {
            return e.toString();
        }
    }
}
