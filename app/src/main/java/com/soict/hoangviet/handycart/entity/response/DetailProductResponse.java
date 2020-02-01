package com.soict.hoangviet.handycart.entity.response;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.soict.hoangviet.handycart.utils.CommonExtensionUtil;
import com.soict.hoangviet.handycart.utils.NumberUtil;

public class DetailProductResponse {

    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("expiry_date")
    private String expiryDate;

    @SerializedName("weight")
    private int weight;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("description")
    private String description;

    @SerializedName("content")
    private String content;

    @SerializedName("regular_price")
    private int regularPrice;

    @SerializedName("manufaction")
    private String manufaction;

    @SerializedName("seller_price")
    private int sellerPrice;

    @SerializedName("sale")
    private int sale;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("supplier")
    private Supplier supplier;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("sku")
    private long sku;

    @SerializedName("stock")
    private int stock;

    @SerializedName("flag_expiry")
    private int flagExpiry;

    @SerializedName("flag_stock")
    private int flagStock;

    @SerializedName("status")
    private int status;

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
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

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setRegularPrice(int regularPrice) {
        this.regularPrice = regularPrice;
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public void setManufaction(String manufaction) {
        this.manufaction = manufaction;
    }

    public String getManufaction() {
        return manufaction;
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

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {
        return supplier;
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

    public void setFlagExpiry(int flagExpiry) {
        this.flagExpiry = flagExpiry;
    }

    public int getFlagExpiry() {
        return flagExpiry;
    }

    public void setFlagStock(int flagStock) {
        this.flagStock = flagStock;
    }

    public int getFlagStock() {
        return flagStock;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "DetailProductResponse{" +
                        "thumbnail = '" + thumbnail + '\'' +
                        ",expiry_date = '" + expiryDate + '\'' +
                        ",weight = '" + weight + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",description = '" + description + '\'' +
                        ",content = '" + content + '\'' +
                        ",regular_price = '" + regularPrice + '\'' +
                        ",manufaction = '" + manufaction + '\'' +
                        ",seller_price = '" + sellerPrice + '\'' +
                        ",sale = '" + sale + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",supplier = '" + supplier + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",sku = '" + sku + '\'' +
                        ",stock = '" + stock + '\'' +
                        ",flag_expiry = '" + flagExpiry + '\'' +
                        ",flag_stock = '" + flagStock + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @BindingAdapter({"imageDetailProductUrl", "default"})
    public static void loadImageUrl(ImageView view, String url, Drawable drawable) {
        CommonExtensionUtil.loadImageDrawable(
                view,
                url,
                drawable
        );
    }

    @BindingAdapter({"sellerPrice"})
    public static void handleRegularPrice(TextView view, int regularPrice) {
        view.setText(NumberUtil.handlePrice(regularPrice));
        view.setPaintFlags(view.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}