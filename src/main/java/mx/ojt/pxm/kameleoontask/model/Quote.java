package mx.ojt.pxm.kameleoontask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "quotes")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"content", "dateCreation"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"votes"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "date_creation")
    private Instant dateCreation;
    @Column(name = "date_update")
    private Instant dateUpdate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private User userPosted;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "quote", cascade = CascadeType.ALL)
    private Set<Vote> votes;
    @Column(name = "rank")
    private Long rank;


}
