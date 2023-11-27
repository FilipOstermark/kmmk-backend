package music.kmmk.backend.api.user.dto;

import music.kmmk.backend.api.dto.PaginatedDto;

import java.util.List;

// TODO Add URI
public class UserListDto extends PaginatedDto<UserDto> {
    public UserListDto(int count, int page, int lastPage, List<UserDto> results) {
        super(count, page, lastPage, results);
    }
}
