package music.kmmk.backend.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class PaginatedDto<T> {

    private final int count;
    private final int page;
    private final int lastPage;
    private final List<T> results;

    protected PaginatedDto(int count, int page, int lastPage, List<T> results) {
        this.count = count;
        this.page = page;
        this.lastPage = lastPage;
        this.results = results;
    }

    @JsonProperty("count")
    int count() {
        return this.count;
    }

    @JsonProperty("page")
    int page() {
        return this.page;
    }

    @JsonProperty("lastPage")
    int lastPage() {
        return this.lastPage;
    }

    @JsonProperty("results")
    List<T> results() {
        return this.results;
    }

}
