package com.billing.app.accounts.repositories;

import com.billing.app.accounts.dto.CollectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsCollectionRepository extends JpaRepository<CollectionRequest, Integer> {
}
