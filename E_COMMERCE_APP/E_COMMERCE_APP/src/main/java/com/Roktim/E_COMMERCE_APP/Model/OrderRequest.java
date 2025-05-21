package com.Roktim.E_COMMERCE_APP.Model;

import java.util.Map;
//We are using this DTO type class because too many mappings can create problem of recursions and unnecessary data will we shown without any reason.
//This DTO will help to transfer objects from one class  to another without mapping resursion confliicts.
//Without this we have to write jason references again and again for instructing data transfer , so DTO acts as a layer between mapped classes.
public class OrderRequest {

    //key-product Id
    //value-quantity
    //For now we will think that this Map is Taking the product Id and Quantity.Yaha man lete hain ki internally automatically userId or productId mapped ho gayi hain.
    private Map<Long,Integer> productQuantities;

    private double totalAmount;

    public Map<Long, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<Long, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
