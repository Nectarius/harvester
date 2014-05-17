package view;

import java.util.List;

/**
 * Created by nectarius on 10.05.14.
 */
public class PageEventView {

    private List<PlainEventView> content;

    private Integer totalPages;

    public PageEventView(int totalPages, List<PlainEventView> content) {
        this.totalPages = totalPages;
        this.content = content;
    }

    public List<PlainEventView> getContent() {
        return content;
    }

    public void setContent(List<PlainEventView> content) {
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
