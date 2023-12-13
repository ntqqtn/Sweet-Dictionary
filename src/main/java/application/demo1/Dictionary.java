package application.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dictionary extends Trie {
    TrieNode root = new TrieNode();
    private ObservableList<String> wordsList = FXCollections.observableArrayList();
    private ArrayList<Word> wordObjectList = new ArrayList<>();

    public void createDictionary(){
        String jdbcUrl = "jdbc:sqlite:dict_hh.db";

        try {
            // ket noi voi sqlite va dict
            Connection connection = DriverManager.getConnection(jdbcUrl);
            // cau lenh muon lay du kieu gi???
            String sql = "SELECT word, pronounce, description FROM av";
            // gui query den sqlite
            Statement statement = connection.createStatement();
            // thuc thi cau lenh va tra ve resultSet
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                String word = result.getString("word");
                String description =  result.getString("pronounce") + "\n" + result.getString("description");
                Word tmp = new Word(word, description);
                wordObjectList.add(tmp);
                insert(word, description);
                wordsList.add(word);
            }
            Collections.sort(wordsList);
        } catch (SQLException var8) {
            System.out.println("Error connecting to SQLite database");
            var8.printStackTrace();
        }
    }
    public ObservableList<String> getWordsList() {
        return wordsList;
    }
    public ArrayList<Word> getWordObjectList() {
        return wordObjectList;
    }

}
