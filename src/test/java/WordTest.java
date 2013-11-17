import entity.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import repository.WordRepository;

import java.util.List;

/**
 * Created by nectarius on 11/17/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class WordTest {

    @Autowired
    private WordRepository wordRepository;

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

     List<Word> wordList = wordRepository.findAll();

       for(Word word: wordList){
           System.out.println("word : "+word.getWord());
           System.out.println("translate : "+word.getTranslate());
           System.out.println("whence : "+word.getWhence());
           System.out.println("priority : "+word.getPriority());
       }



   }


}
