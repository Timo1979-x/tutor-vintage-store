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
import org.agoncal.quarkus.panache.model.Publisher;
import org.agoncal.quarkus.panache.repository.ArtistRepository;

@Path("api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class PublisherResource {

    @GET
    public List<Publisher> allPublishers() {
        return Publisher.listAll();
    }

    @GET
    @Path("{id}")
    public Publisher getPublisherById(@PathParam("id") long id) {
        return (Publisher) Publisher.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @POST
    @Transactional(Transactional.TxType.REQUIRED)
    public Response persistPublisher(Publisher o, @Context UriInfo uriInfo) {
        Publisher.persist(o);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(o.id));
        return Response.created(builder.build()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional(Transactional.TxType.REQUIRED)
    public void deletePublisherById(@PathParam("id") long id) {
        Publisher.deleteById(id);
    }
}
