package it.uniroma2.sc.demospringhibernate.control;

public interface IDeleteController {
    /**
     * Delete dog by his id.
     *
     * @return number of items deleted.
     */
    int deleteDogById(Long id) throws Exception;
}
