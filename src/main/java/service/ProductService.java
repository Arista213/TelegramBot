package service;

import model.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с продуктами.
 */
public abstract class ProductService {
    /**
     * @param productsStr введённые пользователем продукты в виде строки.
     * @return лист продуктов.
     */
    public static List<Product> getProducts(String productsStr) {
        String[] products = productsStr.split(" ");
        return Arrays.stream(products).map(Product::new).collect(Collectors.toList());
    }
}
