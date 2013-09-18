/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.wss;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.Product;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author tomassellden
 */
@Path("products")
public class ProductCatalogueResource {

    private final static Shop shop = Shop.INSTANCE;
    private IProductCatalogue container = shop.getProductCatalogue();
    // Helper class used to build URI's. Injected by container 
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<Product> tmpList = container.getRange(0, container.getCount());
        List<ProductProxy> productList = new ArrayList<>();
        for (Product p : tmpList) {
            productList.add(new ProductProxy(p));
        }
        //is this really important?
        GenericEntity<List<ProductProxy>> ge = new GenericEntity<List<ProductProxy>>(productList) {
        };
        return Response.ok(ge).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response add(@FormParam("name") String name,
            @FormParam("price") double price) {
        Product p = new Product(name, price);
        try {
            container.add(p);
            URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf("name")).build(p);
            return Response.created(uri).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("{Id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("Id") Long Id) {
        try {
            container.remove(Id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id,
            @FormParam("name") String name, @FormParam("price") double price) {
        try {
            //Product p = container.find(id);
            container.update(new Product(id, name, price));
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
        Product tmpproduct = container.find(id);
        if( tmpproduct != null) {
            ProductProxy product = new ProductProxy(tmpproduct);
            return Response.ok(product).build();
        } else {
            return Response.noContent().build();
            
        }
    }
    
    @GET
    @Path("range")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getRange(@QueryParam("fst") int first, @QueryParam("max") int nItems) {
        Logger.getAnonymousLogger().log(Level.INFO, "getRange first= " + first + " nItems= " + nItems);
        
        List<Product> tmpProduct = container.getRange(first, nItems);
        List<ProductProxy> productList = new ArrayList<>();
        for ( Product p : tmpProduct) {
            productList.add(new ProductProxy(p));
        }
         GenericEntity<List<ProductProxy>> ge = new GenericEntity<List<ProductProxy>>(productList) {
        };
        return Response.ok(ge).build();
    }
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCount() {
        
        Integer i = new Integer(container.getCount());
        PrimitiveJSONWrapper<Integer> p = new PrimitiveJSONWrapper<>(i);
        return Response.ok(p).build();
        
    }
    
}
