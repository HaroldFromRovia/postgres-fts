package ru.itis.kpfu.postgresfts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.kpfu.postgresfts.models.Dictionary;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Dictionary, Long> {

    @RestResource(rel="fts-search", path = "fts-search")
    @Query("SELECT dictionary FROM Dictionary dictionary WHERE fts(:def) = true")
    List<Dictionary> ftsSearch(@Param("def") String def);

}
