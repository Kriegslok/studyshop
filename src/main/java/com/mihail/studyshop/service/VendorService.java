package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.Vendor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface VendorService {

    public Vendor addVendor(Vendor vendor);

    public List<Vendor> getVendors();

    public Vendor getVendorById(UUID guid);

    public Vendor getVendor(UUID guid);

    public Vendor deleteVendor(UUID guid);

    public Vendor editVendor(UUID guid, Vendor vendor);

    public Vendor addVendorCodeToVendor(UUID vendorGuid, UUID vendorCodeGuid);

    public List<Vendor> getByName(String name);

}
