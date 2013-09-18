/* 
 * The shop as a Singleton
 */
// Global
var shop = (function(){
    
    var baseUri = "http://localhost:8080/ws_shop_skel/rs/";  
    var products = new ProductCatalogue(baseUri + "products");
    //var orderBook = new OrderBook(baseUri);
    // etc ...
    
    return {
        getProductCatalogue : function(){
            return products;
        },
        getBaseUri : function(){
            return baseUri;
        }
    };    
})();

