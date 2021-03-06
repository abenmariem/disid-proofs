package com.disid.restful.service.impl;

import com.disid.restful.model.Category;
import com.disid.restful.model.Product;
import com.disid.restful.repository.ProductRepository;
import com.disid.restful.service.api.ProductService;

import io.springlets.data.domain.GlobalSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

  public ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public long count() {
    return productRepository.count();
  }

  public long countByCategoriesContains(Category category) {
    return productRepository.countByCategoriesContains(category);
  }

  @Transactional(readOnly = false)
  public void delete(Iterable<Long> ids) {
    List<Product> toDelete = productRepository.findAll(ids);
    productRepository.deleteInBatch(toDelete);
  }

  @Transactional
  public void delete(Product product) {
    // Clear bidirectional many-to-many child relationship with categories        
    for (Category category : product.getCategories()) {
      category.getProducts().remove(product);
    }
    product.setCategories(null);

    productRepository.delete(product);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Page<Product> findAll(GlobalSearch globalSearch, Pageable pageable) {
    return productRepository.findAll(globalSearch, pageable);
  }

  public List<Product> findAll(Iterable<Long> ids) {
    return productRepository.findAll(ids);
  }

  public List<Product> findAll(Long[] productIds) {
    return productRepository.findAll(Arrays.asList(productIds));
  }

  public Page<Product> findByCategoriesContains(Category category, GlobalSearch search,
      Pageable pageable) {
    return productRepository.findByCategoriesContains(category, search, pageable);
  }

  public Product findOne(Long id) {
    return productRepository.findOne(id);
  }

  @Transactional(readOnly = false)
  public List<Product> save(Iterable<Product> entities) {
    return productRepository.save(entities);
  }

  @Transactional(readOnly = false)
  public Product save(Product entity) {
    return productRepository.save(entity);
  }
}
