package exercise;

import lab.Trie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TextEditorImpl implements TextEditor {
    private Trie<Deque<StringBuilder>> userTextCommands;
    private List<String> loggedUsers;

    public TextEditorImpl() {
        this.userTextCommands = new Trie<>();
        this.loggedUsers = new ArrayList<>();
    }

    public void login(String username){
        this.loggedUsers.add(username);
        Deque<StringBuilder> newDeck = new ArrayDeque();
        newDeck.push(new StringBuilder(""));
        this.userTextCommands.insert(username, newDeck);
    }

    public void logout(String username){
        this.loggedUsers.remove(username);
    }

    public void prepend(String username, String string){
        this.insert(username,0,string);
    }

    public void insert(String username, int index, String string){
        if (this.loggedUsers.contains(username)){
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
        if (this.loggedUsers.contains(username)){
            Deque<StringBuilder> temp = this.userTextCommands.getValue(username);
            StringBuilder value = new StringBuilder(temp.peek().substring(startIndex, startIndex+ length));
            temp.push(value);
            this.userTextCommands.insert(username, temp);
        }
    }

    public void delete(String username, int startIndex, int length){
        if (loggedUsers.contains(username)){
            Deque<StringBuilder> temp = this.userTextCommands.getValue(username);
            StringBuilder value = new StringBuilder(temp.peek());
            value = value.delete(startIndex, startIndex+ length);
            temp.push(value);
            userTextCommands.insert(username, temp);
        }
    }

    public void clear(String username){
        if (loggedUsers.contains(username)){
            Deque<StringBuilder> temp = this.userTextCommands.getValue(username);
            temp.push(new StringBuilder(""));
            userTextCommands.insert(username, temp);
        }
    }

    public int length(String username){
        if (loggedUsers.contains(username)){
            return this.userTextCommands.getValue(username).peek().length();
        }
        return 0;
    }

    public String print(String username){
        if (this.loggedUsers.contains(username)){
            return this.userTextCommands.getValue(username).peek().toString();
        }
        return "";
    }

    public void undo(String username){
        if (this.loggedUsers.contains(username)){
            this.userTextCommands.getValue(username).pop();
        }
    }

    public Iterable<String> users(String prefix){
        if (prefix != null){
            return userTextCommands.getByPrefix(prefix);
        }
        return loggedUsers;
    }
}
