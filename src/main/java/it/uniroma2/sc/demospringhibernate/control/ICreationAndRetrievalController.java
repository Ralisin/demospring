package it.uniroma2.sc.demospringhibernate.control;

import it.uniroma2.sc.demospringhibernate.dto.CaneDTO;
import it.uniroma2.sc.demospringhibernate.entity.Persona;

import java.util.List;

public interface ICreationAndRetrievalController {

    /**
     * Creates a new dog entry.
     *
     * @param c The dog entity to be created.
     * @return The created dog.
     */
    CaneDTO createDog(CaneDTO c);

    /**
     * Loads a dog by its ID.
     *
     * @param idCane The ID of the dog to retrieve.
     * @return The dog entity with the specified ID.
     */
    CaneDTO loadDogById(Long idCane);

    /**
     * Loads all dogs from the database.
     *
     * @return A list of all dogs.
     */
    List<CaneDTO> loadDogs();

    /**
     * Searches for dogs by their name.
     *
     * @param nome The name of the dog to search for.
     * @return A list of dogs matching the given name.
     */
    List<CaneDTO> searchDogsByName(String nome);

    /**
     * Searches for dogs owned by a specific owner.
     *
     * @param idPadrone The ID of the owner.
     * @return A list of dogs belonging to the specified owner.
     * @throws Exception if the owner is not found.
     */
    List<CaneDTO> searchDogsByOwner(Long idPadrone) throws Exception;

    /**
     * Creates sample data for testing purposes.
     */
    void createSampleData();

    /**
     * Loads all people from the database.
     *
     * @return A list of all people.
     */
    List<Persona> loadPeople();
}

/**
 * Why use interfaces and their implementations:
 *
 * 1. **Abstraction**: Interfaces allow defining a contract (set of methods) without worrying about the implementation details.
 *    This makes the code more flexible and easier to change or extend.
 *
 * 2. **Loose Coupling**: By programming to an interface, rather than to a concrete class, you reduce dependencies between different parts
 *    of the system. This promotes loose coupling, where the implementation can be changed without affecting the dependent code.
 *
 * 3. **Multiple Implementations**: An interface allows the possibility of having multiple implementations. For example, you could have
 *    different implementations of `ICreationAndRetrievalController` that handle various types of data storage (in-memory, database, etc.).
 *
 * 4. **Testing and Mocking**: Interfaces are essential for unit testing. With interfaces, you can easily mock implementations
 *    in tests, allowing for isolation of different components in the system and more effective testing.
 *
 * 5. **Dependency Injection**: In frameworks like Spring, interfaces make it easier to inject different implementations using
 *    dependency injection, promoting flexibility and better management of application components.
 *
 * In this case, the interface `ICreationAndRetrievalController` defines methods for managing dogs and people,
 * while the actual implementation can vary, allowing future changes without breaking existing code.
 */
