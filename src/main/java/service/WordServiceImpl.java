package service;

import entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.WordRepository;
import view.PlainWordView;
import view.WordView;
import viewmapper.PlainWordViewMapper;

import java.util.List;

/**
 * Created by nectarius on 11/17/13.
 */
@Service
public class WordServiceImpl implements  WordService{

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private PlainWordViewMapper plainWordViewMapper;

    @Override
    public List<PlainWordView> findAll() {
       List<Word>  words = wordRepository.findAll();
       return  plainWordViewMapper.createList( words);
    }

    @Override
    public PlainWordView save(PlainWordView plainWordView) {
        Word word = plainWordViewMapper.copyFrom(plainWordView,new Word());
        word = wordRepository.save(word);
        return plainWordViewMapper.create(word);
    }

    @Override
    public void deleteById(Long id) {
        wordRepository.delete(id);
    }

    @Override
    public PlainWordView findById(Long id) {
       Word word = wordRepository.findOne(id);
       return plainWordViewMapper.create(word);
    }


}
