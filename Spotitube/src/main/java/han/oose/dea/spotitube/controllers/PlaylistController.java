package han.oose.dea.spotitube.controllers;

import han.oose.dea.spotitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spotitube.controllers.dto.TrackDTO;
import han.oose.dea.spotitube.controllers.service.LoginService;
import han.oose.dea.spotitube.controllers.service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    private LoginService loginService;
    private PlaylistService playlistService;

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GET
    public Response getAllPlaylists(@QueryParam("token") String token) {
        var playlists = playlistService.getAllPlaylists(token);
        var response = Response.status(Response.Status.OK).entity(playlists).build();

        return response;
    }

    @GET
    @Path("/{playlistId}/tracks")
    public Response getPlaylistsTracks(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        var tracks = playlistService.getPlaylistsTracks(playlistId);
        var response = Response.status(Response.Status.OK).entity(tracks).build();

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlist) {
        playlistService.addPlaylist(token, playlist);

        var playlists = playlistService.getAllPlaylists(token);
        var response = Response.status(Response.Status.CREATED).entity(playlists).build();

        return response;
    }

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

    @DELETE
    @Path("/{playlistId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        playlistService.deletePlaylist(token, playlistId);

        var playlists = playlistService.getAllPlaylists(token);
        var response = Response.status(Response.Status.OK).entity(playlists).build();

        return response;
    }

    @DELETE
    @Path("{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        playlistService.removeTrackFromPlaylist(token, playlistId, trackId);

        var tracks = playlistService.getPlaylistsTracks(playlistId);
        var response = Response.status(Response.Status.OK).entity(tracks).build();

        return response;
    }

    @POST
    @Path("{playlistId}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, TrackDTO track) {
        playlistService.addTrackToPlaylist(token, playlistId, track);

        var tracks = playlistService.getPlaylistsTracks(playlistId);
        var response = Response.status(Response.Status.OK).entity(tracks).build();

        return response;
    }
}
