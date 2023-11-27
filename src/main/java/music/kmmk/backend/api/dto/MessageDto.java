package music.kmmk.backend.api.dto;


public record MessageDto(
        String message
) {
    public MessageDto() {
        this("OK");
    }
}
