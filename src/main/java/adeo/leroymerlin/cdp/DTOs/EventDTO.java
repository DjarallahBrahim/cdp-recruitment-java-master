package adeo.leroymerlin.cdp.DTOs;

import java.util.List;

public class EventDTO {
    private String title;
    private String imgUrl;
    private List<BandDTO> bands;
    public EventDTO() {

    }
    public EventDTO(String title, String imgUrl, List<BandDTO> bands) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.bands = bands;
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

    public List<BandDTO> getBands() {
        return bands;
    }

    public void setBands(List<BandDTO> bands) {
        this.bands = bands;
    }
}
