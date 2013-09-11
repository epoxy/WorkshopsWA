/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.sshop;

import edu.chl.hajo.shop.core.Product;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tomassellden
 * har skrivit lite kommentarer pa daliga kod snitt som jag gjort fick den godkand
 * men det ar inte riktigt bra :)!
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
public class ProductServlet extends HttpServlet {

    private String id = "";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("param");
        String view = request.getParameter("view");
        String action = request.getParameter("action");


        HttpSession session = request.getSession(true);
        ContainerNavigator navigator = new ContainerNavigator(0, 20, Shop.INSTANCE.getProductCatalogue());
        //ContainerNavigator tmp = (ContainerNavigator) request.getSession().getAttribute("navigator");
        if (view != null) {

            if (view.equals("products")) {
                if (session.getAttribute("nav") == null) {
                    session.setAttribute("nav", navigator);
                }
                request.setAttribute("PRODUCT_LIST", nextAndPrevLinks(navigator, param));
                request.getRequestDispatcher("WEB-INF/jsp/products/products.jspx").forward(request, response);
            }
            if (view.equals("addProduct")) {
                request.getRequestDispatcher("WEB-INF/jsp/products/addProduct.jspx").forward(request, response);
            }
            if (view.equals("editProduct")) {
                id = request.getParameter("id");
                String name = request.getParameter("name");
                request.setAttribute("id", id);
                request.setAttribute("name", name);
                request.getRequestDispatcher("WEB-INF/jsp/products/editProduct.jspx").forward(request, response);
            }
            if (view.equals("delProduct")) {
                id = request.getParameter("id");
                String name = request.getParameter("name");
                request.setAttribute("id", id);
                request.setAttribute("name", name);
                request.getRequestDispatcher("WEB-INF/jsp/products/delProduct.jspx").forward(request, response);
            }


        }
        if (action != null) {
            if (action.equals("add")) {
                String name = request.getParameter("name");
                String price = request.getParameter("price");
                Product newProduct = new Product(name, Double.parseDouble(price));
                navigator.next().add(newProduct);
                request.setAttribute("PRODUCT_LIST", nextAndPrevLinks(navigator, param));
                response.sendRedirect("/servlet_shop_skel/products?view=addProduct");
                
            }

            if (action.equals("edit")) {
                String name = request.getParameter("name");
                String price = request.getParameter("price");
                int index = removeProduct(navigator, id);

                if (index != -1) {
                    navigator.getRange().remove(index);
                }
                /*
                 * onadig metod battre att anvanda update ska finnas i abstractEntity!
                 * jag tar bort produkten och darefter lagger till en ny produkt!
                 */
                Product abE = new Product(Long.parseLong(id), name, Double.parseDouble(price));
                navigator.next().add(abE);
                 /*
                 * ocksa daligt att adda den direkt till navigator battre att 
                 * lagga till den i shop.INSTANCE.productcatalogue!
                 */
                request.setAttribute("PRODUCT_LIST", nextAndPrevLinks(navigator, param));
                request.getRequestDispatcher("WEB-INF/jsp/products/products.jspx").forward(request, response);
            }
            if (action.equals("del")) {
                int index = removeProduct(navigator, id);
                if (index != -1) {
                    navigator.getRange().remove(index);
                }
                request.setAttribute("PRODUCT_LIST", nextAndPrevLinks(navigator, param));
                request.getRequestDispatcher("WEB-INF/jsp/products/products.jspx").forward(request, response);
            }
        }
    }

    public int removeProduct(ContainerNavigator navigator, String id) {
        for (int i = 0; i < navigator.getRange().size(); i++) {
            Product p = (Product) navigator.getRange().get(i);
            if (p.getId() == Long.parseLong(id)) {
                return i;
            }
        }
        return -1;
    }
    private int endList;
    private int startList;

    public List<Product> nextAndPrevLinks(ContainerNavigator navigator, String param) {
      /*int nbrElemenet = navigator.getRange().size();
        if (param == null) {
            return navigator.previous();
        } else if (param.equals("prev")){
            return navigator.previous();
        } else {
            return navigator.next();
        }*/
        /*
         * to Anton
         * detta ar dalig kod, du ska kunna anvanda dig utav next och prev 
         * istallet!
         */
       int lastElement = navigator.getRange().size();
        if (param == null) {
            startList = 0;
            endList = 3;
            return navigator.getRange().subList(startList, endList);
        }
        if (param.equals("prev")) {
            if (startList == 0) {
                return navigator.getRange().subList(startList, endList);
            }
            if (startList < 3) {
                endList = startList;
                startList = 0;
                return navigator.getRange().subList(startList, endList);
            } else if ((endList - startList) < 3){
                endList = startList;
                startList -= 3;
                return navigator.getRange().subList(startList, endList);
            } else {
                startList -=3;
                endList -=3;
                return navigator.getRange().subList(startList, endList);
            }
        }
        if (param.equals("next")) {
            if (endList == lastElement) {
                return navigator.getRange().subList(startList, endList);
            }
            if (lastElement < (endList + 3)) {
                startList = endList;
                endList = navigator.getRange().size();
                
                return navigator.getRange().subList(startList, endList);
            } else {
                startList += 3;
                endList += 3;
                return navigator.getRange().subList(startList, endList);
            }
        }
       return null;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
