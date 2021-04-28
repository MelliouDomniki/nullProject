package com.example.nullproject2.roles;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private Erole name;


}
