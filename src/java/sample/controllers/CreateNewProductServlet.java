/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import sample.dao.CategoryDAO;
import sample.dao.ProductDAO;
import sample.dto.CategoryDTO;
import sample.dto.ProductDTO;

/**
 *
 * @author DucAnh
 */
@MultipartConfig
public class CreateNewProductServlet extends HttpServlet {

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
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        if (ServletFileUpload.isMultipartContent(request)) {
            // Khởi tạo đối tượng ServletFileUpload và cấu hình thư mục lưu trữ tạm thời
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(factory);

            String existingImagePath = null;
            String imagepath = null;

            try {
                // Phân tích các trường dữ liệu và tệp tin đính kèm từ request
                //List<FileItem> items = (List<FileItem>) fileUpload.getItemIterator(request);
                List<FileItem> items = fileUpload.parseRequest(request);
                // Tạo đối tượng ProductDTO để lưu trữ thông tin sản phẩm
                ProductDTO product = new ProductDTO();

                // Lặp qua các trường dữ liệu và xử lý tương ứng
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // Xử lý các trường thông thường
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();
                        System.out.println("Field Name: " + fieldName);
                        System.out.println("Field Value: " + fieldValue);

                        switch (fieldName) {
//                            case "productId":
//                                product.setProductId(fieldValue);
//                                System.out.println("Field Name: " + fieldName);
//                                System.out.println("Field Value: " + fieldValue);
//                                break;
                            case "txtProductName":
                                product.setProductName(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                break;
                            case "txtPrice":
                                product.setPrice(Double.parseDouble(fieldValue));
                                break;
                            case "txtQuantity":
                                product.setQuantity(Integer.parseInt(fieldValue));
                                break;
                            case "txtCatory":
                                product.setCategoryID(Integer.parseInt(fieldValue));
//                                System.out.println("Field Nameg: " + fieldName);
//                                System.out.println("Field Valueg: " + fieldValue);
                                break;
                            case "txtProductDetail":
                                product.setProductDetail(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                break;
                            case "txtSize":
                                product.setSize(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                break;
                            case "txtAgeRecommendation":
                                product.setAgeRecommendation(Integer.parseInt(fieldValue));
                                break;
                            case "txtDate":
                                product.setDate(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                //System.out.println("Date" + fieldValue);
                                break;
                            case "txtStatus":
                                product.setStatus(Integer.parseInt(fieldValue));
                                //System.out.println("status" + fieldValue);
                                break;
                            case "txtCountry":
                                product.setCountry(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                break;
                        }
                    } else {
                        // Xử lý trường hợp tạo sản phẩm với ảnh đính kèm
                        String fileName = item.getName();
                        if (fileName != null && !fileName.isEmpty()) {
                            String uploadPath = getServletContext().getRealPath("/") + "images/";

                            File file = new File(uploadPath, fileName);

                            // Kiểm tra xem thư mục images đã tồn tại chưa, nếu chưa thì tạo mới
                            File directory = new File(uploadPath);
                            if (!directory.exists()) {
                                directory.mkdir();
                            }

                            try (InputStream input = item.getInputStream()) {
                                // Lưu ảnh vào thư mục images
                                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            product.setImgPath("images/" + fileName);
                            System.out.println("d  " + fileName);
                        }
                    }
                }

                // Gọi phương thức DAO để tạo sản phẩm
                ProductDAO productDAO = new ProductDAO();
                CategoryDAO categoryDAO = new CategoryDAO();
                boolean success = productDAO.createProduct(product);

                // Lấy danh sách sản phẩm đã cập nhật
                List<ProductDTO> productList = productDAO.loadProducts();
                //List<CategoryDTO> categoryList = categoryDAO.getCatetoryList();
                // Đặt productList là một thuộc tính trong session
                HttpSession session = request.getSession();
                session.setAttribute("products", productList);
                // Đặt thông tin sản phẩm vừa tạo thành một thuộc tính riêng trong đối tượng yêu cầu (request)
                session.setAttribute("createdProduct", product);
                //request.setAttribute("categoryname", categoryList);
                // Chuyển tiếp yêu cầu đến trang index.jsp
                request.getRequestDispatcher("newProduct.jsp").forward(request, response);

                // ...
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
