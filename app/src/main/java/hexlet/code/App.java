package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
//    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
//    boolean help;
//    @Option(names = {"-V", "--version"}, usageHelp = true, description = "Print version information and exit.")
//    boolean version;
    @Override
    public Integer call() throws Exception { // your business logic goes here...
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
