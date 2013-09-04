*** README ***

This is run on Tomcat using "deploy on save" to speed
up development cycle.
    
Includes
--------
Not diff. between static includes <jsp:directive.include> 
and dynamic <jsp:include>, check generated servlet
to see (right click in jspx > View Servlet)

Authorization
-------------
This is mostly to use a filter and the session object.
Recommended is declarative security (in web.xml).
But then we need to create a realm for the server
etc... to much for now.

Links
-----
Note: We mostly use links (just a few submit buttons that's all) 