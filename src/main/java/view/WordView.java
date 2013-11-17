package view;

/**
 * Created by nectarius on 11/17/13.
 */
public class WordView {

    private Long id;

    private String word;

    private String translate;

    private Integer priority;

    private String whence;

    public WordView(Long id, String word, String translate, Integer priority, String whence) {
        this.id = id;
        this.word = word;
        this.translate = translate;
        this.priority = priority;
        this.whence = whence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getWhence() {
        return whence;
    }

    public void setWhence(String whence) {
        this.whence = whence;
    }
}
