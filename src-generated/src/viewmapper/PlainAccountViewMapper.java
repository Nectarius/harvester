// -- DO NOT EDIT  -  THIS CODE WILL BE REGENERATED! --
package viewmapper;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import view.PlainAccountView;
import entity.Account;

@Service
public class PlainAccountViewMapper {


	public PlainAccountView create(Account account) {
		if( account == null ) return null;
		PlainAccountView plainAccountView = new PlainAccountView();
		return copyTo(account, plainAccountView);
	}
	
	public List<PlainAccountView> createList(Collection<Account> source)
	{
	    List<PlainAccountView> result = new ArrayList<PlainAccountView>(source.size());
	    for (Account account : source)
	    {
	        result.add(create(account));
	    }
		return result;
	}	
 
 	public PlainAccountView copyTo(Account source, PlainAccountView destination) {
				destination.setId(source.getId());
			
				destination.setLogin(source.getLogin());
			
				destination.setPassword(source.getPassword());
			
				destination.setName(source.getName());
			
		return destination;
	}
	
	public Account copyFrom(PlainAccountView source, Account destination) {
				destination.setId(source.getId());
				destination.setLogin(source.getLogin());
				destination.setPassword(source.getPassword());
				destination.setName(source.getName());
		return destination;
	}
}
