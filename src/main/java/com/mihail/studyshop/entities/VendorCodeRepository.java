package com.mihail.studyshop.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VendorCodeRepository extends JpaRepository<VendorCode, UUID> {
    List<VendorCode> findByCode(String code);
    List<VendorCode> findByVendor(Vendor vendor);

}
