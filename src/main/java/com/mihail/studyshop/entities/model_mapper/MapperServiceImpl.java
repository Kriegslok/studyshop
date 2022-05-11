package com.mihail.studyshop.entities.model_mapper;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.Phone;
import com.mihail.studyshop.entities.Vendor;
import com.mihail.studyshop.entities.dto.ManagerDto;
import com.mihail.studyshop.entities.dto.PhoneDto;
import com.mihail.studyshop.entities.dto.VendorDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MapperServiceImpl implements MapperService{
    @Override
    public Manager managerFromDto(ManagerDto managerDto) {
        Manager manager = new Manager(managerDto.getFirstName(), managerDto.getLastName(), managerDto.getInn());
        manager.setPhones(Arrays.asList(new Phone(managerDto.getPhone(), true)));
        return manager;
    }

    @Override
    public Phone phoneFromDto(PhoneDto phoneDto) {
        Phone phone = new Phone(phoneDto.getPhoneNumber(), Boolean.getBoolean(phoneDto.getIsPrimary()));
        return phone;
    }

    @Override
    public Vendor vendorFromDto(VendorDto vendorDto) {
        Vendor vendor = new Vendor(vendorDto.getName(), vendorDto.getDescription());
        return vendor;
    }
}
