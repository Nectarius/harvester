// -- DO NOT EDIT  -  THIS CODE WILL BE REGENERATED! --
package viewmapper;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import view.PlainGuestView;
import entity.Guest;

@Service
public class PlainGuestViewMapper {


	public PlainGuestView create(Guest guest) {
		if( guest == null ) return null;
		PlainGuestView plainGuestView = new PlainGuestView();
		return copyTo(guest, plainGuestView);
	}
	
	public List<PlainGuestView> createList(Collection<Guest> source)
	{
	    List<PlainGuestView> result = new ArrayList<PlainGuestView>(source.size());
	    for (Guest guest : source)
	    {
	        result.add(create(guest));
	    }
		return result;
	}	
 
 	public PlainGuestView copyTo(Guest source, PlainGuestView destination) {
				destination.setId(source.getId());
			
				destination.setName(source.getName());
			
				destination.setSurname(source.getSurname());
			
				destination.setBrief(source.getBrief());
			
				destination.setDescription(source.getDescription());
			
				destination.setStatus(source.getStatus());
			
				destination.setByWhomWasAdded(source.getByWhomWasAdded());
			
				destination.setTransport(source.getTransport());
			
		return destination;
	}
	
	public Guest copyFrom(PlainGuestView source, Guest destination) {
				destination.setId(source.getId());
				destination.setName(source.getName());
				destination.setSurname(source.getSurname());
				destination.setBrief(source.getBrief());
				destination.setDescription(source.getDescription());
				destination.setStatus(source.getStatus());
				destination.setByWhomWasAdded(source.getByWhomWasAdded());
				destination.setTransport(source.getTransport());
		return destination;
	}
}
