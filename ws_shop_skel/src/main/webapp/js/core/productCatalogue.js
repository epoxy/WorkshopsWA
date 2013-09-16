/***********************************************
 *  
 *  A proxy representing the product service (model)
 *   NOTE: All methods return deferred object, see JQuery
 *  
 */

var ProductCatalogue = function ( baseUri ) {
    this.baseUri = baseUri;
};


ProductCatalogue.prototype = (function () {
    
    return {
      
        find: function( id ){
            return $.getJSON(this.baseUri + "/" + id);
    },
            
        getAll: function() {
             return $.getJSON(this.baseUri);
        },
                
        add: function( product ) {
            return $.ajax({
                type: 'POST',
                datatype: 'json',
                url: this.baseUri,
                data: product
            });
        },
        
        remove: function( id ) {
            return $.ajax({
                type: 'DELETE',
                datatype: 'json',
                url: this.baseUri + "/" + id
            });
        },
        update: function( product ) {
            return $.ajax({
                type: 'PUT',
                datatype: 'json',
                url: this.baseUri + "/" + arguments[2],//product.id
                data: product
            });
        },
        getRange: function( range ) {
            return $.getJSON(this.baseUri + "/range?fst=" + arguments[0] + "&max=" + arguments[1]);
        },
        
        getCount: function(){
            return $.getJSON(this.baseUri + "/count");
        }
    };
    
}());

