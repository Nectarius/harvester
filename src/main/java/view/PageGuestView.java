package view;

import java.util.List;

/**
 * Created by nectarius on 10.05.14.
 */
public class PageGuestView {

    private List<PlainGuestView> content;

    private Integer totalPages;

    private PlainEventView event;

    public PlainEventView getEvent() {
        return event;
    }

    public void setEvent(PlainEventView event) {
        this.event = event;
    }

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
