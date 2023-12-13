package application.demo1;


public class AppContent {
    private static MyNote myHistoryContent;
    private static MyNote myNoteContent;
    private static Dictionary dictionaryContent;

    private AppContent() {
        //
    }

    public static MyNote getHistory() {
        if (myHistoryContent == null) {
            myHistoryContent = new MyNote();
        }
        return myHistoryContent;
    }

    public static void setHistory(MyNote note) {

        AppContent.myHistoryContent = note;
    }

    public static MyNote getMyNoteContent() {
        if (myNoteContent == null) {
            myNoteContent = new MyNote();
        }
        return myNoteContent;
    }

    public static void setMyNoteContent(MyNote note) {

        AppContent.myNoteContent = note;
    }

    public static Dictionary getDictionary() {
        if (dictionaryContent == null) {
            dictionaryContent = new Dictionary();
            dictionaryContent.createDictionary();
        }
        return dictionaryContent;
    }

    public static void setDictionary(Dictionary dictionary) {
        AppContent.dictionaryContent = dictionary;
    }
}
