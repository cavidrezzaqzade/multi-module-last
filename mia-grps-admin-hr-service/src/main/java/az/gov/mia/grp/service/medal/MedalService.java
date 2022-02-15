package az.gov.mia.grp.service.medal;

import az.gov.mia.grp.api.medal.MedalApiDelegate;
import az.gov.mia.grp.entity.medal.MedalEntity;
import az.gov.mia.grp.model.MedalDTO;
import az.gov.mia.grp.repository.medal.MedalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@PreAuthorize("hasAuthority('HR_CRUD')")
public class MedalService implements MedalApiDelegate {

    private final MedalRepository repository;

    public MedalService(MedalRepository repository) {
        this.repository = repository;
    }

    public static MedalDTO dtoMapper(MedalEntity entity) {
        return new MedalDTO(entity.getId(), entity.getName());
    }



    private MedalEntity entityMapper(MedalDTO dto) {
        return entityMapper(dto, (new MedalEntity()));
    }

    public static MedalEntity entityMapper(MedalDTO dto, MedalEntity entity) {
        entity.setName(dto.getName());
        return entity;
    }

//    @PreAuthorize("hasAnyAuthority('HR_CRUD','USER_CRUD')")
    @Override
    public ResponseEntity<List<MedalDTO>> getAll(){
        List<MedalDTO> DTOList = repository.getAllByIdNotNullOrderById().stream()
                .map(MedalService::dtoMapper).collect(Collectors.toList());
        return ResponseEntity.ok(DTOList);
    }

    @Override
    public ResponseEntity<MedalDTO> show(long id) {
        MedalEntity entity = repository.findById(id).get();
//                .orElseThrow(() -> new ItemNotFoundException(Reason.NOT_FOUND.getValue()));

        return ResponseEntity.ok(dtoMapper(entity));
    }


    @Override
    public ResponseEntity<?> add(MedalDTO dto) {
        MedalEntity entity = entityMapper(dto);

        //TODO:: must be in validation case (custom validation or Generic validation)
        Map<String, String> map = new HashMap<>();

        if (repository.existsByNameIgnoreCase(dto.getName())){
            map.put("name","Medal adı mövcuddur");
        }

//        if(!map.isEmpty()) throw new ItemNotFoundMapValidationException(map);

        MedalEntity createdData = repository.save(entity);

        return null;
//        return MessageResponse.successResponse(Reason.SUCCESS_ADD.getValue(), dtoMapper(createdData));
    }

    @Override
    public ResponseEntity<?> update(long id, MedalDTO dto) {
        Optional<MedalEntity> entity = repository.findById(id);

        if (entity.isPresent()) {

            //TODO:: must be in validation case (custom validation or Generic validation)
            Map<String, String> map = new HashMap<>();

            MedalEntity alreadyExistedEntityWithSameName = repository.findByNameIgnoreCase(dto.getName());

            if (alreadyExistedEntityWithSameName != null && alreadyExistedEntityWithSameName.getId() != id){
                map.put("name","Medal adı mövcuddur");
            }

//            if(!map.isEmpty()) throw new ItemNotFoundMapValidationException(map);

            MedalEntity updatedEntity = repository.save(entityMapper(dto, entity.get()));

            return null;
//            return MessageResponse.successResponse(Reason.SUCCESS_UPDATE.getValue(), dtoMapper(updatedEntity));
        } else {
            return null;
//            throw new ItemNotFoundException(Reason.NOT_FOUND.getValue());
        }
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        repository.deleteById(id);

        return null;
//        return MessageResponse.successDelete(Reason.SUCCESS_DELETE.getValue());
    }
}
