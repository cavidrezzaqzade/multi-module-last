package az.gov.mia.grp.util;

public interface ConverterImpl<T, M> {

    T convertFromEntityToDTO(M entity);

    M convertFromDtoToEntity(T dto);
}