package com.example.dbclpm.dto;

import java.util.List;

public class StudentPointTableDtos {
    private List<StudentPointTable> studentPointTables;
    private int quantity;
    private int page;
    private int itemInPage = 10;
    
    public StudentPointTableDtos() {}

    public StudentPointTableDtos(List<StudentPointTable> studentPointTables, int quantity, int page, int itemInPage) {
        super();
        this.studentPointTables = studentPointTables;
        this.quantity = quantity;
        this.page = page;
        this.itemInPage = itemInPage;
    }

    public List<StudentPointTable> getStudentPointTables() {
        return studentPointTables;
    }

    public void setStudentPointTables(List<StudentPointTable> studentPointTables) {
        this.studentPointTables = studentPointTables;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItemInPage() {
        return itemInPage;
    }

    public void setItemInPage(int itemInPage) {
        this.itemInPage = itemInPage;
    }

    @Override
    public String toString() {
        return "StudentPointTableDtos [studentPointTables=" + studentPointTables + ", quantity=" + quantity + ", page="
                + page + ", itemInPage=" + itemInPage + "]";
    }
}
