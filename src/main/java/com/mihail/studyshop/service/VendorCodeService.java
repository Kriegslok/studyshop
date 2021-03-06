package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Phone;
import com.mihail.studyshop.entities.VendorCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface VendorCodeService {

    public VendorCode addVendorCode(VendorCode vendorCode) throws IllegalArgumentException;

    public List<VendorCode> getVendorCodes();

    public VendorCode getVendorCodeById(UUID guid);

    public VendorCode getVendorCode(UUID guid) throws IllegalArgumentException;

    public List<VendorCode> getVendorCodeByCode(String code);

    public VendorCode deleteVendorCode(UUID guid);

    public VendorCode editVendorCode(UUID guid, VendorCode vendorCode);

    public boolean exists (VendorCode vendorCode);

    public void setVendorService(VendorService vendorService);

    public List<VendorCode> getVendorCodeByGuidOrVendorOrCode(String guid, String vendorGuid, String code);

    public List<VendorCode> deleteVendorCodeByGuidOrVendorOrCode(String guid, String vendorGuid, String code);
}
