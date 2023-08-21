package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    //private Path filepath1 = Paths.get("/tmp/file.txt").toAbsolutePath();
    String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    //private Path filepath2 = Paths.get("/tmp/file.txt").toAbsolutePath();
    String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        Map<String, Object> data1 = objectMapper.readValue(json1, new TypeReference<>() {
        });
        Map<String, Object> data2 = objectMapper.readValue(json2, new TypeReference<>() {
        });
        System.out.println(Differ.generate(data1, data2));
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
