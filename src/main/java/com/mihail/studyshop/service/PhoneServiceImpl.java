package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.ManagerRepository;
import com.mihail.studyshop.entities.Phone;
import com.mihail.studyshop.entities.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PhoneServiceImpl implements PhoneService {


    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository) {

        this.phoneRepository = phoneRepository;
    }

    @Transactional
    @Override
    public Phone addPhone(Phone phone) {
        if (phone.getPrimary() == null || phone.getPhoneNumber() == null)
            throw new IllegalArgumentException("Phone number is null or Primary is null");
        Phone phone1 = new Phone(phone.getPhoneNumber(), phone.getPrimary());
//        phone1.setManager(managerService.getManager(phone.getManager().getGuid()));

        return phoneRepository.save(phone);
    }

    @Override
    public List<Phone> getPhones() {
        List<Phone> phones = StreamSupport
                .stream(phoneRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return phones;
    }

    @Override
    public Phone getPhoneById(UUID phoneGuid) {
        return getPhone(phoneGuid);
    }

    @Override
    public Phone getPhone(UUID phoneGuid) {
        return phoneRepository.findById(phoneGuid).orElseThrow(() ->
                new IllegalArgumentException(
                        "author with id: " + phoneGuid + " could not be found"));
    }

    @Override
    public Phone getPhone(String number) {
        Phone phone = phoneRepository.getPhoneByPhoneNumber(number);
        if(phone == null) throw  new IllegalArgumentException(
                "author with id: " + number + " could not be found");
        return phone;

    }

    @Transactional
    @Override
    public Phone deletePhone(UUID phoneGuid) {
        Phone phone = getPhone(phoneGuid);
        phoneRepository.delete(phone);
        return phone;
    }

    @Transactional
    @Override
    public Phone editPhone(UUID phoneGuid, Phone phone) {
        Phone phoneToEdit = getPhone(phoneGuid);
        phoneToEdit.setManager(phone.getManager());
        phoneToEdit.setPrimary(phone.getPrimary());
        phoneToEdit.setDateCreate(phone.getDateCreate());
        phoneToEdit.setPhoneNumber(phone.getPhoneNumber());

        return phoneToEdit;
    }


}
