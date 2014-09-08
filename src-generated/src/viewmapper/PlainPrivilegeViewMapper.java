// -- DO NOT EDIT  -  THIS CODE WILL BE REGENERATED! --
package viewmapper;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import view.PlainPrivilegeView;
import entity.Privilege;

@Service
public class PlainPrivilegeViewMapper {


	public PlainPrivilegeView create(Privilege privilege) {
		if( privilege == null ) return null;
		PlainPrivilegeView plainPrivilegeView = new PlainPrivilegeView();
		return copyTo(privilege, plainPrivilegeView);
	}
	
	public List<PlainPrivilegeView> createList(Collection<Privilege> source)
	{
	    List<PlainPrivilegeView> result = new ArrayList<PlainPrivilegeView>(source.size());
	    for (Privilege privilege : source)
	    {
	        result.add(create(privilege));
	    }
		return result;
	}	
 
 	public PlainPrivilegeView copyTo(Privilege source, PlainPrivilegeView destination) {
				destination.setId(source.getId());
			
				destination.setName(source.getName());
			
		return destination;
	}
	
	public Privilege copyFrom(PlainPrivilegeView source, Privilege destination) {
				destination.setId(source.getId());
				destination.setName(source.getName());
		return destination;
	}
}
