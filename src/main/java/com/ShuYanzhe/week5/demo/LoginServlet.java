package com.ShuYanzhe.week5.demo;

import com.ShuYanzhe.dao.UserDao;
import com.ShuYanzhe.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name="LoginServlet",value="/login")
public class LoginServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        /*ServletConfig config = getServletConfig();
        String driver = config.getInitParameter("driver");
        String url = config.getInitParameter("url");
        String username = config.getInitParameter("username");
        String password = config.getInitParameter("password");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("init()--> " + con);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();*/
        con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao=new UserDao();
        try {
            User user= userDao.findByUsernamePassword(con,username,password);
            if (user!=null){
                String rememberMe=request.getParameter("rememberMe");
                if (rememberMe!=null && rememberMe.equals("1")){
                    Cookie usernameCookie=new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie=new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie=new Cookie("cRememberMe",rememberMe);
                    usernameCookie.setMaxAge(5);
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }
                HttpSession session= request.getSession();
                System.out.println("session id-->"+session.getId());
                session.setMaxInactiveInterval(10);

                session.setAttribute("user",user);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }else {
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       /* String sql = "select id,username,password,email,gender,birthdate from usertable where username='"+username+"' and password='"+password+"'";
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            if(rs.next()){
                //out.println("Login Successful!!!");
                //out.println("Welcome"+username);
                request.setAttribute("id",rs.getString("id"));
                request.setAttribute("username",rs.getString("username"));
                request.setAttribute("password",rs.getString("password"));
                request.setAttribute("email",rs.getString("email"));
                request.setAttribute("gender",rs.getString("gender"));
                request.setAttribute("birthdate",rs.getString("birthdate"));
                request.getRequestDispatcher("userInfo.jsp").forward(request,response);
            }else {
                //out.println("Username or password Error!!!");
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}