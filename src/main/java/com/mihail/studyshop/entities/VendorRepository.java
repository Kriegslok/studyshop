package com.mihail.studyshop.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    Vendor getVendorByGuid(UUID guid);
    List<Vendor> getVendorByName(String name);
    List<Vendor> findByName(String name);
}
