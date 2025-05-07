package com.app.my_project;

import java.util.ArrayList; //import ArrayList class เพื่อเก็บข้อมูลในรูปแบบ List
import java.util.Arrays; //import Arrays class เพื่อใช้ในการแปลง ArrayList เป็น Array
import java.util.List; //import List interface เพื่อใช้ในการเก็บข้อมูลในรูปแบบ List

import org.springframework.web.bind.annotation.DeleteMapping; // import DeleteMapping class เพื่อใช้ในการลบข้อมูล
import org.springframework.web.bind.annotation.GetMapping; // import GetMapping class เพื่อใช้ในการดึงข้อมูล
import org.springframework.web.bind.annotation.PathVariable; // import PathVariable class เพื่อใช้ในการรับค่าจาก URL
import org.springframework.web.bind.annotation.PostMapping; // import PostMapping class เพื่อใช้ในการเพิ่มข้อมูล
import org.springframework.web.bind.annotation.PutMapping; // import PutMapping class เพื่อใช้ในการแก้ไขข้อมูล
import org.springframework.web.bind.annotation.RequestBody; // import RequestBody class เพื่อใช้ในการรับค่าจาก Body ใน postman
import org.springframework.web.bind.annotation.RequestMapping; // import RequestMapping class เพื่อใช้ในการกำหนด URL ใน postman
import org.springframework.web.bind.annotation.RestController; // import RestController class เพื่อใช้ในการสร้าง REST API

import com.app.models.BookModel; // import BookModel class เพื่อใช้ในการสร้าง Object ของ BookModel

@RestController
@RequestMapping("/api/books") // กำหนด URL สำหรับการเข้าถึง API
public class BookController {

    // สร้าง List ของ BookModel เพื่อเก็บข้อมูลหนังสือ
    private List<BookModel> books = new ArrayList<>(
            Arrays.asList( // สร้าง Object ของ BookModel และเพิ่มข้อมูลลงใน List
                    new BookModel("1", "Java", 500),
                    new BookModel("2", "Python", 600),
                    new BookModel("3", "C++", 700)));

    // อ่านข้อมูลหนังสือทั้งหมดด้วยคำสั่ง GetMapping
    @GetMapping
    public List<BookModel> getAllBooks() { // สร้างเมธอด getAllBooks เพื่อดึงข้อมูลหนังสือทั้งหมด
        return books; // คืนค่าข้อมูลหนังสือทั้งหมด
    }

    // อ่านข้อมูลหนังสือด้วย id ด้วยคำสั่ง GetMapping
    @GetMapping("/{id}") // การส่งค่าพารามิเตอร์ id ผ่าน URL
    public BookModel getBookById(@PathVariable String id) { // สร้างเมธอด getBookById เพื่อดึงข้อมูลหนังสือด้วย id
        return books.stream() // ใช้ Stream API เพื่อกรองข้อมูล
                .filter(book -> book.getId().equals(id)) // กรองข้อมูลหนังสือที่มี id ตรงกับที่ส่งมา
                .findFirst() // หาหนังสือเล่มแรกที่ตรงกับเงื่อนไข
                .orElse(null); // ถ้าไม่พบให้คืนค่า null
    }

    // การเพิ่มข้อมูลหนังสือด้วยคำสั่ง PostMapping
    @PostMapping // การส่งค่าพารามิเตอร์ id ผ่าน URL
    public BookModel addBook(@RequestBody BookModel book) { // สร้างเมธอด addBook เพื่อเพิ่มข้อมูลหนังสือ
        books.add(book); // เพิ่มข้อมูลหนังสือใหม่ลงใน List
        return book; // คืนค่าข้อมูลหนังสือที่เพิ่มเข้าไป
    }

    // การแก้ไขหรืออัพเดทข้อมูลหนังสือด้วยคำสั่ง PutMapping
    @PutMapping("/{id}") // การส่งค่าพารามิเตอร์ id ผ่าน URL
    public BookModel updateBook(@PathVariable String id, @RequestBody BookModel updatedBook) { // สร้างเมธอด updateBook
                                                                                               // เพื่อแก้ไขข้อมูลหนังสือ
        for (int i = 0; i < books.size(); i++) { // วนลูปเพื่อหาหนังสือที่ต้องการแก้ไข
            if (books.get(i).getId().equals(id)) { // ถ้าพบหนังสือที่มี id ตรงกัน
                books.set(i, updatedBook); // แก้ไขข้อมูลหนังสือใน List
                return updatedBook; // คืนค่าข้อมูลหนังสือที่แก้ไขแล้ว
            }
        }
        return null; // ถ้าไม่พบให้คืนค่า null
    }

    // การลบข้อมูลหนังสือด้วยคำสั่ง DeleteMapping
    @DeleteMapping("/{id}") // การส่งค่าพารามิเตอร์ id ผ่าน URL
    public void deleteBook(@PathVariable String id) { // สร้างเมธอด deleteBook เพื่อทำการลบข้อมูลหนังสือ
        books.removeIf(book -> book.getId().equals(id)); // ลบข้อมูลหนังสือที่มี id ตรงกัน
    } // คืนค่า void เพราะไม่ต้องการคืนค่าใดๆ

}
