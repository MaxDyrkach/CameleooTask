package mx.ojt.pxm.kameleoontask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"name", "email", "dateCreation"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id", "password", "quotes"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", unique = true)
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "date_creation")
    private Instant dateCreation;
    @OneToMany(mappedBy = "userPosted", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Quote> quotes;

}
