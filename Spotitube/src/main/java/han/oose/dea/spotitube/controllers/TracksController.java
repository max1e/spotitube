package han.oose.dea.spotitube.controllers;

import han.oose.dea.spotitube.controllers.service.LoginService;
import han.oose.dea.spotitube.controllers.service.TracksService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TracksController {

    private LoginService loginService;
    private TracksService tracksService;

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setTracksService(TracksService tracksService) {
        this.tracksService = tracksService;
    }

    /**
     * Returns response containing the available tracks of the given playlist, or all tracks if none is given
     *
     * @param token The token of the querying user
     * @param playlistId The playlist whose available tracks are requested
     * @return Response OK containing available tracks
     */
    @GET
    public Response getAvailableTracks(@QueryParam("token") String token, @QueryParam("playlistId") Integer playlistId) {
        var tracks = tracksService.getAvailableTracks(token, playlistId);
        var response = Response.status(Response.Status.OK).entity(tracks).build();

        return response;
    }
}
