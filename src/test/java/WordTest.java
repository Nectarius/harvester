import entity.Guest;
import entity.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.GuestRepository;
import repository.WordRepository;

import java.util.List;

/**
 * Created by nectarius on 11/17/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class WordTest {

    @Autowired
    private GuestRepository guestRepository;

   /* @Test
    public void test(){

        Word word = new Word();

        word.setWord("apparently");

        word.setPriority(0);

        word.setWhence("game of thrones");

        wordRepository.save(word);

    }*/

   @Test
   public void test(){

     List<Guest> guestList = guestRepository.findAll();

       for(Guest guest: guestList){
           System.out.println("name : "+ guest.getName());
           System.out.println("status : "+guest.getStatus());
       }



   }


}
