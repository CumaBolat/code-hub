package tr.mu.posta.cuma.ide.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShellCommandExecutor {

  public String executeShellCommand(String command) {
    StringBuilder output = new StringBuilder();
    StringBuilder error = new StringBuilder();

    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.command("bash", "-c", command);
    try {
      Process process = processBuilder.start();

      // if (command.startsWith("sudo"))
      //   this.enterPassword(process);

      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
      System.out.println(output.toString());
      while ((line = errorReader.readLine()) != null) {
        System.err.println(line);
        error.append(line + "\n");
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to execute shell command", e);
    }

    if (emptyResponse(output, error)) return " ";

    return error.length() > 0 ? this.removeLastLine(error) : this.removeLastLine(output);
  }

  private String removeLastLine(StringBuilder str) {
    System.out.println("Output string: " + str.toString());
    str.deleteCharAt(str.length() - 1);
    return str.toString();
  }

  private boolean emptyResponse(StringBuilder output, StringBuilder error) {
    return (output.length() == 0 && error.length() == 0);
  }

  // private void enterPassword(Process process) {
  //   try {
  //     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
  //     writer.write(this.password + "\n");
  //     writer.flush();
  //   } catch (IOException e) {
  //     throw new RuntimeException("Failed to enter password", e);
  //   }
  // }
}
