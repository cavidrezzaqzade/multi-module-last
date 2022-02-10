package az.gov.mia.grp.util;

import az.gov.mia.grp.response.MessageResponse;
import az.gov.mia.grp.response.PageableResponse;
import az.gov.mia.grp.response.Reason;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PaginationHelper<DTO> {

    public ResponseEntity<?> getResponseEntity(List<DTO> dtoList, int number, long totalElements, int totalPages) {
        PageableResponse<DTO> response = new PageableResponse<>();

        response.setPages(dtoList);
        response.setCurrentPage(number);
        response.setTotalItems(totalElements);
        response.setTotalPages(totalPages);

        return MessageResponse.successResponse(Reason.SUCCESS_GET.getValue(), response);
    }
}