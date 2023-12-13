package application.demo1;

public class Word {

    public Word(){

    }
    public Word(String word_target, String word_explain){
        this.word_explain = word_explain;
        this.word_target = word_target;

    }


    private String word_target;
    private String word_explain;

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_explain() {
        return word_explain;
    }


    //    public Word(int id, String word, String pronounce, String html, String description){
//        this.id = id;
//        this.word = word;
//        this.pronounce = pronounce;
//        this.html = html;
//        this.description = description;
//    }
//    private int id;
//    private String word;
//    private String pronounce;
//    private String html;
//    private String description;
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setWord(String word) {
//        this.word = word;
//    }
//
//    public String getWord() {
//        return word;
//    }
//
//    public void setPronounce(String pronounce) {
//        this.pronounce = pronounce;
//    }
//
//    public String getPronounce() {
//        return pronounce;
//    }
//
//    public void setHtml(String html) {
//        this.html = html;
//    }
//
//    public String getHtml() {
//        return html;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getDescription() {
//        return description;
//    }
}
