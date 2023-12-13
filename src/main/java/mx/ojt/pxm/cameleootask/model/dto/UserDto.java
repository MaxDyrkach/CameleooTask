package mx.ojt.pxm.cameleootask.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mx.ojt.pxm.cameleootask.model.Quote;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class UserDto {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private Instant dateCreation;
    private Set<Quote> quotes;
}
