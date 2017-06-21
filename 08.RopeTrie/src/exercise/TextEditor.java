package exercise;

public interface TextEditor {

    public void login(String username);

    public void logout(String username);

    public void prepend(String username, String string);

    public void insert(String username, int index, String string);

    public void substring(String username, int startIndex, int length);

    public void delete(String username, int startIndex, int length);

    public void clear(String username);

    public int length(String username);

    public String print(String username);

    public void undo(String username);

    public Iterable<String> users(String prefix);
}
