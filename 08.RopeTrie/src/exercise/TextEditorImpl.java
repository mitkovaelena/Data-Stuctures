package exercise;

import lab.Trie;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TextEditorImpl implements TextEditor {
    private Trie<Deque<StringBuilder>> userTextCommands;
    private Map<String, Boolean> loggedUsers;

    public TextEditorImpl() {
        this.userTextCommands = new Trie<>();
        this.loggedUsers = new LinkedHashMap<>();
    }

    public void login(String username){
        this.loggedUsers.put(username,true);
        Deque<StringBuilder> newDeck = new ArrayDeque<>();
        newDeck.push(new StringBuilder(""));
        this.userTextCommands.insert(username, newDeck);
    }

    public void logout(String username){
        this.loggedUsers.replace(username, false);
    }

    public void prepend(String username, String string){
        this.insert(username,0,string);
    }

    public void insert(String username, int index, String string){
        if (this.loggedUsers.containsKey(username)){
            Deque<StringBuilder> temp = this.userTextCommands.getValue(username);
            StringBuilder value = new StringBuilder();
            if (!temp.isEmpty()){
                value = temp.peek();
            }
            value = value.insert(index, string);
            temp.push(value);
            this.userTextCommands.insert(username, temp);

        }
    }

    public void substring(String username, int startIndex, int length){
        if (this.loggedUsers.containsKey(username)){
            Deque<StringBuilder> temp = this.userTextCommands.getValue(username);
            StringBuilder value = new StringBuilder(temp.peek().substring(startIndex, startIndex+ length));
            temp.push(value);
            this.userTextCommands.insert(username, temp);
        }
    }

    public void delete(String username, int startIndex, int length){
        if (loggedUsers.containsKey(username)){
            Deque<StringBuilder> temp = this.userTextCommands.getValue(username);
            StringBuilder value = new StringBuilder(temp.peek());
            value = value.delete(startIndex, startIndex+ length);
            temp.push(value);
            userTextCommands.insert(username, temp);
        }
    }

    public void clear(String username){
        if (loggedUsers.containsKey(username)){
            Deque<StringBuilder> temp = this.userTextCommands.getValue(username);
            temp.push(new StringBuilder(""));
            userTextCommands.insert(username, temp);
        }
    }

    public int length(String username){
        if (loggedUsers.containsKey(username)){
            return this.userTextCommands.getValue(username).peek().length();
        }
        return 0;
    }

    public String print(String username){
        if (this.loggedUsers.containsKey(username)){
            return this.userTextCommands.getValue(username).peek().toString();
        }
        return "";
    }

    public void undo(String username){
        if (this.loggedUsers.containsKey(username)){
            this.userTextCommands.getValue(username).pop();
        }
    }

    public Iterable<String> users(String prefix){
        List<String> output = new LinkedList<>();

        if (prefix == null) {
            for(String user : loggedUsers.keySet()){
                if (loggedUsers.get(user)){
                    output.add(user);
                }
            }
            return output;
        }

        List<String> usersByPrefix = StreamSupport.stream(userTextCommands.getByPrefix(prefix).spliterator(), false).collect(Collectors.toList());
        for(String user : loggedUsers.keySet()){
            if (loggedUsers.get(user) && usersByPrefix.contains(user)){
                output.add(user);
            }
        }

        return output;
    }
}
