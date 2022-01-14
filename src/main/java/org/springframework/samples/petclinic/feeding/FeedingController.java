package org.springframework.samples.petclinic.feeding;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class FeedingController {
    
	@Autowired
	FeedingService feedingService;

	@Autowired
	PetService petService;

    @GetMapping(path = "/feeding/create")
	public String initCreationForm(Map<String, Object> model,Principal principal) {

		Collection<FeedingType> ft = this.feedingService.getAllFeedingTypes();
		Collection<Feeding> f = this.feedingService.getAll();
		Collection<Pet> p = this.petService.getAllPets();

		model.put("feeding", f);
		model.put("feedingTypes", ft);
		model.put("pets", p);
		return "feedings/createOrUpdateFeedingForm";
	}
}
