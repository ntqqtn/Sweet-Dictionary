package application.demo1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import java.util.*;

public class Trie extends TrieNode {
    TrieNode root = new TrieNode();
    public boolean ifPredictNull;

    TrieNode getNewNode() {
        TrieNode newNode = new TrieNode();
        newNode.isEndOfWord = false;
        return newNode;
    }

    /*
     * hàm insert() dùng để 1 từ vào trong Trie
     * hàm insert() sẽ được dùng trong hàm createDictionary() của class Dictionary
     */
    public void insert(String key, String description) {
        String word = key.toLowerCase();
        if (root == null) {
            getNewNode();
        }

        TrieNode curNode = root;

        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (!curNode.children.containsKey(curChar)) {
                curNode.children.put(curChar, getNewNode());
            }
            curNode = curNode.children.get(curChar);
        }

        if (!curNode.isEndOfWord) {
            curNode.isEndOfWord = true;
            curNode.setWordObject(key, description);
        }
    }

    /*
     * hàm getPredictWord dùng BFS để lấy ra những từ gợi ý(từ có chứa từ dùng định nhập vaò)
     * @parameter: bắt buộc phải có TrieNode root (nó sẽ nhận điểm bắt đầu từ node cuối cùng của từ nhập vào
     * VD: abc thì root sẽ nhận đầu vào là TrieNode c
     * Sau đó duyệt và lấy ra những từ chứa key đó
     */
    public ArrayList<String> getPredictWords(TrieNode root, int limit) {
        ArrayList<String> predict = new ArrayList<>();
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty() && predict.size() < limit) {
            TrieNode node = queue.poll();

            if (node.isEndOfWord) {
                predict.add(node.wordObject.getWord_target());
            }

            for (TrieNode child : node.children.values()) {
                queue.add(child);
            }
        }

        return predict;
    }

    /*
     * Hàm predictList nhận đầu vào là từ người dùng đang nhập
     * Sau đó bắt đầu lặp để duyệt từng ký tự trong từ đó
     * Nếu ký tự đó chưa có trong từng level of Trie thi trả về NULL luôn
     * Không thì sẽ lấy về TrieNode cuối cùng dùng cho hàm getPredictWords, để bắt đầu tìm từ gợi ý
     */
    /* đã viet lai ham */
    public ObservableList<String> predictList(String key) {
        String word = key.toLowerCase();

        TrieNode curNode = root;
        TrieNode lastNonNullNode = root;
        ifPredictNull = false;

        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            curNode = curNode.children.get(curChar);
            if (curNode == null) {
                curNode = lastNonNullNode;
                ifPredictNull = true;
                break;
            }
            lastNonNullNode = curNode;
        }

        ObservableList<String> predict = FXCollections.observableArrayList(getPredictWords(curNode, 100));

        return predict;
    }

    /*
     * Hàm tìm kiếm từ người dùng muốn tra trong Trie
     * */
    public String search(String key) {
        String word = key.toLowerCase();
        TrieNode curNode = root;
        String definition = "";

        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            curNode = curNode.children.get(curChar);
            if (curNode == null) return "";
        }
        System.out.println(curNode.wordObject.getWord_target());
        System.out.println(curNode.wordObject.getWord_explain());
        definition = curNode.wordObject.getWord_explain();
        return definition;
    }


}