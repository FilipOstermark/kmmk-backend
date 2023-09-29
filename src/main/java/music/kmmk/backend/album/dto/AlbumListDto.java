package music.kmmk.backend.album.dto;

import music.kmmk.backend.common.dto.PaginatedDto;

import java.util.List;

/**
 * Implement Pageable instead?
 */
// TODO Add URI
public class AlbumListDto extends PaginatedDto<AlbumDto> {

    public AlbumListDto(int count, int page, int lastPage, List<AlbumDto> results) {
        super(count, page, lastPage, results);
    }

}
