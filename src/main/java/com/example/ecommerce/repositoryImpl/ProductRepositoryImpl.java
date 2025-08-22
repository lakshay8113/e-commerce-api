package com.example.ecommerce.repositoryImpl;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonRegularExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductFilter;

public class ProductRepositoryImpl {
    private MongoOperations operations;

    ProductRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }

    public List<Product> findAllProducts(Pageable pageable) {

        Query query = new Query(); // {}

        query.addCriteria(Criteria.where("isDeleted").is(false)); // {isDeleted: false}

        query.with(pageable); // { skip: 0, limit: 10,sort: {price: -1} }

        System.out.println(query.toString());

        return operations.find(query, Product.class);
    }

    public Product createProduct(Product product) {
        return operations.insert(product);
    }

    // findById
    // save, replace - update
    // remove - delete

    public List<Product> searchProducts(ProductFilter productFilter, Pageable pageable) {
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (productFilter.getSearchTerm() != null) {
            BsonRegularExpression regex = new BsonRegularExpression(productFilter.getSearchTerm(), "i");

            criteria.orOperator(
                    Criteria.where("name").regex(regex),
                    Criteria.where("description").regex(regex),
                    Criteria.where("tags").is(productFilter.getSearchTerm()));
            // {$or: [{name: new RegExp("", "i")},{description: new RegExp("", "i")}, {tags:
            // "2024"}]}
        }

        if (productFilter.getPriceRange() != null) {
            List<Criteria> criterias = new ArrayList<Criteria>();

            if (productFilter.getPriceRange().getMin() > 0) {
                criterias.add(Criteria.where("price").gte(productFilter.getPriceRange().getMin()));
            }

            if (productFilter.getPriceRange().getMax() > 0) {
                criterias.add(Criteria.where("price").lt(productFilter.getPriceRange().getMax()));
            }

            criteria.andOperator(criterias);
        }

        query.addCriteria(criteria);

        // Projection Fields
        // query.fields().include("name", "price", "description");

        query.with(pageable);

        System.out.println(query.toString());

        return operations.find(query, Product.class);
    }

    public List<Product> findProductById(String id) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));

        System.out.println(query.toString());

        return operations.find(query, Product.class);
    }

    public Product updateProduct(Product product) {
        return operations.save(product);
    }

    public List<Product> removeProduct(String id) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(id));

        operations.remove(query);

        return operations.find(query, Product.class);
    }
}
