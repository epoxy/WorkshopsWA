
*** README ****

This is run using GlassFish (don't run any other
application especially NOT some Athmosphere thing...)

Directory layout for web parts
------------------------
- content, dynamically replaced content (html) for index.page
- js, all applicaton specific JavaScript
- js/core, model (proxies)
- js/ctrl, control part for MVC not used
- js/gui, page specific scripts (products.html uses products.js)
- js/test, test for application specific JavaScript
- lib, JavaScript libraries
- resources, css and images

Dependencies
------------
<dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-json</artifactId>
            <version>1.17.1</version>
        </dependency>
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-server</artifactId>
            <version>1.17.1</version>
        </dependency>
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-servlet</artifactId>
            <version>1.17.1</version>
        </dependency>

HTML5 vs XHTML5
----------------
Use HTML5 (else problems with lib's ...) but
write valid XML = polyglot!

Summary: We use html files with polyglot markup

Testing
-------
Testing WS using cURL, see test folder
Also using Apache HttpClient, see test folder
Seems possible to test from inside NetBeans (click RESTful Web Services)

Debugging dynamic JS in Chrome
-----------------------------
Must have (last ) in file 

//@ sourceURL=products.js

Else no debugging of dynamically loaded script in Chrome, see
products.js

Instances
---------
NOTE: Only unique top level resources @Path(/products)?
Spec. doesn't seem to require?


Complete furious frustration
---------------------------
How to remove a RESTful Web Service??????
Impossible, NetBeans will scan war file
(so really have to remove from code, possible
delete target directory)
 
