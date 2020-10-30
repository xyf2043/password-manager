package persistence;

import model.PasswordEditor;
import model.User;
import model.UserList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// Citation/Reference: format of JsonReader in WorkShopApp, some methods used from JsonReader
public class PasswordReader {

    private String fileSource;

    // EFFECTS: constructs reader to read from fileSource file
    public PasswordReader(String fileSource) {
        this.fileSource = fileSource;
    }

    // EFFECTS: reads users from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PasswordEditor read() throws IOException {
        String jsonData = readFile(fileSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUserList(jsonObject);
    }

    // EFFECTS: reads fileSource file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses userList from JSON object (from Constructor) and returns it
    private PasswordEditor parseUserList(JSONObject jsonObject) {
        PasswordEditor userList = new PasswordEditor();
        addUsers(userList,jsonObject);
        return userList;
    }

    // MODIFIES: userList
    // EFFECTS: parses users from JSON object and adds them to userList
    private void addUsers(UserList userList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("userList");
        for (Object json : jsonArray) {
            JSONObject user = (JSONObject) json;
            addUser(userList,user);
        }
    }

    // MODIFIES: userList
    // EFFECTS: parses user from JSON object and adds it to userList
    private void addUser(UserList userList, JSONObject jsonObject) {
        String password = String.valueOf(jsonObject.getString("password"));
        String username = String.valueOf(jsonObject.getString("username"));
        User user = new User(username,password);
        userList.insertUser(user);
    }
}
