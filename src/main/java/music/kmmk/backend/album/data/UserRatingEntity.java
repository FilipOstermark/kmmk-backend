package music.kmmk.backend.album.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserRatingEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private Integer rating;

    public UserRatingEntity() { }

    public UserRatingEntity(String userEmail, Integer rating) {
        this.userEmail = userEmail;
        this.rating = rating;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
