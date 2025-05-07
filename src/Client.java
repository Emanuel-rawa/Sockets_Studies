import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket("localhost", 6789);

    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    Scanner scanner = new Scanner(System.in);

    // Mensagem inicial do servidor
    System.out.println(in.readLine());

    while (true) {
      System.out.print("Enter command: ");
      String request = scanner.nextLine();
      out.println(request);

      if (request.equals("HELP")) {
        String responseLine;
        while ((responseLine = in.readLine()) != null) {
          if (responseLine.equals("END"))
            break;
          System.out.println("Server: " + responseLine);
        }
      } else {
        String response = in.readLine();
        System.out.println("Server: " + response);
      }

      if (request.equals("LOGOUT"))
        break;
    }

    socket.close();
    scanner.close();
  }
}
