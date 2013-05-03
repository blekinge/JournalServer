package dk.statsbiblioteket.journalserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: abr
 * Date: 5/3/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */

@Path("/")
public class JournalServer {

    private SingletonJournal queue;

    public JournalServer() throws IOException {
        queue = SingletonJournal.getInstance();
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String put(String payload) throws IOException {
        return queue.put(payload);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String get(
            @PathParam("id")
            String id) throws IOException {
        return  queue.get(id);
    }
}
