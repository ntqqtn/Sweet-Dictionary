package application.demo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {
    private Dictionary diction = AppContent.getDictionary();
//    private;

    /** get 4 phan tu random.*/
    public List<Word> getFourElementFirstList(ArrayList<Word> list) {
        Collections.shuffle(list);
        List<Word> randomFourElements = list.subList(0, 4);
        return randomFourElements;
    }

    public Word getKeyWord(List<Word> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        // Sử dụng lớp Random để sinh số ngẫu nhiên
        Random random = new Random();

        // Chọn một đối tượng Word ngẫu nhiên từ danh sách
        int randomIndex = random.nextInt(list.size());
        Word word = list.get(randomIndex);
        return word;
    }


}
