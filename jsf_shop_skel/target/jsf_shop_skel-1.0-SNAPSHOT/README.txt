    

*** README ***

This JSF workshop uses CDI **only** (no JSF Managed Beans), Bean validation
and runs on 
- GlassFish (ok)
- TomEE (some issues with error messages)
- Tomcat, hard to get bean validation to work and more

No Ajax
----
No AJAX in pure JSF sample ( /jsf-folder) 


Injection
--------
Always prefer setter (testing possible)
Constructor injection often impossible need default ctor.

Realms
-------
Create a user in the (default) file realm in GlassFish

Starts GlassFish Admin console (Servers > GlassFish, right click > 
View Domain Admin Console)

Goto: server-config > Security > Realms > File > manage users 
> New ...
