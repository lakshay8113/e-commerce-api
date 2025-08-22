package com.example.ecommerce.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductFilter;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repositoryImpl.ProductRepositoryImpl;
import com.example.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductRepositoryImpl productRepositoryImpl;

    @Override
    public List<Product> getAllProducts(int pageNo, int limit, String sortBy, int sortOrder, String searchTerm) {

        Sort sort = Sort.unsorted();

        if (!sortBy.equals("")) {
            Sort.Direction sortDirection = sortOrder == -1 ? Sort.Direction.DESC
                    : (sortOrder == 1 ? Sort.Direction.ASC : null);

            sort = sortDirection == null ? Sort.by(sortBy) : Sort.by(sortDirection, sortBy);
        }

        PageRequest pageRequest = PageRequest.of(pageNo - 1, limit, sort);

        return productRepositoryImpl.findAllProducts(pageRequest);
    }

    @Override
    public List<Product> searchProducts(ProductFilter productFilter) {
        int sortOrder = (productFilter.getSort() != null && productFilter.getSort().getOrder() != 0)
                ? productFilter.getSort().getOrder()
                : 1;
        String sortBy = (productFilter.getSort() != null && productFilter.getSort().getField() != null)
                ? productFilter.getSort().getField()
                : null;
        int pageNo = productFilter.getPageNumber() != 0 ? productFilter.getPageNumber() : 1;
        int limit = productFilter.getSize() != 0 ? productFilter.getSize() : 10;

        Sort sort = Sort.unsorted();

        if (sortBy != null) {
            Sort.Direction sortDirection = sortOrder == -1 ? Sort.Direction.DESC
                    : (sortOrder == 1 ? Sort.Direction.ASC : null);

            sort = sortDirection == null ? Sort.by(sortBy) : Sort.by(sortDirection, sortBy);
        }

        PageRequest pageRequest = PageRequest.of(pageNo - 1, limit, sort);

        return productRepositoryImpl.searchProducts(productFilter, pageRequest);
    }

    @Override
    public Product addProduct(Product product) {
        Product productToSend = new Product();
        productToSend.setCreatedAt(new Date());
        productToSend.setUpdatedAt(new Date());
        productToSend.setDeleted(false);
        productToSend.setName(product.getName());
        productToSend.setDescription(product.getDescription());
        productToSend.setPrice(product.getPrice());
        productToSend.setDiscountedPrice(product.getDiscountedPrice());
        productToSend.setStock(product.getStock());
        productToSend.setImages(product.getImages());

        return productRepositoryImpl.createProduct(productToSend);
    }

    @Override
    public List<Product> getProductById(String myId) {
        return productRepositoryImpl.findProductById(myId);
    }

    @Override
    public Product updateProduct(String id, Product updatedProduct) {
        Product productFromDB = productRepository.findById(id).orElse(null);

        if (productFromDB != null) {
            if (updatedProduct.getName() != null) {
                productFromDB.setName(updatedProduct.getName());
            }

            if (updatedProduct.getDescription() != null) {
                productFromDB.setDescription(updatedProduct.getDescription());
            }

            if (updatedProduct.getPrice() >= 0) {
                productFromDB.setPrice(updatedProduct.getPrice());
            }

            if (updatedProduct.getDiscountedPrice() >= 0) {
                productFromDB.setDiscountedPrice(updatedProduct.getDiscountedPrice());
            }

            if (updatedProduct.getStock() >= 0) {
                productFromDB.setStock(updatedProduct.getStock());
            }
            if (updatedProduct.getImages() != null) {
                productFromDB.setImages(updatedProduct.getImages());
            }

            productFromDB.setUpdatedAt(new Date());

            return productRepositoryImpl.updateProduct(productFromDB);
        }

        return null;
    }

    @Override
    public boolean deleteProduct(String id) {
        Product productFromDB = productRepository.findProductById(id).orElse(null);

        if (productFromDB != null) {
            // productFromDB.setDeleted(true);
            productRepository.save(productFromDB);
            return true;
        }

        return false;
    }
}
