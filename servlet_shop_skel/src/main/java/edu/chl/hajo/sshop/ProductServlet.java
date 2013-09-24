/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.sshop;

import edu.chl.hajo.shop.core.IProductCatalogue;
import edu.chl.hajo.shop.core.Product;
import edu.chl.hajo.shop.utils.IEntityContainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Epoxy
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products/*"})
public class ProductServlet extends HttpServlet {
    
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
        PrintWriter out = response.getWriter();
        ContainerNavigator cN;
        String action = request.getParameter("action");
        String view = request.getParameter("view");
        if(request.getSession().getAttribute("containerNavigator")==null){
            //Skapar CN samt lägger till den till sessionen
            out.println("cn måste skapas");
//            this.getServletContext().getAttribute(Keys.SHOP.toString());
            cN = new ContainerNavigator(0, 3, Shop.INSTANCE.getProductCatalogue());
            request.getSession().setAttribute("containerNavigator", cN);
        }
        else{
            out.println("cn finns redan");
            cN = (ContainerNavigator) request.getSession().getAttribute("containerNavigator");
        }
        
        //Actions
        if(action!=null){
            String prodName = null;
            double price = 0;
            double fruitId = 0;
            switch (action) {
                case "addItem":
                    prodName = request.getParameter("name");
                    price = Double.parseDouble(request.getParameter("price"));
                    cN.getRange().add(new Product(prodName, price));
//                    request.getSession().setAttribute("containerNavigator", cN);
                    response.sendRedirect("/servlet_shop/products?view=addProduct");
                    break;
                case "delItem": 
                    fruitId = Double.parseDouble(request.getParameter("fruitId"));
                    Logger.getAnonymousLogger().log(Level.INFO, "fruit ID" + fruitId);
                    int indexOfFruitToRemove = findIndexOfFruit(cN, fruitId);
                    Logger.getAnonymousLogger().log(Level.INFO, "fruit pos" + indexOfFruitToRemove);
                    //TODO Fixa så att sidan visas efter att containern uppdaterats
//                    request.getSession().setAttribute("containerNavigator", cN);
                    //request.getRequestDispatcher("WEB-INF/jsp/products/products.jspx").forward(request, response);
                    if(indexOfFruitToRemove!=-1){
                        cN.getRange().remove(indexOfFruitToRemove);
                    }
                    response.sendRedirect("/servlet_shop/products?view=this");
                    break;
                case "editItem":
                    fruitId = Double.parseDouble(request.getParameter("fruitId"));
                    prodName = request.getParameter("name");
                    price = Double.parseDouble(request.getParameter("price"));
                    int indexOfFruitToEdit = findIndexOfFruit(cN, fruitId);
                    if(indexOfFruitToEdit!=-1){
                        Product p = new Product((long)fruitId, prodName, price);
                        cN.getRange().set(indexOfFruitToEdit, p);
                    }
//                    request.getSession().setAttribute("containerNavigator", cN);
                    response.sendRedirect("/servlet_shop/products?view=this");
                    Logger.getAnonymousLogger().log(Level.INFO, "EDIT ITEM");
                    
                    
            }
        }
        
        //Views
        if(view!=null){
            String fruitId = null;
            //Logger.getAnonymousLogger().log(Level.INFO, "VIEW");
            switch (view){
                case "prev":
                    cN.previous();
                    request.getRequestDispatcher("WEB-INF/jsp/products/products.jspx").forward(request, response);
                    break;
                case "next":
                    request.getSession().setAttribute("PRODUCT_LIST", cN.next());
                    request.getRequestDispatcher("WEB-INF/jsp/products/products.jspx").forward(request, response);
                    break;
                case "this":
                    request.getSession().setAttribute("PRODUCT_LIST", cN.getRange());
                    request.getRequestDispatcher("WEB-INF/jsp/products/products.jspx").forward(request, response);
                    break;
                case "addProduct":
                    request.getRequestDispatcher("WEB-INF/jsp/products/addProduct.jspx").forward(request, response);
                    break;
                case "editProduct":
                    Logger.getAnonymousLogger().log(Level.INFO, "EDIT PROD");
                    fruitId = request.getParameter("id");
                    request.setAttribute("fruitId", fruitId);
                    //id = request.getParameter("id");
                    request.getRequestDispatcher("WEB-INF/jsp/products/editProduct.jspx").forward(request, response);
                    break;
                case "delProduct":
                    Logger.getAnonymousLogger().log(Level.INFO, "DELETE PROD");
                    fruitId = request.getParameter("id");
                    request.setAttribute("fruitId", fruitId);
                    Logger.getAnonymousLogger().log(Level.INFO, "id: ");
                    
                    request.getRequestDispatcher("WEB-INF/jsp/products/delProduct.jspx").forward(request, response);
                    break;
            }
        }
        
    }
    public int findIndexOfFruit(ContainerNavigator cN, double fruitId) {
        List fruits = cN.getRange();
        for(int i=0; i<fruits.size(); i++){
            Product p = (Product)fruits.get(i);
            if(p.getId()==fruitId){
                return i;
            }
        }
        return -1;
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