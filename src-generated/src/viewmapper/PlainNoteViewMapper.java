// -- DO NOT EDIT  -  THIS CODE WILL BE REGENERATED! --
package viewmapper;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import view.PlainNoteView;
import entity.Note;

@Service
public class PlainNoteViewMapper {


	public PlainNoteView create(Note note) {
		if( note == null ) return null;
		PlainNoteView plainNoteView = new PlainNoteView();
		return copyTo(note, plainNoteView);
	}
	
	public List<PlainNoteView> createList(Collection<Note> source)
	{
	    List<PlainNoteView> result = new ArrayList<PlainNoteView>(source.size());
	    for (Note note : source)
	    {
	        result.add(create(note));
	    }
		return result;
	}	
 
 	public PlainNoteView copyTo(Note source, PlainNoteView destination) {
				destination.setId(source.getId());
			
				destination.setTheme(source.getTheme());
			
				destination.setLastUpdateTime(source.getLastUpdateTime());
			
				destination.setCreateTime(source.getCreateTime());
			
				destination.setText(source.getText());
			
		return destination;
	}
	
	public Note copyFrom(PlainNoteView source, Note destination) {
				destination.setId(source.getId());
				destination.setTheme(source.getTheme());
				destination.setLastUpdateTime(source.getLastUpdateTime());
				destination.setCreateTime(source.getCreateTime());
				destination.setText(source.getText());
		return destination;
	}
}
