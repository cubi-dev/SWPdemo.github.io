/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp391.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import swp391.login.LoginError;
import swp391.customer.CustomerDAO;
import swp391.customer.CustomerDTO;
import swp391.utils.MyApplicationConstants;

/**
 *
 * @author Chau Nhat Truong
 */
public class LoginServlet extends HttpServlet {

//    private final String INVALID_PAGE = "invalid.html";
//    private final String SEARCH_PAGE = "search.html";
//    private final String SEARCH_PAGE = "searchPage";
//    private final String SEARCH_PAGE = "search.jsp";
//    private final String ERROR_PAGE = "error.html";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
//        String url = INVALID_PAGE;

        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        String url = siteMaps.getProperty(
                MyApplicationConstants.LoginServlet.LOGIN_PAGE);
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        boolean errorFound = false;
        LoginError errors = new LoginError();
        try {
            //1. Check all user error
            if (email.trim().length() < 1) {
                errorFound = true;
                errors.setEmailLengthError("You can't leave this empty");
            }
            if (password.trim().length() < 1) {
                errorFound = true;
                errors.setPasswordLengthError("You can't leave this empty");
            }
            if (errorFound) {
                //1.1 catch error
                request.setAttribute("LOGIN_ERROR", errors);
                //1.2 transfer to inform users
            } else {
                //Call Model/DAO ==> new object & call method of that object
                CustomerDAO dao = new CustomerDAO();
//            boolean result = dao.checkLogin(email, password);
                CustomerDTO result = dao.checkLogin(email, password);
                if (result == null) {
                    errorFound = true;
                    errors.setLoginFail("Incorrect email or password");
                }
                if (errorFound) {
                    //1.1 catch error
                    request.setAttribute("LOGIN_ERROR", errors);
                    //1.2 transfer to inform users
                } else {
//                url = SEARCH_PAGE;
                    url = siteMaps.getProperty(
                            MyApplicationConstants.LoginServlet.LOGIN_PAGE);

                    HttpSession session = request.getSession();
                    session.setAttribute("USER", result);
                    Cookie cookie = new Cookie(email, password);
                    cookie.setMaxAge(60 * 3);
                    response.addCookie(cookie);
                }
            }//end user is existed
        } catch (NamingException ex) {
//            ex.printStackTrace();
            log("LoginServlet _ Naming _ " + ex.getMessage());
        } catch (SQLException ex) {
//            ex.printStackTrace();
            log("LoginServlet _ SQL _ " + ex.getMessage());
        } finally {
//            response.sendRedirect(url); //Sửa lại send redirect nó mới giữ cái welcome
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
