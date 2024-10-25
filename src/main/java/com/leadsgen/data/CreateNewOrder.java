package com.leadsgen.data;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;
import io.qameta.allure.Step;
import lombok.Data;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

@Data
public class CreateNewOrder {
    @CsvBindByName(column = "SKU")
    private String sku;
    @CsvBindByName(column = "Shippingfee")
    private String shippingFee;
    @CsvBindByName(column = "Starter")
    private String starter;
    @CsvBindByName(column = "Printingside")
    private String printingSide;
    @CsvBindByName(column = "Size")
    private String size;
    @CsvBindByName(column = "Color")
    private String color;
    @CsvBindByName(column = "Location")
    private String location;
    @CsvBindByName(column = "Product Name")
    private String productName;



    @Override
    public String toString() {
        return "AddCustomProduct(sku=" + sku +
                ", shippingFee=" + shippingFee +
                ", starter=" + starter +
                ", printingSide=" + printingSide +
                ", productName=" + productName +
                ", location=" + location +
                ", size=" + size +
                ", color=" + color + ")";
    }

    private <T> List<T> getDataAsObject(String pathToFile, Class<T> type) {
        List<T> tList = new ArrayList<>();
        try (Reader reader = new FileReader(pathToFile)) {
            tList = new CsvToBeanBuilder<T>(reader)
                    .withType(type)
                    .build().parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tList;
    }


    public List<CreateNewOrder> createOrderData() {
        List<CreateNewOrder> data = getDataAsObject("src/main/java/com/leadsgen/data/BurgerPrintsSKU2DUS-Vini.csv", CreateNewOrder.class);
        return data;
    }

    @Step
    public static String getShippingFeeForSKU(List<CreateNewOrder> orderList, String skuToFind) {
        if (orderList == null || orderList.isEmpty() || skuToFind == null || skuToFind.trim().isEmpty()) {
            return null;
        }
        // Duyệt qua từng sản phẩm trong danh sách
        for (CreateNewOrder order : orderList) {
            // So sánh SKU (loại bỏ khoảng trắng và không phân biệt chữ hoa/chữ thường)
            if (order.getSku().trim().equalsIgnoreCase(skuToFind.trim())) {
                // Trả về giá trị Shipping Fee nếu SKU khớp
                return order.getShippingFee();
            }
        }
        // Trả về null nếu không tìm thấy SKU khớp
        return null;
    }

    @Step
    public static String getStaterForSKU(List<CreateNewOrder> orderList, String skuToFind) {
        if (orderList == null || orderList.isEmpty() || skuToFind == null || skuToFind.trim().isEmpty()) {
            return null;
        }
        // Duyệt qua từng sản phẩm trong danh sách
        for (CreateNewOrder order : orderList) {
            // So sánh SKU (loại bỏ khoảng trắng và không phân biệt chữ hoa/chữ thường)
            if (order.getSku().trim().equalsIgnoreCase(skuToFind.trim())) {
                // Trả về giá trị Shipping Fee nếu SKU khớp
                return order.getStarter();
            }
        }
        // Trả về null nếu không tìm thấy SKU khớp
        return null;
    }

    @Step
    public static String getCostForSKU(List<CreateNewOrder> productList, String skuToFind) {
        if (productList == null || productList.isEmpty() || skuToFind == null || skuToFind.trim().isEmpty()) {
            return null;
        }
        for (CreateNewOrder product : productList) {
            if (product.getSku().trim().equalsIgnoreCase(skuToFind.trim())) {
                double state = Double.parseDouble(product.getStarter());
                double printingSide = Double.parseDouble(product.getPrintingSide());
                double sum = state + printingSide;
                return String.valueOf(sum).replaceAll("\\.0", "");
            }
        }
        return null;
    }
}
