package exercises;

import lab.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class phonebook {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = "";
        HashTable<String,String> guests = new HashTable<>();

        while(true){
            inputLine = reader.readLine();
            if(inputLine.equals("search")) break;
            String[] line = inputLine.split("-");
            guests.addOrReplace(line[0],line[1]);
        }

        while(true){
            inputLine = reader.readLine();
            if(inputLine.equals("stop")) break;
            if(guests.containsKey(inputLine)){
                System.out.println(inputLine + " -> " + guests.get(inputLine));
            }
            else
                System.out.println("Contact "+ inputLine + " does not exist.");
        }
    }
}
