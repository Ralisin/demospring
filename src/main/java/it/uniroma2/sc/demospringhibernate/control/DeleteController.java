package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.dao.DogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DeleteController implements IDeleteController {
    @Autowired
    private DogDao dogDao;

    @Override
    public int deleteDogById(Long id, String token) throws NoSuchElementException {
        if(!dogDao.existsById(id)) throw new NoSuchElementException("No dog found for id " + id);

        dogDao.deleteById(id);
        return 1;
    }
}
