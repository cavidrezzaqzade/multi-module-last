package az.gov.mia.grp.api.medal;

import az.gov.mia.grp.model.MedalDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedalApiDelegate {
    default ResponseEntity<?> add(MedalDTO dto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    default ResponseEntity<List<MedalDTO>> getAll() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    default ResponseEntity<MedalDTO> show(long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    default ResponseEntity<?> update(long id, MedalDTO dto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    default ResponseEntity<?> delete(long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}