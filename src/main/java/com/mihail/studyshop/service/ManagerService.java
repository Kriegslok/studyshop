package com.mihail.studyshop.service;

import com.mihail.studyshop.entities.Manager;
import com.mihail.studyshop.entities.Phone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ManagerService {

    public Manager addManger(Manager manager);

    public List<Manager> getManagers();

    public Manager getManagerById(UUID managerGuid);

    public Manager getManager(UUID managerGuid);

    public Manager deleteManager(UUID managerGuid);

    public Manager editManager(UUID managerGuid, Manager manager);

    public Manager addPhoneToManager(UUID managerGuid, UUID phoneGuid);

    public Manager deletePhoneFromManager(UUID managerGuid, UUID phoneGuid);

    public List<Manager> getByFirstAndLastName(String firstName, String LastName);

    List<Manager> findByFirstName(String firstName);

    public List<Phone> getManagerPhones(UUID managerGuid);

    public Manager addPhoneToManager(UUID managerGuid, Phone phone);

}
