package org.agoncal.quarkus.panache.resource;

import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.repository.ArtistRepository;

@Path("api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class ArtistResource {
    @Inject
    ArtistRepository repo;

    @GET
    public List<Artist> allArtists() {
        return repo.listAll();
    }

    @GET
    @Path("{id}")
    public Artist getArtistById(@PathParam("id") long id) {
        return repo.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @GET
    @Path("sorted")
    public List<Artist> getArtistsSorted() {
        return repo.listAllArtistsSorted();
    }

    @POST
    @Transactional(Transactional.TxType.REQUIRED)
    public Response persistArtist(Artist a, @Context UriInfo uriInfo) {
        repo.persist(a);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(a.getId()));
        return Response.created(builder.build()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteArtistById(@PathParam("id") long id) {
        repo.deleteById(id);
    }
}
