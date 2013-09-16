/*
 * All js for the products.html page 
 * 
 * NOTE: Last in file a comment to enable debugging
 * because this is dynamically loaded (doesn't work by default to debug
 * dynamic scripts in Chrome at least...)
 * 
 */
// Run after DOM constructed (same as $(document).ready())
$(function() {

    var nav = new Navigator(shop.getProductCatalogue());
    nav.prev(createTable, fail);
    function fail() {
        createErrorDialog("Can't list!!!").dialog("open")
    }
    /*************************************
     * 
     * Components (from JQueryUI) and eventhandling
     */
    $("#next-button")
            .button()
            .click(function() {
        nav.next(createTable, fail);
        function fail() {
            createErrorDialog("Can't list!!").dialog("open")
        }
    });

    $("#prev-button")
            .button()
            .click(function() {
            nav.prev(createTable, fail);
            function fail() {
                createErrorDialog("Can't list!!!").dialog("open")
            }
    });


    $("#add-product")
            .button()
            .click(function() {

    });

    
    /**********************************************
     *   
     *   Functions for redering tables, dialogs and helper functions
     */
    function createTable(products) {
        $('#products tbody tr').remove();
       // var table = "";
        for (i = 0; i < products.length; i++) {
            $('#products').append("<tr class=" + products[i].id + ">" +
             "<td>" + products[i].id + "</td><td>" + 
                    products[i].name + "</td><td>" + 
                    products[i].price + "</td></tr>");
            
        }
        $('#products').dblClick("dblClick", createEditDeleteDialog(products));
    }

    function createAddDialog() {
        // Use JQueryUI dialog
        var myDialog = $("#dialog-form").dialog({
            autoOpen: false,
            modal: true,
             buttons: {
                Ok: function() {
                    $(this).dialog("close");
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            }
            
            
        });
        myDialog.dialog("open");
    }

    // Possible to both edit and delet from same dialog
    function createEditDeleteDialog(product) {
        alert(product[0].id);
    }

    // If delete in above dialog, have to confirm.
    function createConfirmDeleteDialog(id) {
         // Use JQueryUI dialog
    }

    function createErrorDialog(message) {
         // Using JQueryUI dialog
        $("#dialog-message").dialog({
            autoOpen: false,
            modal: true,
            stack: true,
            buttons: {
                Ok: function() {
                    $(this).dialog("close");
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            }
        });
        $('#dialog-message').dialog('option', 'title', 'Something went! wrong');
        $("#dialog-message #msg").text(message);
        return $('#dialog-message');
    }

    function validate(product) {
        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/; // A number
        if (!regex.test(product.price)) {
            $("#dialog-form #price").addClass("ui-state-error");
            return false;
        }
        return true;
    }

    function clearValidationErrors() {
        $("#dialog-form input").each(function() {
            $(this).removeClass("ui-state-error");
        });
    }

    function getFormDialogData() {
        var product = {};
        product.id = $("#dialog-form #id").val();
        product.name = $("#dialog-form #pname").val();
        product.price = $("#dialog-form #price").val();
        return product;
    }

    function setFormDialogData(product) {
        $("#dialog-form #id").val(product.id);
        $("#dialog-form #pname").val(product.name);
        $("#dialog-form #price").val(product.price);
    }

    function clearFormDialogData() {
        $("#dialog-form #id").val("");
        $("#dialog-form #pname").val("");
        $("#dialog-form #price").val("");
    }

});

// This is neede to debug dynamically downloaded JS in Chrome
//@ sourceURL=products.js
