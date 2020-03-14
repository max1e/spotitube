package han.oose.dea.spottitube.controllers;

import han.oose.dea.spottitube.controllers.dto.PlaylistDTO;
import han.oose.dea.spottitube.controllers.dto.TrackDTO;
import han.oose.dea.spottitube.controllers.service.LoginService;
import han.oose.dea.spottitube.controllers.service.PlaylistService;

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
        Response response;

        if (!loginService.doesTokenMatch(token)) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            var playlists = playlistService.getAllPlaylists();
            response = Response.status(Response.Status.OK).entity(playlists).build();
        }

        return response;
    }

    @GET
    @Path("/{playlistId}/tracks")
    public Response getPlaylistsTracks(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        Response response;

        if (!loginService.doesTokenMatch(token)) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            var tracks = playlistService.getPlaylistsTracks(playlistId);
            response = Response.status(Response.Status.OK).entity(tracks).build();
        }

        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlist) {
        Response response;

        if (!loginService.doesTokenMatch(token)) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            playlistService.addPlaylist(playlist);
            var playlists = playlistService.getAllPlaylists();
            response = Response.status(Response.Status.CREATED).entity(playlists).build();
        }

        return response;
    }

    @PUT
    @Path("/{playlistId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, PlaylistDTO playlist) {
        Response response;

        if (!loginService.doesTokenMatch(token)) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else if (playlist.getId() != playlistId) {
            throw new BadRequestException();
        }
        else {
            playlistService.editPlaylistName(playlist);
            var playlists = playlistService.getAllPlaylists();
            response = Response.status(Response.Status.OK).entity(playlists).build();
        }

        return response;
    }

    @DELETE
    @Path("/{playlistId}")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        Response response;

        if (!loginService.doesTokenMatch(token)) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            playlistService.deletePlaylist(playlistId);
            var playlists = playlistService.getAllPlaylists();
            response = Response.status(Response.Status.OK).entity(playlists).build();
        }

        return response;
    }

    @DELETE
    @Path("{playlistId}/tracks/{trackId}")
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        Response response;

        if (!loginService.doesTokenMatch(token)) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            playlistService.removeTrackFromPlaylist(playlistId, trackId);
            var tracks = playlistService.getPlaylistsTracks(playlistId);
            response = Response.status(Response.Status.OK).entity(tracks).build();
        }

        return response;
    }

    @POST
    @Path("{playlistId}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, TrackDTO track) {
        Response response;

        if (!loginService.doesTokenMatch(token)) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            playlistService.addTrackToPlaylist(playlistId, track);
            var tracks = playlistService.getPlaylistsTracks(playlistId);
            response = Response.status(Response.Status.OK).entity(tracks).build();
        }

        return response;
    }
}
