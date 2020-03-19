package han.oose.dea.spotitube.controllers.dto;

import java.util.List;

public class PlaylistDTO {

    private int id;
    private String name;
    private boolean owner;
    private List<TrackDTO> tracks;
    private int duration;

    public PlaylistDTO() {
        //
    }

    public PlaylistDTO(int id, String name, boolean owner, List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public PlaylistDTO(int id, String name, boolean owner, List<TrackDTO> tracks, int duration) {
        this(id, name, owner, tracks);
        this.duration = duration;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
