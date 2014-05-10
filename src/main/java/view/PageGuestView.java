package view;

import java.util.List;

/**
 * Created by nectarius on 10.05.14.
 */
public class PageGuestView {

    private List<PlainGuestView> content;

    private Integer totalPages;

    public PageGuestView(int totalPages, List<PlainGuestView> content) {
        this.totalPages = totalPages;
        this.content = content;
    }

    public List<PlainGuestView> getContent() {
        return content;
    }

    public void setContent(List<PlainGuestView> content) {
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
