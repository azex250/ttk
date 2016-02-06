package az.service;

import az.dao.ITagDAO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired; 


@Service
public class TagManager implements ITagManager {
	private ITagDAO tagDAO;

	@Autowired 
	public void setTagDAO(ITagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

	@Override
	public List<String> getTags() {
		return tagDAO.list(); 
	}
}