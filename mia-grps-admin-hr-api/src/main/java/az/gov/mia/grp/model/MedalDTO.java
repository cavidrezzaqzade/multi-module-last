package az.gov.mia.grp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class MedalDTO {
    private Long id;

    @NotBlank(message = "Medal adı boş ola bilməz")
    private String name;
}
