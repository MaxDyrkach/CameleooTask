package mx.ojt.pxm.cameleootask.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.ojt.pxm.cameleootask.service.QuoteService;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotingDto {
    @NotBlank
    private String vote;
}
