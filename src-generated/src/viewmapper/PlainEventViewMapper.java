// -- DO NOT EDIT  -  THIS CODE WILL BE REGENERATED! --
package viewmapper;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import view.PlainEventView;
import entity.Event;

@Service
public class PlainEventViewMapper {


	public PlainEventView create(Event event) {
		if( event == null ) return null;
		PlainEventView plainEventView = new PlainEventView();
		return copyTo(event, plainEventView);
	}
	
	public List<PlainEventView> createList(Collection<Event> source)
	{
	    List<PlainEventView> result = new ArrayList<PlainEventView>(source.size());
	    for (Event event : source)
	    {
	        result.add(create(event));
	    }
		return result;
	}	
 
 	public PlainEventView copyTo(Event source, PlainEventView destination) {
				destination.setId(source.getId());
			
				destination.setName(source.getName());
			
				destination.setDescription(source.getDescription());
			
				destination.setStatus(source.isStatus());
			
		return destination;
	}
	
	public Event copyFrom(PlainEventView source, Event destination) {
				destination.setId(source.getId());
				destination.setName(source.getName());
				destination.setDescription(source.getDescription());
				destination.setStatus(source.isStatus());
		return destination;
	}
}
