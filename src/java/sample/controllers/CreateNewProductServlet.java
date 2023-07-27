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
import java.util.ArrayList;
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
import sample.dao.BirdDAO;
import sample.dao.CategoriesBirdDAO;
import sample.dao.ProductDAO;
import sample.dto.BirdDTO;
import sample.dto.InformationCreateError;
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

//            String existingImagePath = null;
//            String imagepath = null;
            InformationCreateError errors = new InformationCreateError();

            try {
                // Phân tích các trường dữ liệu và tệp tin đính kèm từ request
                //List<FileItem> items = (List<FileItem>) fileUpload.getItemIterator(request);
                List<FileItem> items = fileUpload.parseRequest(request);
                List<String> birds = new ArrayList<>();
                // Tạo đối tượng ProductDTO để lưu trữ thông tin sản phẩm
                ProductDTO product = new ProductDTO();
                boolean foundError = false;
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
                                if (fieldValue.trim().length() < 3 || fieldValue.trim().length() > 500) {
                                    errors.setProductNameLengthError("Product name is required input from 3 to 500 characters");
                                    foundError = true;
                                } else {
                                    product.setProductName(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                }
                                break;
                            case "txtPrice":    
                                try {
                                String price = fieldValue.replaceAll(",", "");
                                product.setPrice(Double.parseDouble(price));
                                if (product.getPrice() <= 0) {
                                    foundError = true;
                                    errors.setProductPriceFormatError("Price is required input number only and greater than 0");
                                }
                            } catch (NumberFormatException e) {
                                foundError = true;
                                errors.setProductPriceFormatError("Price is required input number only and greater than 0");
                            }
                            break;
                            case "txtQuantity":
                                try {
                                product.setQuantity(Integer.parseInt(fieldValue));
                            } catch (NumberFormatException e) {
                                foundError = true;
                                errors.setProductQuantityFormatError("The quantity of product is required input an integer number");
                            }
                            break;
                            case "txtCatory":
                                product.setCategoryID(Integer.parseInt(fieldValue));
//                                System.out.println("Field Nameg: " + fieldName);
//                                System.out.println("Field Valueg: " + fieldValue);
                                break;
                            case "txtProductDetail":
                                if (fieldValue.trim().length() < 5 || fieldValue.trim().length() > 2000) {
                                    errors.setProductDetailLengthError("Product detail is required input from 5 to 2000 characters");
                                    foundError = true;
                                } else {
                                    product.setProductDetail(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                }
                                break;
                            case "txtSize":
                                try {
                                int size = Integer.parseInt(fieldValue);
                                if (size <= 0) {
                                    foundError = true;
                                    errors.setProductSizeFormatError("The size of product is required input an integer number greater than 0");
                                }
                                product.setSize(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                            } catch (NumberFormatException e) {
                                foundError = true;
                                errors.setProductSizeFormatError("The size of product is required input an integer number greater than 0");
                            }
                            break;
                            case "txtBirds":
                                birds.add(fieldValue);
                                break;
                            case "txtAgeRecommendation":
                                product.setAgeRecommendation(Integer.parseInt(fieldValue));
                                break;
                            case "txtDate":
                                try {
                                product.setDate(Integer.parseInt(fieldValue));
                            } catch (Exception e) {
                                errors.setProductDateExpireValueError("Date exprice is required input an integer number");
                                foundError = true;
                            }
                            //Integer.parseInt(fileds.get("txtAgeRecommendation"))
                            //System.out.println("Date" + fieldValue);
                            break;
                            case "txtStatus":
                                product.setStatus(Integer.parseInt(fieldValue));
                                //System.out.println("status" + fieldValue);
                                break;
                            case "txtCountry":
                                if (fieldValue.trim().length() < 2 || fieldValue.trim().length() > 50) {
                                    errors.setProductCountryNotSelect("Country is required input from 2 to 50 characters");
                                    foundError = true;
                                } else {
                                    product.setCountry(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                }
                                break;
                            case "txtDateManufacture":
                                if (fieldValue.isEmpty()) {
                                    errors.setProductDateManuNotSelect("Please select date manufacture");
                                    foundError = true;
                                } else {
                                    product.setDateManufacture(new String(fieldValue.getBytes("ISO-8859-1"), "UTF-8"));
                                }
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
                if (birds.isEmpty() || birds == null) {
                    errors.setProductCategoriesBirdNotSelect("Please select this field");
                    foundError = true;
                }
                if (foundError) {
                    request.setAttribute("CREATE_PRODUCT_ERROR", errors);
                    request.getRequestDispatcher("MainController?btAction=Create+New+Product").forward(request, response);
                }
                // Gọi phương thức DAO để tạo sản phẩm
                ProductDAO productDAO = new ProductDAO();
                int productID = productDAO.createProduct(product);

                // Create Categories Bird
                CategoriesBirdDAO categoriesBirdDAO = new CategoriesBirdDAO();
                boolean createCategoriesBird = categoriesBirdDAO.createCategoriesBird(birds, productID);
                ArrayList<BirdDTO> listBird = new ArrayList<>();
                listBird = BirdDAO.getBirdsByProductID(productID);
                // Lấy danh sách sản phẩm đã cập nhật
                List<ProductDTO> productList = productDAO.loadProducts();
                //List<CategoryDTO> categoryList = categoryDAO.getCatetoryList();
                // Đặt productList là một thuộc tính trong session
                HttpSession session = request.getSession();
                session.setAttribute("products", productList);
                // Đặt thông tin sản phẩm vừa tạo thành một thuộc tính riêng trong đối tượng yêu cầu (request)
                request.setAttribute("createdProduct", product);
                request.setAttribute("LIST_BIRD", listBird);
                //request.setAttribute("categoryname", categoryList);
                // Chuyển tiếp yêu cầu đến trang index.jsp
                request.getRequestDispatcher("newProduct.jsp").forward(request, response);

                // ...
            } catch (FileUploadException e) {
                log("CreateNewProductServlet_FileUploadException" + e.getMessage());
            } catch (Exception e) {
                log("CreateNewProductServlet_Exception" + e.getMessage());
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
