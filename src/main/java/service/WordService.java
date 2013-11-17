package service;

import view.PlainWordView;
import view.WordView;

import java.util.List;

/**
 * Created by nectarius on 11/17/13.
 */
public interface WordService {

    List<PlainWordView> findAll();

    PlainWordView save(PlainWordView plainWordView);

    void deleteById(Long id);

    PlainWordView findById(Long id);


}
