package com.example.client.ClientEndPoints;

import com.example.client.Document;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

public interface DocumentClient {

    @GET
    @Path("documents")
    @Produces(MediaType.APPLICATION_JSON)
    List<Document> getDocuments();

    @POST
    @Path("/document")
    @Consumes(MediaType.APPLICATION_JSON)
    Long createDocument(Document document);

}
