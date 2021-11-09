package com.amarillo.canineworld;

public class ModelProductos {
    private String discountAvailable, productCategoria, productDescPor, productDescPrecio,
            productDescripcion, productId, productTitle, productValor, timestamp, uid;

    public ModelProductos(){

    }


    public ModelProductos(String discountAvailable, String productCategoria, String productDescPor, String productDescPrecio,
                          String productDescripcion, String productId, String productTitle, String productValor, String timestamp, String uid) {
        this.discountAvailable = discountAvailable;
        this.productCategoria = productCategoria;
        this.productDescPor = productDescPor;
        this.productDescPrecio = productDescPrecio;
        this.productDescripcion = productDescripcion;
        this.productId = productId;
        this.productTitle = productTitle;
        this.productValor = productValor;
        this.timestamp = timestamp;
        this.uid = uid;
    }

    public String getDiscountAvailable() {
        return discountAvailable;
    }

    public void setDiscountAvailable(String discountAvailable) {
        this.discountAvailable = discountAvailable;
    }

    public String getProductCategoria() {
        return productCategoria;
    }

    public void setProductCategoria(String productCategoria) {
        this.productCategoria = productCategoria;
    }

    public String getProductDescPor() {
        return productDescPor;
    }

    public void setProductDescPor(String productDescPor) {
        this.productDescPor = productDescPor;
    }

    public String getProductDescPrecio() {
        return productDescPrecio;
    }

    public void setProductDescPrecio(String productDescPrecio) {
        this.productDescPrecio = productDescPrecio;
    }

    public String getProductDescripcion() {
        return productDescripcion;
    }

    public void setProductDescripcion(String productDescripcion) {
        this.productDescripcion = productDescripcion;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductValor() {
        return productValor;
    }

    public void setProductValor(String productValor) {
        this.productValor = productValor;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
