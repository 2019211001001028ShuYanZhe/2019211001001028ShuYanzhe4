package com.ShuYanzhe.controller;

import com.ShuYanzhe.dao.ProductDao;
import com.ShuYanzhe.model.Category;
import com.ShuYanzhe.model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    private Connection con = null;
    //private static final Logger Log = Logger.getLogger(AddProductServlet.class);

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        con = (Connection)getServletContext().getAttribute("con");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        Double price = request.getParameter("price")!=null?Double.parseDouble(request.getParameter("price")):0.0;
        int categoryId = request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):0;
        String productDescription = request.getParameter("productDescription");
        InputStream inputStream = null;
        Part filePart = request.getPart("picture");
        if(filePart!=null){
            System.out.println("file name :"+filePart.getName()+" size"+filePart.getSize()+"file type"+filePart.getContentType());
            inputStream = filePart.getInputStream();
        }
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setProductDescription(productDescription);
        product.setCategoryId(categoryId);
        product.setPicture(inputStream);

        ProductDao dao = new ProductDao();
        int i = 0;
        try {
            i = dao.save(product,con);
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(i>0){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        List<Category> categoryList = null;
        try {
            categoryList = category.findAllCategory(con);
            request.setAttribute("categoryList",categoryList);
            String path = "/WEB-INF/views/admin/addProduct.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}