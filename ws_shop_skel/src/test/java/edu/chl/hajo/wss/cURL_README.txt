****************************
*
*  Testing JAX-RS resource classes using cURL 
*
*****************************

A Note for cURL:
The text "additional stuff not fine" text was added for debug purposes 
a while ago, but it isn't really helping anyone and for some reason some 
Linux distributions provide their libcurls built with debug info still 
present and thus (far too many) users get to read this info.
So, this is basically harmless,


Testing the ProductCatalogueResource from command line (class @Path("products"))
------------------------------------------------------------
# *** GET *** (read) NOTE: 8080 is for GlassFish
curl -v http://localhost:8080/ws_shop_skel/rs/products  

# Probably need another id last!
curl -v http://localhost:8080/ws_shop_skel/rs/products/1

# Content negotiation (conneg) asking for JSON  (will deliver an array of objects)
curl -v -H "Accept: application/json" http://localhost:8080/ws_shop_skel/rs/products
# Will deliver single object
curl -v -H "Accept: application/json" http://localhost:8080/ws_shop_skel/rs/products/1

# Query parameters. NOTE: Must quote URI. Will get list (with possible 0 elements)
curl -v -H "Accept: application/json" "http://localhost:8080/ws_shop_skel/rs/products/range?fst=0&max=2"

# Get primitive type
curl -v http://localhost:8080/ws_shop_skel/rs/products/count
curl -v -H "Accept: application/json" http://localhost:8080/ws_shop_skel/rs/products/count

# *** POST (create) ***
curl -v -X POST http://localhost:8080/ws_shop_skel/rs/products --data "name=test&price=99.99"

# *** PUT (update) ***
# Sending JSON, probably need other id last
curl -v -X PUT "Content-Type: application/json" http://localhost:8080/ws_shop_skel/rs/products/1 --data "name=updated&price=999"

# *** DELETE (delete) ***
curl -v -X DELETE http://localhost:8080/ws_shop_skel/rs/products/11 



