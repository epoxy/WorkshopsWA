/* 
 * Test of ProductCatalogue
 */

// Possible test with curl first 
asyncTest("ProductCatalogue.count", function() {
    var deferred = shop.getProductCatalogue().getCount();
    deferred.done(function(nProducts) {
        ok(nProducts.value === 6, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});

asyncTest("ProductCatalogue.getRange", function() {
    var deferred = shop.getProductCatalogue().getRange(1, 3);
    deferred.done(function(products) {
        ok(products.length === 3, "Test passed");
        //ok( true, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});


asyncTest("ProductCatalogue.find", function() {
    var deferred = shop.getProductCatalogue().find(1);
    deferred.done(function(product) {
        ok(product.id === 1, "Test passed");
        start();
    });
    deferred.fail(function() {
        ok(false, "Test failed (is Server up??)");
        start();
    });
});


