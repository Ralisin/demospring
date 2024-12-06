package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.dao.DogDao;
import it.uniroma2.sc.demospringhibernate.enums.Role;
import it.uniroma2.sc.demospringhibernate.secutiry.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteController implements IDeleteController {
    @Autowired
    private DogDao dogDao;

    @Autowired
    private TokenStorage tokenStorage;

    @Override
    public int deleteDogById(Long id, String token) throws Exception {
        if (tokenStorage.getRoleByToken(token) != Role.VETERINARIO) throw new Exception("Do not have permissions to delete dogs");

        if(!dogDao.existsById(id)) throw new Exception("No dog found for id " + id);

        dogDao.deleteById(id);
        return 1;
    }
}
