package music.kmmk.backend.common.dto;


public record MessageDto(
        String message
) {
    public MessageDto() {
        this("OK");
    }
}
