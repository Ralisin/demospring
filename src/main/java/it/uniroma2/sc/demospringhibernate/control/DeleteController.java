package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.dao.DogDao;
import it.uniroma2.sc.demospringhibernate.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DeleteController implements IDeleteController {
    @Autowired
    private DogDao dogDao;

    @Autowired
    private PermissionService permissionService;

    @Override
    public int deleteDogById(Long id, String token) throws NoSuchElementException, SecurityException {
        if (!permissionService.hasPermissionToDelete(token)) throw new SecurityException("Do not have permissions to delete dogs");

        if(!dogDao.existsById(id)) throw new NoSuchElementException("No dog found for id " + id);

        dogDao.deleteById(id);
        return 1;
    }
}
