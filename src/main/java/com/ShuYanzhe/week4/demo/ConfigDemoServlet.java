package com.ShuYanzhe.week4.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(
        urlPatterns = {"/config"},
        initParams = {
                @WebInitParam(name="name", value = "LiuXingyan"),
                @WebInitParam(name="studentId", value = "2019211001000811"),
        },loadOnStartup = 1
)
public class ConfigDemoServlet extends HttpServlet {
    Connection con=null;
    public void init() throws ServletException {
        //String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //String url="jdbc:sqlserver://localhost;databaseName=userdb";
        //String username="sa";
        //String password="admin123456789";
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=getServletConfig().getInitParameter("name");
        String studentId=getServletConfig().getInitParameter("studentId");
        response.getWriter().println("name:"+name);
        response.getWriter().println("studentId:"+studentId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void destroy(){

    }
}