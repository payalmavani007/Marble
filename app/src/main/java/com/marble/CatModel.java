package com.marble;

/**
 * Created by hp-pc on 06-01-2018.
 */

public class CatModel  {
    String cat_id;
    String category_name;
    String getCategory_description;
    String cat_photo;

    public String getCategory_price() {
        return category_price;
    }

    public void setCategory_price(String category_price) {
        this.category_price = category_price;
    }

    String category_price;


    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getGetCategory_description() {
        return getCategory_description;
    }

    public void setGetCategory_description(String getCategory_description) {
        this.getCategory_description = getCategory_description;
    }

    public String getCat_photo() {
        return cat_photo;
    }

    public void setCat_photo(String cat_photo) {
        this.cat_photo = cat_photo;
    }

}
