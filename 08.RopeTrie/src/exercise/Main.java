package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TextEditor textEditor = new TextEditorImpl();
        String commandLine = reader.readLine();

        while (commandLine != "end") {
            try {
                String[] params = commandLine.split(" ");

                switch (params[0]) {
                    case "login":
                        textEditor.login(params[1]);
                        break;
                    case "logout":
                        textEditor.logout(params[1]);
                        break;
                    case "users":
                        Iterable<String> usersToPrint;
                        if (params.length == 1) {
                            usersToPrint = textEditor.users(null);
                        } else {
                            usersToPrint = textEditor.users(params[1]);
                        }
                        Iterator iterator = usersToPrint.iterator();
                        while (iterator.hasNext()){
                            System.out.println(iterator.next());
                        }
                        break;
                }

                switch (params[1]) {
                    case "insert":
                        String string = commandLine.split("\"")[1];
                        textEditor.insert(params[0], Integer.parseInt(params[2]), string);
                        break;
                    case "prepend":
                        String str = commandLine.split("\"")[1];
                        textEditor.prepend(params[0], str);
                        break;
                    case "substring":
                        textEditor.substring(params[0], Integer.parseInt(params[2]), Integer.parseInt(params[3]));
                        break;
                    case "delete":
                        textEditor.delete(params[0], Integer.parseInt(params[2]), Integer.parseInt(params[3]));
                        break;
                    case "clear":
                        textEditor.clear(params[0]);
                        break;
                    case "print":
                        System.out.println(textEditor.print(params[0]));
                        break;
                    case "length":
                        System.out.println(textEditor.length(params[0]));
                        break;
                    case "undo":
                        textEditor.undo(params[0]);
                        break;
                }
                commandLine = reader.readLine();
            } catch (Exception e){
                commandLine = reader.readLine();
            }
        }
    }
}
