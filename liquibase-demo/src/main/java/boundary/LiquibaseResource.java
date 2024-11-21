package boundary;

import control.LiquibaseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Path("liquibase")
public class LiquibaseResource {
    @Inject
    LiquibaseService liquibaseService;

    @GET
    @Path("/update")
    public String updateDatabase() {
        liquibaseService.updateDatabase();
        return "Database updated successfully!";
    }

    @GET
    @Path("/rollback")
    public String rollbackDatabase(@QueryParam("tag") String tag) {
        liquibaseService.rollbackDatabase(tag);
        return "Database rolled back to tag: " + tag;
    }

    @GET
    @Path("/tag")
    public String tagDatabase(@QueryParam("tag") String tag) {
        liquibaseService.tagDatabase(tag);
        return "Database tagged with: " + tag;
    }

    @GET
    @Path("/rollbackToCount/{id}")
    public String rollbackToCount(@PathParam("id") int count) {
        liquibaseService.rollbackToCount(count);
        return "Database rolled back to count: " + count;
    }
}
