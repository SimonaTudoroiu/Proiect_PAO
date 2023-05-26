package utile;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Audit {
    FileWriter writer;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logAction(String action) throws IOException {
        writer.append(action);
        writer.append(",");
        writer.append(formatter.format(LocalDateTime.now()));
        writer.append("\n");
        writer.flush();
    }


    public Audit() {
        try{
            this.writer = new FileWriter("src/audit.csv");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}