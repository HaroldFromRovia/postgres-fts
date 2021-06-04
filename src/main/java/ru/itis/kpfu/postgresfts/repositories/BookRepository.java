package ru.itis.kpfu.postgresfts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.kpfu.postgresfts.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Long, Book> {

    @Query("SELECT p FROM Pokemon p WHERE fts(:text) = true")
    List<Book> search(@Param("text") String text);

    //後篇、斷篇第十
}
