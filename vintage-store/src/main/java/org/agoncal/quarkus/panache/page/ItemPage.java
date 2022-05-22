package org.agoncal.quarkus.panache.page;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.CD;

@Path("/page/items")
@Produces(MediaType.TEXT_HTML)
public class ItemPage {

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance book(Book book);

        public static native TemplateInstance books(List<Book> books);

        public static native TemplateInstance cd(CD cd);

        public static native TemplateInstance cds(List<CD> cds);
    }

    @GET
    @Path("books/{id}")
    public TemplateInstance bookById(@PathParam("id") long id) {
        return Templates.book(Book.findById(id))
            .data("query", "")
            .data("pageIndex", "")
            .data("sort", "")
            .data("pageSize", "")
            ;
    }

    @GET
    @Path("books")
    public TemplateInstance allBooks(
        @QueryParam("query") String query,
        @QueryParam("sort") @DefaultValue("id") String sort,
        @QueryParam("page") @DefaultValue("0") Integer pageIndex,
        @QueryParam("size") @DefaultValue("1000") Integer pageSize
    ) {
        // return Templates.books(Book.listAll());
        return Templates.books(Book.find(query, Sort.by(sort)).page(pageIndex, pageSize).list()).
            data("query", query)
            .data("sort", sort)
            .data("pageIndex", pageIndex)
            .data("pageSize", pageSize)
            ;
    }

    @GET
    @Path("cds/{id}")
    public TemplateInstance cdById(@PathParam("id") long id) {
        return Templates.cd(CD.findById(id));
    }

    @GET
    @Path("cds")
    public TemplateInstance allCDs() {
        return Templates.cds(CD.listAll());
    }
}
