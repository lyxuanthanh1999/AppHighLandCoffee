package com.tieuluan.mainprojectmycoffee.Database.ModelDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Cart: Entity nơi định nghĩa bảng và trường của Database.
// Mỗi 1 Entity tương đương với 1 bảng trong Database.
@Entity(tableName = "Cart")
public class Cart {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "HinhAnh")
    public String HinhAnh;

    @ColumnInfo(name = "amount")
    public int amount;

    @ColumnInfo(name = "price")
    public Double price;

    @ColumnInfo(name = "duong")
    public int duong;

    @ColumnInfo(name = "da")
    public int da;

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDuong() {
        return duong;
    }

    public void setDuong(int duong) {
        this.duong = duong;
    }

    public int getDa() {
        return da;
    }

    public void setDa(int da) {
        this.da = da;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public Cart(int id, String name, int amount, Double price, int duong, int da) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.duong = duong;
        this.da = da;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", HinhAnh='" + HinhAnh + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", duong=" + duong +
                ", da=" + da +
                '}';
    }
}
