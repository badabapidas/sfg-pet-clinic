package com.sag.bada.badapetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sag.bada.badapetclinic.model.Owner;
import com.sag.bada.badapetclinic.model.Pet;
import com.sag.bada.badapetclinic.model.PetType;
import com.sag.bada.badapetclinic.model.Speciality;
import com.sag.bada.badapetclinic.model.Vet;
import com.sag.bada.badapetclinic.services.OwnerService;
import com.sag.bada.badapetclinic.services.PetTypeService;
import com.sag.bada.badapetclinic.services.SpecialityService;
import com.sag.bada.badapetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialityService specialtiesService;

	@Autowired
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialityService specialtiesService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtiesService = specialtiesService;
	}

	@Override
	public void run(String... args) throws Exception {

		int petTypeCount = petTypeService.findAll().size();
		if (petTypeCount == 0)
			loadData();

	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType saveDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		dog.setName("Cat");
		PetType saveCatPetType = petTypeService.save(cat);

		Speciality radiology = new Speciality();
		radiology.setDescription("Radiology");
		Speciality savedRadiology = specialtiesService.save(radiology);

		Speciality surgery = new Speciality();
		surgery.setDescription("Surgery");
		Speciality savedSurgery = specialtiesService.save(radiology);

		Speciality dentistry = new Speciality();
		dentistry.setDescription("Dentistry");
		Speciality savedDentistry = specialtiesService.save(dentistry);

		Owner owner1 = new Owner();
		owner1.setFirstName("Bapi");
		owner1.setLastName("Das");
		owner1.setAddress("Martahhalli");
		owner1.setCity("Bangalore");
		owner1.setTelephone("11111111");

		Pet bapisPet = new Pet();
		bapisPet.setPetType(saveDogPetType);
		bapisPet.setOwner(owner1);
		bapisPet.setBirthDate(LocalDate.now());
		bapisPet.setName("Rosco");
		owner1.getPets().add(bapisPet);

		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Umar");
		owner2.setLastName("BaBa");
		owner2.setAddress("Assam");
		owner1.setCity("Guwahati");
		owner1.setTelephone("2222222");

		Pet umarsPet = new Pet();
		umarsPet.setPetType(saveCatPetType);
		umarsPet.setOwner(owner2);
		umarsPet.setBirthDate(LocalDate.now());
		umarsPet.setName("Batra");
		owner2.getPets().add(umarsPet);

		ownerService.save(owner2);

		System.out.println("Loaded Owners....");

		Vet vet1 = new Vet();
		vet1.setFirstName("Sandeep");
		vet1.setLastName("Sharma");
		vet1.getSpecialities().add(savedRadiology);

		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Anuj");
		vet2.setLastName("Baruah");
		vet2.getSpecialities().add(savedSurgery);

		vetService.save(vet2);

		System.out.println("Loaded Vets....");
	}

}
