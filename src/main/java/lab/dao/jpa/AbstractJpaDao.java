package lab.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("WeakerAccess")
public class AbstractJpaDao {
    @PersistenceUnit
    private EntityManagerFactory emf;

    protected void withEntityManager(Consumer<EntityManager> entityManagerConsumer) {
        EntityManager em = emf.createEntityManager();
        entityManagerConsumer.accept(em);
        em.close();
    }

    protected <T> T mapEntityManager(Function<EntityManager, T> entityManagerMapper) {
        EntityManager em = emf.createEntityManager();
        T result = entityManagerMapper.apply(em);
        em.close();
        return result;
    }

    protected void withEntityManagerInTransaction(Consumer<EntityManager> entityManagerConsumer) {
        withEntityManager(entityManager -> {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManagerConsumer.accept(entityManager);
            transaction.commit();
        });
    }
}