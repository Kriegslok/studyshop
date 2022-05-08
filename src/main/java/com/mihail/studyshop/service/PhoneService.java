package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.Phone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PhoneService {


    public Phone addPhone(Phone phone);

    public List<Phone> getPhones();

    public Phone getPhoneById(UUID phoneGuid);

    public Phone getPhone(UUID phoneGuid);

    public Phone getPhone(String number);

    public Phone deletePhone(UUID phoneGuid);

    public Phone editPhone(UUID phoneGuid, Phone phone);





}
