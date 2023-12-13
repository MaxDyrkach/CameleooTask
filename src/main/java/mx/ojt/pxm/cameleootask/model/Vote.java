package mx.ojt.pxm.cameleootask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.time.Instant;

@Entity
@Table(name = "votes", indexes = {@Index(columnList = "time")})
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"time", "val"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id","quote"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Quote quote;
    @Column(name = "time")
    private Instant time;
    @Column(name = "val")
    private Long val;

}
