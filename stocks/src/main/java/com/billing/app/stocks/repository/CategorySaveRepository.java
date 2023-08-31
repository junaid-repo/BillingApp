package com.billing.app.stocks.repository;

import com.billing.app.stocks.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

public interface CategorySaveRepository extends JpaRepository<ProductCategory, String> {
}
