package view;

import java.util.List;

/**
 * Created by nectarius on 10.05.14.
 */
public class PageNoteView {

    private List<PlainNoteView> content;

    private Integer totalPages;

    public PageNoteView(int totalPages, List<PlainNoteView> content) {
        this.totalPages = totalPages;
        this.content = content;
    }

    public List<PlainNoteView> getContent() {
        return content;
    }

    public void setContent(List<PlainNoteView> content) {
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
