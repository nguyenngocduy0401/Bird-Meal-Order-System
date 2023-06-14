/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import sample.dto.OrderDetailGuestDTO;
import sample.dto.ProductDTO;
/**
 *
 * @author DucAnh
 */
public class CartObj implements Serializable {

    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String productID)
            throws SQLException, NamingException, ClassNotFoundException {
        //1. Checking items has existed
        if (productID == null) {
            return;
        }

        if (productID.trim().isEmpty()) {
            return;
        }

        if (this.items == null) {
            this.items = new HashMap<>();
        }

        //2. Checking item exited in items
        int id = Integer.parseInt(productID);
        int quantity = 1;
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductByProductID(id);

        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + 1;
        }

        //3. Update items
        this.items.put(dto, quantity);
    }


    public int getQuantityBySKU(String SKU) {
        if (SKU == null || SKU.trim().isEmpty()) {
            return 0;
        }

        if (this.items == null) {
            return 0;
        }

        int quantity = 0;

        for (ProductDTO dto : this.items.keySet()) {
            if (SKU.equals(dto.getProductID())) {
                quantity = this.items.get(dto);
                return quantity;
            }
        }
        return 0;
    }

    public void removeItemByProductID(int productID)
            throws SQLException, NamingException, ClassNotFoundException {
        //1. checking items has existed
        if (this.items == null) {
            return;
        }
        //2. checking item existed in cart
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductByProductID(productID);
        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public void removeItemsForCheckOut(ProductDTO dto) {
        if (this.items == null) {
            return;
        }

        if (this.items.containsKey(dto)) {
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public Map<ProductDTO, Integer> showCheckedItems(String[] ProductID)
            throws SQLException, NamingException, ClassNotFoundException {
        if (this.items == null) {
            return null;
        }

        Map<ProductDTO, Integer> list = new HashMap<>();
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = new ProductDTO();
        for (String productID : ProductID) {
            int id = Integer.parseInt(productID);
            dto = dao.getProductByProductID(id);
            list.put(dto, items.get(dto));
        }
        return list;
    }

    public int checkOutItemsOfCart(String fullName, String phoneNumber, String shippingDate, String address, int status,String total,
            Map<ProductDTO, Integer> checkedItems)
            throws SQLException, NamingException, ClassNotFoundException {
        if (this.items == null) {
            return -1;
        }

        OrderGuesDAO ordersGuestDAO = new OrderGuesDAO();
        int orderID = ordersGuestDAO.createNewOrder(fullName, phoneNumber, shippingDate, status, address, total);

        if (orderID > 0) {
            OrderDetailGuestDAO orderDetailsGuestDAO = new OrderDetailGuestDAO();
            boolean result
                    = orderDetailsGuestDAO.createOrderDetails(orderID, checkedItems);
            if (result) {
                return orderID;
            }
        }
        return -1;
    }

    public List<OrderDetailGuestDTO> addItemsToOrderDetailsDTO
        (Map<ProductDTO, Integer> checkedItems, int orderID) {
        List<OrderDetailGuestDTO> list = new ArrayList<>();

        for (ProductDTO productDTO : checkedItems.keySet()) {
            int productID = productDTO.getProductID();
            int quantity = checkedItems.get(productDTO);
            double price = productDTO.getPrice();

            double total;
            total = quantity * price;

            OrderDetailGuestDTO orderDetailsGuestDTO
                    = new OrderDetailGuestDTO(orderID, productID, quantity, price);

            list.add(orderDetailsGuestDTO);
        }

        return list;
    }

        public void addItemToCart(int productID, int quantity)
            throws SQLException, NamingException, ClassNotFoundException {
        //1. Checking items has existed

        if (this.items == null) {
            this.items = new HashMap<>();
        }

        //2. Checking item exited in items
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = dao.getProductByProductID(productID);

        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + quantity;
        }

        //3. Update items
        this.items.put(dto, quantity);
    }

}
