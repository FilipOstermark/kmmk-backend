package music.kmmk.backend.dto;


public record MessageDto(
        String message
) {
    public MessageDto() {
        this("OK");
    }
}
