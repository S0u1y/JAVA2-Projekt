package com.example.client.ClientEndPoints;

import com.example.client.Page;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

public interface PageClient {

    @GET
    @Path("page/{document_id}/{page_num}")
    @Produces(MediaType.APPLICATION_JSON)
    Page getPage(@PathParam("document_id") Long document_id, @PathParam("page_num") int page_num);

    @GET
    @Path("/pagenum/{document_id}")
    @Produces(MediaType.TEXT_PLAIN)
    int getNumberOfPages(@PathParam("document_id") Long id);

}
