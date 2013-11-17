// -- DO NOT EDIT  -  THIS CODE WILL BE REGENERATED! --
package viewmapper;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import view.PlainWordView;
import entity.Word;

@Service
public class PlainWordViewMapper {


	public PlainWordView create(Word word) {
		if( word == null ) return null;
		PlainWordView plainWordView = new PlainWordView();
		return copyTo(word, plainWordView);
	}
	
	public List<PlainWordView> createList(Collection<Word> source)
	{
	    List<PlainWordView> result = new ArrayList<PlainWordView>(source.size());
	    for (Word word : source)
	    {
	        result.add(create(word));
	    }
		return result;
	}	
 
 	public PlainWordView copyTo(Word source, PlainWordView destination) {
				destination.setId(source.getId());
			
				destination.setWord(source.getWord());
			
				destination.setTranslate(source.getTranslate());
			
				destination.setPriority(source.getPriority());
			
				destination.setWhence(source.getWhence());
			
		return destination;
	}
	
	public Word copyFrom(PlainWordView source, Word destination) {
				destination.setId(source.getId());
				destination.setWord(source.getWord());
				destination.setTranslate(source.getTranslate());
				destination.setPriority(source.getPriority());
				destination.setWhence(source.getWhence());
		return destination;
	}
}
