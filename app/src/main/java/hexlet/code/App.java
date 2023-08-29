package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    //private Path filepath1 = Paths.get("/tmp/file.txt").toAbsolutePath();
    String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    //private Path filepath2 = Paths.get("/tmp/file.txt").toAbsolutePath();
    String filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws IOException {
        Parser parser = new Parser(filepath1, filepath2);
        parser.parse();
        System.out.println(Differ.generate(parser.getData1(), parser.getData2(), format));
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
