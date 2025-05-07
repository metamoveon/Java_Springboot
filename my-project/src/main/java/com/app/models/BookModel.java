package com.app.models;

public class BookModel {
    // สร้างฟิลด์ขึ้นมา 3 ฟิลด์ในแบบ private
    private String id;
    private String name;
    private Integer price;

    // สร้าง Constructor
    public BookModel(String id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // สร้าง Getter และ Setter ของคลาส BookModel ทุกครั้งที่ประกาศ private
    // ฟิลด์จะต้องสร้าง Getter และ Setter ขึ้นมาเพื่อให้สามารถเข้าถึงฟิลด์ได้

    // อ่านค่า get ก่อน
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    // เขียนค่่า set โดยไม่ได้รับค่าจากผู้ใช้จึงใช้คำสั่ง void
    // เพื่อไม่ให้ผู้ใช้สามารถเปลี่ยนแปลงค่าของฟิลด์ได้
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
