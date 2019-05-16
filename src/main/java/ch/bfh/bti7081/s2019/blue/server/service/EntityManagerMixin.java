package ch.bfh.bti7081.s2019.blue.server.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Optional;
@Component
public class EntityManagerMixin {

    private final EntityManager em;

    @Inject
    public EntityManagerMixin(EntityManager em) {
        this.em = em;
    }

    public <T> EntityWrapper<T> get(int id, JpaRepository<T, Integer> repository) {
        Optional<T> originalOpt = repository.findById(id);

        if (originalOpt.isEmpty()) {
            return new EntityWrapper<>();
        }

        T original = originalOpt.get();
        em.detach(original);

        T modified = repository.getOne(id);
        em.detach(modified);

        return new EntityWrapper<>(original, modified);
    }
}