package az.gov.mia.grp.api.medal;

import az.gov.mia.grp.model.MedalDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * https://vabiss.atlassian.net/browse/GA-147
 * 1. Medallar soraqçası.
 * İnput adı - Medalın adı - string, required, unique, long text.
 */

@Validated
@Tag(name = "MedalApi", description = "Medallar API")
public interface MedalApi {
    default MedalApiDelegate getDelegate() {
        return new MedalApiDelegate() {
        };
    }

    @Operation(summary = "Medalları məlumatları listelemek",
            description = "Get all Medals",
            tags = {"MedalApi"})
    @GetMapping("")
    default ResponseEntity<?> getAll() {
        return getDelegate().getAll();
    }

    @Operation(summary = "Medal məlumatını qaytarmaq",
            description = "Get Medal by ID",
            tags = {"MedalApi"})
    @GetMapping("/{id}")
    default ResponseEntity<?> show(@PathVariable Long id) {
        return getDelegate().show(id);
    }

    @Operation(summary = "Medal məlumatını elave etmek",
            description = "Add new Medal",
            tags = {"MedalApi"})
    @PostMapping(value = "", consumes = {"application/json"})
    default ResponseEntity<?> add(@Valid @RequestBody MedalDTO dto) {
        return getDelegate().add(dto);
    }

    @Operation(summary = "Medal məlumatını yenilemek",
            description = "Update Medal by ID",
            operationId = "updateMedalApi",
            tags = {"MedalApi"})
    @PutMapping(value = "/{id}", consumes = {"application/json"})
    default ResponseEntity<?> update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody MedalDTO dto) {
        return getDelegate().update(id, dto);
    }

    @Operation(summary = "Medal məlumatını silmek",
            description = "Delete Medal by ID",
            tags = {"MedalApi"})
    @DeleteMapping(value = "/{id}")
    default ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return getDelegate().delete(id);
    }
}