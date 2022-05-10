package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.ManagerRepository;
import com.mihail.studyshop.entities.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ManagerServiceImpl implements ManagerService {


    private final ManagerRepository managerRepository;
    private final PhoneService phoneService;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository, PhoneService phoneService) {
        this.managerRepository = managerRepository;
        this.phoneService = phoneService;
    }

    @Transactional
    @Override
    public Manager addManger(Manager manager) {
        if (manager.getFirstName() == null || manager.getLastName() == null || manager.getInn() == null)
            throw new IllegalArgumentException("author need a zipcode");
        Manager manager1 = new Manager(manager.getFirstName(), manager.getLastName(), manager.getInn());
        manager1 = managerRepository.save(manager1);
        List<Phone> phones = getManagerPhones(manager1.getGuid());
        if(phones.isEmpty() && !manager.getPhones().isEmpty()){
            for(Phone phone: manager.getPhones()){
                phone.setManager(manager1);
                phone.setGuid(phoneService.addPhone(phone).getGuid());
                //manager1.getPhones().add(phone);

            }
        }
        manager1.setPhones(phones);
        manager1.setBlocked(manager.getBlocked());
        manager1.setEmploymentDate(manager.getEmploymentDate());
        manager1.setFiredDate(manager.getFiredDate());
        manager1.setOnHoliday(manager.getOnHoliday());

        return editManager(manager1.getGuid(), manager1);
    }

    @Override
    public List<Manager> getManagers() {
        List<Manager> managers = StreamSupport
                .stream(managerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return managers;
    }


    @Override
    public Manager getManagerById(UUID managerGuid) {
        return getManager(managerGuid);
    }

    @Override
    public Manager getManager(UUID managerGuid) {
        return managerRepository.findById(managerGuid).orElseThrow(()->
                new IllegalArgumentException(
                "author with id: " + managerGuid + " could not be found"));
    }

    @Override
    public List<Manager> getByFirstAndLastName(String firstName, String lastName) {
        List<Manager> managers = new ArrayList<>();
        managers = managerRepository.findByFirstNameAndLastName(firstName, lastName);
        if(managers.isEmpty()) throw new IllegalArgumentException(
                "author with firstName and lastName: " + firstName + " " + lastName + " could not be found");
        return managers;
    }

    @Override
    public List<Manager> findByFirstName(String firstName) {
        List<Manager> managers = new ArrayList<>();
        managers = managerRepository.findByFirstName(firstName);
        if(managers.isEmpty()) throw new IllegalArgumentException(
                "author with firstName: " + firstName + " could not be found");
        return managers;
    }

    @Override
    public List<Manager> findByLastName(String lastName) {
        List<Manager> managers = new ArrayList<>();
        managers = managerRepository.findByLastName(lastName);
        if(managers.isEmpty()) throw new IllegalArgumentException(
                "author with lastName: " + lastName + " could not be found");
        return managers;
    }

    @Override
    public Manager deleteManager(UUID managerGuid) {
        Manager manager = getManager(managerGuid);
        managerRepository.delete(manager);
        return manager;
    }

    @Transactional
    @Override
    public Manager editManager(UUID managerGuid, Manager manager) {
        Manager namagerToEdit = getManager(managerGuid);
        namagerToEdit.setOnHoliday(manager.getOnHoliday());
        namagerToEdit.setBlocked(manager.getBlocked());
        namagerToEdit.setPhones(manager.getPhones());
        namagerToEdit.setFiredDate(manager.getFiredDate());
        namagerToEdit.setDateCreate(manager.getDateCreate());
        namagerToEdit.setOnHoliday(manager.getOnHoliday());
        namagerToEdit.setInn(manager.getInn());
        namagerToEdit.setFirstName(manager.getFirstName());
        namagerToEdit.setLastName(manager.getLastName());
        namagerToEdit.setEmploymentDate(manager.getEmploymentDate());
        return namagerToEdit;
    }

    @Transactional
    @Override
    public Manager addPhoneToManager(UUID managerGuid, UUID phoneGuid) {
        Manager manager = getManager(managerGuid);
        Phone phone = phoneService.getPhone(phoneGuid);
        manager.getPhones().add(phone);
        return manager;
    }

    @Transactional
    @Override
    public Manager addPhoneToManager(UUID managerGuid, Phone phone) {
        Manager manager = getManager(managerGuid);
        try {
            addPhoneToManager(managerGuid, phone.getGuid());
            return editManager(managerGuid, manager);
        } catch (Exception e) {

        }
        phoneService.addPhone(phone);

        manager.getPhones().add(phone);
        return editManager(managerGuid, manager);
    }

    @Override
    public Manager deletePhoneFromManager(UUID managerGuid, UUID phoneGuid) {
        Manager manager = getManager(managerGuid);
        Phone phone = phoneService.getPhone(phoneGuid);
        manager.getPhones().remove(phone);
        return manager;
    }

    @Override
    public List<Phone> getManagerPhones(UUID managerGuid) {
        return getManager(managerGuid).getPhones();
    }

}

