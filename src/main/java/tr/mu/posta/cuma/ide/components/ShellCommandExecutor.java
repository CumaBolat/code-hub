package tr.mu.posta.cuma.ide.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class ShellCommandExecutor {

    public String executeShellCommand(String command, String sessionId) {
        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("sh", "-c", command);
        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
                error.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to execute shell command", e);
        }

        if (emptyResponse(output, error)) return " ";

        return error.length() > 0 ? this.removeLastLine(error) : this.removeLastLine(output);
    }

    private String removeLastLine(StringBuilder str) {
        str.deleteCharAt(str.length() - 1);

        return str.toString();
    }

    private boolean emptyResponse(StringBuilder output, StringBuilder error) {
        return (output.length() == 0 && error.length() == 0);
    }
}
