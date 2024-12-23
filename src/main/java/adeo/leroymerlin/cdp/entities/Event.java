package adeo.leroymerlin.cdp.entities;

import jakarta.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String imgUrl;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Band> bands;

    private Integer nbStars;

    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Band> getBands() {
        return bands;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }

    public Integer getNbStars() {
        return nbStars;
    }

    public void setNbStars(Integer nbStars) {
        this.nbStars = nbStars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", bands_id=" + (bands != null
                ? bands.stream()
                .map(band -> band.getId() != null ? String.valueOf(band.getId()) : "null")
                .collect(Collectors.joining(", "))
                : "No bands") +
                ", nbStars=" + nbStars +
                ", comment='" + comment + '\'' +
                '}';
    }

}
