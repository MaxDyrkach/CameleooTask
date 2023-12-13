package mx.ojt.pxm.kameleoontask.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mx.ojt.pxm.kameleoontask.model.Vote;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class QuoteDto {

    private Long id;
    private String content;
    private Instant dateCreation;
    private Instant dateUpdate;
    private String userPosted;
    private Long rank;
    private Set<Vote> votes;

}
