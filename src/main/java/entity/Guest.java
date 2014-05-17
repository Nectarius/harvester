package entity;

import javax.persistence.*;

/**
 * Created by nectarius on 10.05.14.
 */
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String description;

    private GuestStatus status;

    private String byWhomWasAdded;

    private String transport;

    @OneToOne
    private Account account;

    @ManyToOne
    private Event event;

    /**
     *
     *  how he was going to get to place
     *
     * @return
     */
    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getByWhomWasAdded() {
        return byWhomWasAdded;
    }

    public void setByWhomWasAdded(String byWhomWasAdded) {
        this.byWhomWasAdded = byWhomWasAdded;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GuestStatus getStatus() {
        return status;
    }

    public void setStatus(GuestStatus status) {
        this.status = status;
    }
}
