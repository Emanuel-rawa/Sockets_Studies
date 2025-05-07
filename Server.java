import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

  static Map<String, String> users = new HashMap<>();
  static Map<String, String> data = new HashMap<>();

  public static void main(String[] args) throws IOException {
    users.put("admin", "1234");
    data.put("jose", "José Ovi, idade 50, email paidamentira@gmail.com");
    data.put("azatoh", "Azatoh WordEater, idade ???, email gatinhofofo321@gmail.com");

    ServerSocket server = new ServerSocket(6789);
    System.out.println("Server ready at the port: 6789");

    while (true) {
      Socket client = server.accept();
      new Thread(new ClientHandler(client)).start();
    }
  }

  static class ClientHandler implements Runnable {
    private Socket socket;
    private boolean authenticated = false;

    ClientHandler(Socket socket) {
      this.socket = socket;
    }

    public void run() {
      try (
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
        out.println("Welcome, please enter you CYPHER to access:<user>:<password>");
        String line;

        while ((line = in.readLine()) != null) {
          if (line.startsWith("LOGIN:")) {
            String[] parts = line.split(":");
            if (parts.length == 3 && users.containsKey(parts[1]) && users.get(parts[1]).equals(parts[2])) {
              authenticated = true;
              out.println("OK: successfully logged in");
            } else {
              out.println("ERROR: Invalid login");
            }
          } else if (line.startsWith("GET_INFO:")) {
            if (!authenticated) {
              out.println("ERROR: unauthenticated");
              continue;
            }
            String user = line.substring(9);
            String info = data.getOrDefault(user, "Can't find user");
            out.println("DATA:" + info);
          } else if (line.equals("LOGOUT")) {
            authenticated = false;
            out.println("OK:Logout performed");
          } else if (line.equals("LIST_KEYS")) {
            if (!authenticated) {
              out.println("ERROR:unauthenticated");
              continue;
            }
            Set<String> keys = data.keySet();
            out.println("LIST:" + String.join(", ", keys));
          } else if (line.equals("HELP")) {
            out.println("List of commands: ");
            out.println("- LOGIN:<usuario>:<senha> -> auth");
            out.println("- GET_INFO:<usuario> → Show data");
            out.println("- LIST_KEYS → List keys for data");
            out.println("- LOGOUT → End session");
            out.println("- HELP → Show this commands");
          } else {
            out.println("ERROR: Invalid command");
          }
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
