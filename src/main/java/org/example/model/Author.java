package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Node("Author")
//@Node
public class Author implements Serializable  {
    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonDeserialize(as = List.class)
    @JsonBackReference
    @Relationship(type = "WRITTEN_BY", direction = Relationship.Direction.INCOMING)
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }

//    @JsonCreator
//    public Author(@JsonProperty("version") long version,
//                  @JsonProperty("id") long id,
//                  @JsonProperty("name") String name,
//                  @JsonProperty("books") List<Book> books) {
//        this.version = version;
//        this.id = id;
//        this.name = name;
//        this.books = books;
//    }
}
