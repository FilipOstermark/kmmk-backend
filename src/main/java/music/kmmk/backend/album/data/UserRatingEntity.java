package music.kmmk.backend.album.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import music.kmmk.backend.user.data.UserEntity;

@Entity
@Table(name = "user_ratings")
@JsonIgnoreProperties(value = "album")
public class UserRatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private AlbumEntity album;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserRatingEntity() { }

    public UserRatingEntity(UserEntity user, Integer rating) {
        this.user = user;
        this.rating = rating;
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user  = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }
}
