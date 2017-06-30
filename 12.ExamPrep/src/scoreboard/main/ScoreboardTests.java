package scoreboard.main;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ScoreboardTests {

    private void executeTest(String inputFileName, String outputFileName) throws IOException {
        CommandExecutor commandExecutor = new CommandExecutor();

        Path inputPath = Paths.get("src\\scoreboard\\tests\\" + inputFileName);
        List<String> inputCommands = Files.readAllLines(inputPath);
        StringBuilder output = new StringBuilder();
        for (String command : inputCommands) {
            if (command.equals("End")) {
                break;
            }

            if (!command.equals("End")) {
                String commandOutput = commandExecutor.processCommand(command);
                output.append(commandOutput).append(System.lineSeparator());
            }
        }

        Path outputPath = Paths.get("src\\scoreboard\\tests\\" + outputFileName);
        String expectedOutput = String.join(System.lineSeparator(), Files.readAllLines(outputPath));
        String actualOutput = output.toString().trim();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test(timeout=200)
    public void Test000_SampleInput() throws IOException {
        executeTest("tests.000.001.in.txt", "tests.000.001.out.txt");
    }



    @Test(timeout=200)
    public void Test001_RegisterUser() throws IOException {
        executeTest("tests.001.in.txt", "tests.001.out.txt");
    }

    @Test(timeout=200)
    public void Test002_RegisterGame() throws IOException {
        executeTest("tests.002.in.txt", "tests.002.out.txt");
    }

    @Test(timeout=200)
    public void Test003_RegisterUser_RegisterGame_AddScore_Simple() throws IOException {
        executeTest("tests.003.in.txt", "tests.003.out.txt");
    }

    @Test(timeout=200)
    public void Test004_RegisterUser_RegisterGame_AddScore_Complex() throws IOException {
        executeTest("tests.004.in.txt", "tests.004.out.txt");
    }

    @Test(timeout=200)
    public void Test005_ShowScoreboard_Very_Simple() throws IOException {
        executeTest("tests.005.in.txt", "tests.005.out.txt");
    }

    @Test(timeout=200)
    public void Test006_ShowScoreboard_Simple() throws IOException {
        executeTest("tests.006.in.txt", "tests.006.out.txt");
    }

    @Test(timeout=200)
    public void Test007_ShowScoreboard_Empty() throws IOException {
        executeTest("tests.007.in.txt", "tests.007.out.txt");
    }

    @Test(timeout=200)
    public void Test008_ShowScoreboard_All_Cases() throws IOException {
        executeTest("tests.008.in.txt", "tests.008.out.txt");
    }

    @Test(timeout=200)
    public void Test009_ListGamesByPrefix() throws IOException {
        executeTest("tests.009.in.txt", "tests.009.out.txt");
    }

    @Test(timeout=200)
    public void Test010_RegisterGame_DeleteGame() throws IOException {
        executeTest("tests.010.in.txt", "tests.010.out.txt");
    }

    @Test(timeout=200)
    public void Test011_AddScore_DeleteGame() throws IOException {
        executeTest("tests.011.in.txt", "tests.011.out.txt");
    }

    @Test(timeout=200)
    public void Test012_DeleteGame_WithInvalidCases() throws IOException {
        executeTest("tests.012.in.txt", "tests.012.out.txt");
    }

    @Test(timeout=200)
    public void Test013_ShowScoreboard_DeleteGame_Complex() throws IOException {
        executeTest("tests.013.in.txt", "tests.013.out.txt");
    }

    @Test(timeout=200)
    public void Test014_ListGamesByPrefix_DeleteGame() throws IOException {
        executeTest("tests.014.in.txt", "tests.014.out.txt");
    }

    @Test(timeout=200)
    public void Test015_All_Commands_All_Cases() throws IOException {
        executeTest("tests.015.in.txt", "tests.015.out.txt");
    }

    @Test(timeout=200)
    public void Test016_Performance_RegisterUser() throws IOException {
        executeTest("tests.016.in.txt", "tests.016.out.txt");
    }

    @Test(timeout=200)
    public void Test017_Performance_RegisterGame() throws IOException {
        executeTest("tests.017.in.txt", "tests.017.out.txt");
    }

    @Test(timeout=200)
    public void Test018_Performance_AddScore() throws IOException {
        executeTest("tests.018.in.txt", "tests.018.out.txt");
    }

    @Test(timeout=200)
    public void Test019_Performance_ShowScoreboard() throws IOException {
        executeTest("tests.019.in.txt", "tests.019.out.txt");
    }

    @Test(timeout=200)
    public void Test020_Performance_ListGamesByPrefix() throws IOException {
        executeTest("tests.020.in.txt", "tests.020.out.txt");
    }

    @Test(timeout=200)
    public void Test021_Performance_DeleteGame() throws IOException {
        executeTest("tests.021.in.txt", "tests.021.out.txt");
    }

    @Test(timeout=300)
    public void Test022_Performance_AllCommands() throws IOException {
        executeTest("tests.022.in.txt", "tests.022.out.txt");
    }
}
