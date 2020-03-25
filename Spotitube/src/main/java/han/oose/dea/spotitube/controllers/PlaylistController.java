package han.oose.dea.spotitube.controllers;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.controllers.service.LoginService;
import han.oose.dea.spotitube.controllers.service.PlaylistService;
import han.oose.dea.spotitube.controllers.service.TracksService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    private LoginService loginService;
    private PlaylistService playlistService;
    private TracksService tracksService;

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setTracksService(TracksService tracksService) {
        this.tracksService = tracksService;
    }

    /**
     * Returns all playlists
     *
     * @param token The token of the querying user
     * @return Response OK containing all playlists with total duration
     */
    @GET
    public Response getAllPlaylists(@QueryParam("token") String token) {
        var playlists = playlistService.getAllPlaylists(token);
        var response = Response.status(Response.Status.OK).entity(playlists).build();

        return response;
    }

    /**
     * Returns all tracks of playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose tracks are requested
     * @return Response OK containing all tracks of the playlist
     */
    @GET
    @Path("/{playlistId}/tracks")
    public Response getPlaylistsTracks(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        var tracks = tracksService.getPlaylistsTracks(token, playlistId);
        var response = Response.status(Response.Status.OK).entity(tracks).build();

        return response;
    }

    /**
     * Creates a new playlist
     *
     * @param token The token of the querying user
     * @param playlist The playlist to be added
     * @return Response CREATED containing all playlists
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlist) {
        playlistService.addPlaylist(token, playlist);

        var playlists = playlistService.getAllPlaylists(token);
        var response = Response.status(Response.Status.CREATED).entity(playlists).build();

        return response;
    }

    /**
     * Edits the name of a playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose name is edited
     * @param playlist The playlist whose edited containing its new name
     * @return Response OK containing all playlists
     */
    @PUT
    @Path("/{playlistId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, PlaylistDTO playlist) {
        playlistService.editPlaylistName(token, playlistId, playlist);

        var playlists = playlistService.getAllPlaylists(token);
        var response = Response.status(Response.Status.OK).entity(playlists).build();

        return response;
    }

    /**
     * Deletes a playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist to be deleted
     * @return Response OK containing all playlists
     */
    @DELETE
    @Path("/{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        playlistService.deletePlaylist(token, playlistId);

        var playlists = playlistService.getAllPlaylists(token);
        var response = Response.status(Response.Status.OK).entity(playlists).build();

        return response;
    }

    /**
     * Removes a track from a playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whose track is removed
     * @param trackId The ID of the track to be removed
     * @return Response OK containing all playlists
     */
    @DELETE
    @Path("{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        playlistService.removeTrackFromPlaylist(token, playlistId, trackId);

        var tracks = tracksService.getPlaylistsTracks(token, playlistId);
        var response = Response.status(Response.Status.OK).entity(tracks).build();

        return response;
    }

    /**
     * Adds a track to a playlist
     *
     * @param token The token of the querying user
     * @param playlistId The ID of the playlist whom a track is added to
     * @param track The track to be added
     * @return Response OK containing all tracks of the playlist
     */
    @POST
    @Path("{playlistId}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, TrackDTO track) {
        playlistService.addTrackToPlaylist(token, playlistId, track);

        var tracks = tracksService.getPlaylistsTracks(token, playlistId);
        var response = Response.status(Response.Status.CREATED).entity(tracks).build();

        return response;
    }
}
