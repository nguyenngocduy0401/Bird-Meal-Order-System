/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import sample.dao.BirdDAO;
import sample.dao.CategoriesBirdDAO;
import sample.dao.CategoryDAO;
import sample.dao.ProductDAO;
import sample.dto.BirdDTO;
import sample.dto.CategoryDTO;
import sample.dto.InformationCreateError;
import sample.dto.ProductDTO;

/**
 *
 * @author DucAnh
 */
public class UpdateProductServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException, NamingException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");

            ProductDTO p = new ProductDTO();
            String filename = null;

            String productId;

            String imagepath = null;
            String selectedCategoryId = null;
            //    String filenamecheck = null;
            //    String fileNamenull = "";
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> productList = dao.loadProducts();
            if (action == null || action.equals("")) {
                request.setAttribute("products", productList);
                request.getRequestDispatcher("showProducts.jsp").forward(request, response);
            }
            int productID;
            switch (action) {
                case "edit":
                    productId = request.getParameter("txtProductId");
                    ArrayList<BirdDTO> listBird = new ArrayList<>();
                    if (productId != null && !productId.isEmpty()) {
                        p = dao.getProductByProductID(Integer.parseInt(productId));
                        listBird = BirdDAO.getBirdsByProductID(Integer.parseInt(productId));
                    }
                    request.setAttribute("INFOR", p);
                    request.setAttribute("ACTION", "Save");
                    request.setAttribute("LISTBIRD", listBird);
                    request.getRequestDispatcher("UpdateproductNew").forward(request, response);
                    break;

                case "Save":
                    //  case "edit":

                    //   break;
                    //    case "save":
                    try {
                    // Đặt encoding cho request thành UTF-8
                    request.setCharacterEncoding("UTF-8");

                    // Create a factory for disk-based file items
                    DiskFileItemFactory factory = new DiskFileItemFactory();

                    // Configure a repository (to ensure a secure temp location is used)
                    ServletContext servletContext = this.getServletConfig().getServletContext();
                    File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                    factory.setRepository(repository);

                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);

                    // Parse the request
                    List<FileItem> items = upload.parseRequest(request);
                    InformationCreateError errors = new InformationCreateError();
                    boolean foundError = false;

                    // Process the uploaded items
                    Iterator<FileItem> iter = items.iterator();
                    HashMap<String, String> fileds = new HashMap();
                    List<String> birds = new ArrayList<>();
                    String existingImagePath = null; // Lưu trữ đường dẫn ảnh cũ
                    while (iter.hasNext()) {
                        FileItem item = iter.next();

                        if (item.isFormField()) {
                            // Đặt encoding cho giá trị tiếng Việt

                            fileds.put(item.getFieldName(), item.getString());
                            String name = item.getFieldName();
                            String value = new String(item.getString().getBytes("ISO-8859-1"), "UTF-8");
                            if (name.equals("txtBirds")) {
                                birds.add(value);
                            }

                            switch (name) {
//                            case "productId":
//                                product.setProductId(fieldValue);
//                                System.out.println("Field Name: " + fieldName);
//                                System.out.println("Field Value: " + fieldValue);
//                                break;
                                case "txtProductName":
                                    if (value.trim().length() < 3 || value.trim().length() > 500) {
                                        errors.setProductNameLengthError("Product name is required input from 3 to 500 characters");
                                        foundError = true;
                                    }
                                    break;
                                case "txtPrice":    
                                try {
                                    String price = value.replaceAll(",", "");
                                    double priceDouble = Double.parseDouble(price);
                                    if (priceDouble <= 0) {
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
                                    int quantity = Integer.parseInt(value);
                                } catch (NumberFormatException e) {
                                    foundError = true;
                                    errors.setProductQuantityFormatError("The quantity of product is required input an integer number");
                                }
                                break;
                                case "txtProductDetail":
                                    if (value.trim().length() < 5 || value.trim().length() > 2000) {
                                        errors.setProductDetailLengthError("Product detail is required input from 5 to 2000 characters");
                                        foundError = true;
                                    }
                                    break;
                                case "txtSize":
                                try {
                                    int size = Integer.parseInt(value);
                                    if (size <= 0) {
                                        foundError = true;
                                        errors.setProductSizeFormatError("The size of product is required input an integer number greater than 0");
                                    }
                                } catch (NumberFormatException e) {
                                    foundError = true;
                                    errors.setProductSizeFormatError("The size of product is required input an integer number greater than 0");
                                }
                                break;
                                case "txtAgeRecommendation":
                                try {
                                    int age = Integer.parseInt(value);
                                    if (age <= 0) {
                                        errors.setProductAgeRecommendationLengthError("The age recommendation of product is required input an integer number greater than 0");
                                        foundError = true;
                                    }
                                } catch (NumberFormatException e) {
                                    errors.setProductAgeRecommendationLengthError("The age recommendation of product is required input an integer number greater than 0");
                                    foundError = true;
                                }
                                break;
                                case "txtDate":
                                try {
                                    Integer.parseInt(value);
                                } catch (Exception e) {
                                    errors.setProductDateExpireValueError("Date exprice is required input an integer number");
                                    foundError = true;
                                }
                                //Integer.parseInt(fileds.get("txtAgeRecommendation"))
                                //System.out.println("Date" + fieldValue);
                                break;
                                case "txtCountry":
                                    if (value.trim().length() < 2 || value.trim().length() > 50) {
                                        errors.setProductCountryNotSelect("Country is required input from 2 to 50 characters");
                                        foundError = true;
                                    }
                                    break;
                                case "txtDateManufacture":
                                    if (value.isEmpty()) {
                                        errors.setProductDateManuNotSelect("Please select date manufacture");
                                        foundError = true;
                                    }
                                    break;
                            }
                        } else {
                            String fileName = item.getName();

                            if (fileName != null && !fileName.isEmpty()) {

                                String uploadPath = getServletContext().getRealPath("/") + "images/";
                                filename = item.getName();

                                File file = new File(uploadPath, filename);
                                //   filenamecheck = filename;
                                //    System.out.println("file name" + uploadedFile);

                                // Kiểm tra xem tệp tin đã tồn tại trong thư mục hay không
                                if (file.exists()) {
                                    // Xử lý trường hợp trùng lặp, ví dụ: đổi tên tệp tin
                                    String baseName = FilenameUtils.getBaseName(filename);
                                    String extension = FilenameUtils.getExtension(filename);
                                    int counter = 1;

                                    // Lặp để tạo tên mới cho tệp tin
                                    while (file.exists()) {
                                        filename = baseName + "_" + counter + "." + extension;
                                        file = new File(uploadPath, filename);
                                        counter++;
                                    }
                                }
                                item.write(file);
                                imagepath = "images/" + filename;
                                System.out.println("d  " + filename);
                            }

                        }
                    }

                    if (birds.isEmpty() || birds == null) {
                        errors.setProductCategoriesBirdNotSelect("Please select this field");
                        foundError = true;
                    }
                    if (foundError) {
                        request.setAttribute("UPDATE_PRODUCT_ERROR", errors);
                        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
                        break;
                    }
                    // Lấy giá trị tiếng Việt từ trường dữ liệu "productName"   
                    String productName = new String(fileds.get("txtProductName").getBytes("ISO-8859-1"), "UTF-8");

                    // Lấy giá trị tiếng Việt từ trường dữ liệu "description"
                    String productDetail = new String(fileds.get("txtProductDetail").getBytes("ISO-8859-1"), "UTF-8");
                    String DateManufacture = new String(fileds.get("txtDateManufacture").getBytes("ISO-8859-1"), "UTF-8");

                    productId = fileds.get("txtProductId");
                    if (productId != null && !productId.isEmpty()) {
                        productID = Integer.parseInt(productId);
                        p = dao.getProductByProductID(productID);
                    }
                    if (p == null) {
                        System.out.println("trung");
                    } else {
                        System.out.println("ze");
                        ProductDTO productdto = new ProductDTO();
                        productdto.setProductID(Integer.parseInt(fileds.get("txtProductId")));
                        productdto.setProductName(productName);
                        productdto.setPrice(Double.parseDouble(fileds.get("txtPrice")));
                        productdto.setQuantity(Integer.parseInt(fileds.get("txtQuantity")));
                        productdto.setCategoryID(Integer.parseInt(fileds.get("txtCategory")));
                        productdto.setProductDetail(productDetail);//txtDate
                        productdto.setSize(fileds.get("txtSize"));
                        productdto.setAgeRecommendation(Integer.parseInt(fileds.get("txtAgeRecommendation")));
                        productdto.setDate(Integer.parseInt(fileds.get("txtDate")));
                        productdto.setDateManufacture(DateManufacture);
                        productdto.setStatus(Integer.parseInt(fileds.get("txtStatus")));
                        productdto.setCountry(fileds.get("txtCountry"));

                        if (existingImagePath != null) {
                            // Sử dụng đường dẫn ảnh cũ
                            productdto.setImgPath(existingImagePath);
                        } else {
                            // Sử dụng đường dẫn ảnh mới
                            productdto.setImgPath(imagepath);
                        }
                        //CategoryDTO cateName = CategoryDAO.getCategoryByID(productdto.getCategoryID());

                        boolean a = dao.updateProduct(productdto);
                        System.out.println("luu");

                        CategoriesBirdDAO categoriesBirdDAO = new CategoriesBirdDAO();
                        boolean deleteCategoriesBird = categoriesBirdDAO.deleteCategoriesBird(productdto.getProductID());
                        boolean createCategoriesBird = categoriesBirdDAO.createCategoriesBird(birds, productdto.getProductID());
                        if (a == true && deleteCategoriesBird == true && createCategoriesBird == true) {
                            HttpSession session = request.getSession();
                            session.setAttribute("updateProduct", productdto);
                            listBird = BirdDAO.getBirdsByProductID(productdto.getProductID());
                            CategoryDTO categoryProduct = CategoryDAO.getCategoryByID(productdto.getCategoryID());
                            request.setAttribute("CATEGORY_PRODUCT", categoryProduct);
                            request.setAttribute("LIST_BIRD", listBird);
                        } else {
                                
                        }
                        //request.setAttribute("productDTO", productdto);
                        //request.setAttribute("cateName", cateName.getCategoryName());
                    }

                } catch (Exception e) {
                    out.print("Error uploading file: " + e.getMessage());
                }
                HttpSession session = request.getSession();
                session.setAttribute("INFOR", p);
                request.getRequestDispatcher("updateSuccessful.jsp").forward(request, response);
                break;

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
