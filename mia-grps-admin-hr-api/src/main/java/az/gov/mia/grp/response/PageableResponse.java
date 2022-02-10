package az.gov.mia.grp.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageableResponse<T> {

    private int currentPage;
    private int totalPages;
    private long totalItems;
    private List<T> pages;

    @Override
    public String toString() {
        return "PageableResponse{" +
                "currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                ", pages=" + pages +
                '}';
    }
}