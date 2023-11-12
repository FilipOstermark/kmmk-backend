package music.kmmk.backend.album.data;

import jakarta.persistence.*;
import music.kmmk.backend.user.data.UserEntity;

import java.util.Set;

@SuppressWarnings("unused")
@Entity
@Table(name = "albums")
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String mbid;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String artistName;

    @Column(nullable = false)
    private Integer releaseYear;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String bestSongTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String worstSongTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String listeningOccasion;

    @Column(nullable = false)
    private String discussionDate;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private Set<UserRatingEntity> ratings;

    @ManyToOne
    @JoinColumn(name = "picked_by_user_id")
    private UserEntity pickedByUser;

    public AlbumEntity() { }

    public AlbumEntity(String mbid,
                       String title,
                       String artistName,
                       Integer releaseYear,
                       String summary,
                       String bestSongTitle,
                       String worstSongTitle,
                       String listeningOccasion,
                       String discussionDate,
                       Set<UserRatingEntity> ratings,
                       UserEntity pickedByUser) {
        this.mbid = mbid;
        this.title = title;
        this.artistName = artistName;
        this.releaseYear = releaseYear;
        this.summary = summary;
        this.bestSongTitle = bestSongTitle;
        this.worstSongTitle = worstSongTitle;
        this.listeningOccasion = listeningOccasion;
        this.discussionDate = discussionDate;
        this.ratings = ratings;
        this.pickedByUser = pickedByUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBestSongTitle() {
        return bestSongTitle;
    }

    public void setBestSongTitle(String bestSongTitle) {
        this.bestSongTitle = bestSongTitle;
    }

    public String getWorstSongTitle() {
        return worstSongTitle;
    }

    public void setWorstSongTitle(String worstSongTitle) {
        this.worstSongTitle = worstSongTitle;
    }

    public String getListeningOccasion() {
        return listeningOccasion;
    }

    public void setListeningOccasion(String listeningOccasion) {
        this.listeningOccasion = listeningOccasion;
    }

    public String getDiscussionDate() {
        return discussionDate;
    }

    public void setDiscussionDate(String discussionDate) {
        this.discussionDate = discussionDate;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Set<UserRatingEntity> getRatings() {
        return ratings;
    }

    public void setRatings(Set<UserRatingEntity> ratings) {
        this.ratings = ratings;
    }

    public UserEntity getPickedByUser() {
        return pickedByUser;
    }

    public void setPickedByUser(UserEntity pickedByUser) {
        this.pickedByUser = pickedByUser;
    }
}
