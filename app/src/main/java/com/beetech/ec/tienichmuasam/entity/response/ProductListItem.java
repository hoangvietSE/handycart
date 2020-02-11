package com.beetech.ec.tienichmuasam.entity.response;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.beetech.ec.tienichmuasam.utils.CommonExtensionUtil;

public class ProductListItem {

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("expiry_date")
    private String expiryDate;

    @SerializedName("weight")
    private int weight;

    @SerializedName("regular_price")
    private int regularPrice;

    @SerializedName("seller_price")
    private int sellerPrice;

    @SerializedName("sale")
    private int sale;

    @SerializedName("product_id")
    private int productId;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("sku")
    private long sku;

    @SerializedName("stock")
    private int stock;

    @SerializedName("supplier_name")
    private String supplierName;

    @SerializedName("flag_expiry")
    private int flagExpiry;

    @SerializedName("supplier_id")
    private int supplierId;

    @SerializedName("flag_stock")
    private int flagStock;

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setRegularPrice(int regularPrice) {
        this.regularPrice = regularPrice;
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public void setSellerPrice(int sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public int getSellerPrice() {
        return sellerPrice;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getSale() {
        return sale;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public long getSku() {
        return sku;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setFlagExpiry(int flagExpiry) {
        this.flagExpiry = flagExpiry;
    }

    public int getFlagExpiry() {
        return flagExpiry;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setFlagStock(int flagStock) {
        this.flagStock = flagStock;
    }

    public int getFlagStock() {
        return flagStock;
    }

    @Override
    public String toString() {
        return
                "ProductListItem{" +
                        "thumbnail = '" + thumbnail + '\'' +
                        ",quantity = '" + quantity + '\'' +
                        ",expiry_date = '" + expiryDate + '\'' +
                        ",weight = '" + weight + '\'' +
                        ",regular_price = '" + regularPrice + '\'' +
                        ",seller_price = '" + sellerPrice + '\'' +
                        ",sale = '" + sale + '\'' +
                        ",product_id = '" + productId + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",sku = '" + sku + '\'' +
                        ",stock = '" + stock + '\'' +
                        ",supplier_name = '" + supplierName + '\'' +
                        ",flag_expiry = '" + flagExpiry + '\'' +
                        ",supplier_id = '" + supplierId + '\'' +
                        ",flag_stock = '" + flagStock + '\'' +
                        "}";
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        CommonExtensionUtil.loadImageDrawable(view, url, error);
    }
}