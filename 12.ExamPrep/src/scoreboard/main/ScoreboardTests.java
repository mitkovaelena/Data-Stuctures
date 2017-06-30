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

        Path inputPath = Paths.get("src\\tests\\" + inputFileName);
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

        Path outputPath = Paths.get("src\\tests\\" + outputFileName);
        String expectedOutput = String.join(System.lineSeparator(), Files.readAllLines(outputPath));
        String actualOutput = output.toString().trim();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test(timeout=200)
    public void Test000_SampleInput() throws IOException {
        executeTest("bunnyWars.test.000.001.in.txt", "bunnyWars.test.000.001.out.txt");
    }



    @Test(timeout=200)
    public void Test001_RegisterUser() throws IOException {
        executeTest("bunnyWars.test.001.in.txt", "bunnyWars.test.001.out.txt");
    }

    @Test(timeout=200)
    public void Test002_RegisterGame() throws IOException {
        executeTest("bunnyWars.test.002.in.txt", "bunnyWars.test.002.out.txt");
    }

    @Test(timeout=200)
    public void Test003_RegisterUser_RegisterGame_AddScore_Simple() throws IOException {
        executeTest("bunnyWars.test.003.in.txt", "bunnyWars.test.003.out.txt");
    }

    @Test(timeout=200)
    public void Test004_RegisterUser_RegisterGame_AddScore_Complex() throws IOException {
        executeTest("bunnyWars.test.004.in.txt", "bunnyWars.test.004.out.txt");
    }

    @Test(timeout=200)
    public void Test005_ShowScoreboard_Very_Simple() throws IOException {
        executeTest("bunnyWars.test.005.in.txt", "bunnyWars.test.005.out.txt");
    }

    @Test(timeout=200)
    public void Test006_ShowScoreboard_Simple() throws IOException {
        executeTest("bunnyWars.test.006.in.txt", "bunnyWars.test.006.out.txt");
    }

    @Test(timeout=200)
    public void Test007_ShowScoreboard_Empty() throws IOException {
        executeTest("bunnyWars.test.007.in.txt", "bunnyWars.test.007.out.txt");
    }

    @Test(timeout=200)
    public void Test008_ShowScoreboard_All_Cases() throws IOException {
        executeTest("bunnyWars.test.008.in.txt", "bunnyWars.test.008.out.txt");
    }

    @Test(timeout=200)
    public void Test009_ListGamesByPrefix() throws IOException {
        executeTest("bunnyWars.test.009.in.txt", "bunnyWars.test.009.out.txt");
    }

    @Test(timeout=200)
    public void Test010_RegisterGame_DeleteGame() throws IOException {
        executeTest("bunnyWars.test.010.in.txt", "bunnyWars.test.010.out.txt");
    }

    @Test(timeout=200)
    public void Test011_AddScore_DeleteGame() throws IOException {
        executeTest("bunnyWars.test.011.in.txt", "bunnyWars.test.011.out.txt");
    }

    @Test(timeout=200)
    public void Test012_DeleteGame_WithInvalidCases() throws IOException {
        executeTest("bunnyWars.test.012.in.txt", "bunnyWars.test.012.out.txt");
    }

    @Test(timeout=200)
    public void Test013_ShowScoreboard_DeleteGame_Complex() throws IOException {
        executeTest("bunnyWars.test.013.in.txt", "bunnyWars.test.013.out.txt");
    }

    @Test(timeout=200)
    public void Test014_ListGamesByPrefix_DeleteGame() throws IOException {
        executeTest("bunnyWars.test.014.in.txt", "bunnyWars.test.014.out.txt");
    }

    @Test(timeout=200)
    public void Test015_All_Commands_All_Cases() throws IOException {
        executeTest("bunnyWars.test.015.in.txt", "bunnyWars.test.015.out.txt");
    }

    @Test(timeout=200)
    public void Test016_Performance_RegisterUser() throws IOException {
        executeTest("bunnyWars.test.016.in.txt", "bunnyWars.test.016.out.txt");
    }

    @Test(timeout=200)
    public void Test017_Performance_RegisterGame() throws IOException {
        executeTest("bunnyWars.test.017.in.txt", "bunnyWars.test.017.out.txt");
    }

    @Test(timeout=200)
    public void Test018_Performance_AddScore() throws IOException {
        executeTest("bunnyWars.test.018.in.txt", "bunnyWars.test.018.out.txt");
    }

    @Test(timeout=200)
    public void Test019_Performance_ShowScoreboard() throws IOException {
        executeTest("bunnyWars.test.019.in.txt", "bunnyWars.test.019.out.txt");
    }

    @Test(timeout=200)
    public void Test020_Performance_ListGamesByPrefix() throws IOException {
        executeTest("bunnyWars.test.020.in.txt", "bunnyWars.test.020.out.txt");
    }

    @Test(timeout=200)
    public void Test021_Performance_DeleteGame() throws IOException {
        executeTest("bunnyWars.test.021.in.txt", "bunnyWars.test.021.out.txt");
    }

    @Test(timeout=200)
    public void Test022_Performance_AllCommands() throws IOException {
        executeTest("bunnyWars.test.022.in.txt", "bunnyWars.test.022.out.txt");
    }
}
