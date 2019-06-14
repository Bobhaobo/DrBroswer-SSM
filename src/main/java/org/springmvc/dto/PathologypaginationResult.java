package org.springmvc.dto;

public class PathologypaginationResult {

    private PathologyDto pathologydto;

    private String image;

    public PathologypaginationResult(PathologyDto pathologydto, String image) {
        this.pathologydto = pathologydto;
        this.image = image;
    }

    public PathologyDto getPathologydto() {
        return pathologydto;
    }

    public void setPathologydto(PathologyDto pathologydto) {
        this.pathologydto = pathologydto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PathologypaginationResult{" +
                "pathologydto=" + pathologydto +
                ", image='" + image + '\'' +
                '}';
    }

    public PathologypaginationResult() {
    }
}
