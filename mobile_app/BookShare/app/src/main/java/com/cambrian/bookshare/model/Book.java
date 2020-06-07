package com.cambrian.bookshare.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book implements Serializable {
    @SerializedName("author")
    @Expose
    private List<String> author = new ArrayList<String>();
    @SerializedName("courseCodes")
    @Expose
    private List<String> courseCodes = new ArrayList<String>();
    @SerializedName("tags")
    @Expose
    private List<String> tags = new ArrayList<String>();
    @SerializedName("bookPhotos")
    @Expose
    private List<String> bookPhotos = new ArrayList<String>();
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("edition")
    @Expose
    private String edition;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("dateAdded")
    @Expose
    private String dateAdded;
    @SerializedName("categories")
    @Expose
    private List<String> categories = new ArrayList<String>();
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("bookUser")
    @Expose
    private String bookUser;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("orgPrice")
    @Expose
    private String orgPrice;

    public String getBookUser() {
        return bookUser;
    }

    public void setBookUser(String bookUser) {
        this.bookUser = bookUser;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOrgPrice() {
        return orgPrice;
    }

    public void setOrgPrice(String orgPrice) {
        this.orgPrice = orgPrice;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(List<String> courseCodes) {
        this.courseCodes = courseCodes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getBookPhotos() {
        return bookPhotos;
    }

    public void setBookPhotos(List<String> bookPhotos) {
        this.bookPhotos = bookPhotos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Book(List<String> author, String isbn, String title, String note, Integer year, List<String> categories, Integer price) {
        this.author = author;
        this.isbn = isbn;
        this.title = title;
        this.note = note;
        this.year = year;
        this.categories = categories;
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return book.equals(book.isbn);
    }
}
