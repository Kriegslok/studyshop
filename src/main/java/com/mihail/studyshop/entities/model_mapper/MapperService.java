package com.mihail.studyshop.entities.model_mapper;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.Phone;
import com.mihail.studyshop.entities.Vendor;
import com.mihail.studyshop.entities.dto.ManagerDto;
import com.mihail.studyshop.entities.dto.PhoneDto;
import com.mihail.studyshop.entities.dto.VendorDto;
import org.springframework.stereotype.Service;

@Service
public interface MapperService {

    Manager managerFromDto(ManagerDto managerDto);

    Phone phoneFromDto(PhoneDto phoneDto);

    Vendor vendorFromDto(VendorDto vendorDto);
}
