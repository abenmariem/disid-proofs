package com.disid.restful.service.api;

import com.disid.restful.model.Category;
import com.disid.restful.model.Product;

import io.springlets.data.domain.GlobalSearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

  long count();

  long countByCategoriesContains(Category category);

  void delete(Iterable<Long> ids);

  void delete(Product product);

  List<Product> findAll();

  Page<Product> findAll(GlobalSearch globalSearch, Pageable pageable);

  List<Product> findAll(Iterable<Long> ids);

  Page<Product> findByCategoriesContains(Category category, GlobalSearch search, Pageable pageable);

  Product findOne(Long id);

  List<Product> save(Iterable<Product> entities);

  Product save(Product entity);
}
