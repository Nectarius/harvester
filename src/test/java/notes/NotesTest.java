package notes;

import entity.Account;
import entity.Note;
import entity.Privilege;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repository.AccountRepository;
import repository.NoteRepository;
import repository.PrivilegeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nectarius on 18.05.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class NotesTest {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    @Rollback(value = true)
    public void test() {

        Account account = accountRepository.findByLogin("Felix");

        if(account == null){
            return;
        }

        Note note = new Note();

        note.setAuthor(account);

        note.setTheme("Standard notes4");

        note.setText("The Bug is ready for test. Wait comments from Jenkis.\n" +
                "Cases physical devices and network gear have different implementation and should be retested both.\n" +
                "\n" +
                "it does since #167632");

        noteRepository.saveAndFlush(note);

    }

}
