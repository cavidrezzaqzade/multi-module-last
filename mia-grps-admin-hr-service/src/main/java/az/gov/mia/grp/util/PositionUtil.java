//package az.gov.mia.grp.util;
//
//import az.gov.mia.grp.entity.rank.Positionable;
//import az.gov.mia.grp.exception.ItemNotFoundException;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface PositionUtil {
//
//    static int generatePosition(JpaRepository<?, ?> repository) {
//        return (int) repository.count() + 1;
//    }
//
//    static <T extends Positionable> void changePosition(JpaRepository<T, Long> repository, Long fromId, Long toId) {
//        T fromEntity = repository.findById(fromId)
//                .orElseThrow(ItemNotFoundException::new);
//
//        T toEntity = repository.findById(toId)
//                .orElseThrow(ItemNotFoundException::new);
//
//        Integer fromPosition = fromEntity.getPosition();
//        fromEntity.setPosition(toEntity.getPosition());
//        toEntity.setPosition(fromPosition);
//
//        repository.save(fromEntity);
//        repository.save(toEntity);
//    }
//}
