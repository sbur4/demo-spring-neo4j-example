package org.example.repository;

import org.example.model.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends Neo4jRepository<Book, Long> {
    Book findOneByTitle(String title);

    List<Book> findAllByYear(Integer year);

    @Query("MATCH (b:Book)-[:WRITTEN_BY]->(a:Author) WHERE a.name = $name AND b.year > $year RETURN b")
    List<Book> findBooksAfterYear(@Param("name") String name, @Param("year") Integer year);
}
