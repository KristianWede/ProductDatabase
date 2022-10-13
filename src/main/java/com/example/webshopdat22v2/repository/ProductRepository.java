package com.example.webshopdat22v2.repository;

import com.example.webshopdat22v2.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProductRepository {

    public List<Product> getAll(){
        List<Product> productList = new LinkedList<Product>();

        try{
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/webshop",
                    "root",
                    "Beaver");
            PreparedStatement psts = conn.prepareStatement("SELECT * from product");
            ResultSet resultSet = psts.executeQuery();


            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                productList.add(new Product(id,name,price));
                System.out.println(id + " " + name + " " + price);
            }

    } catch (SQLException e){
            System.out.println("Could not connect to database! You got gnomed!!!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return productList;
    }

}
