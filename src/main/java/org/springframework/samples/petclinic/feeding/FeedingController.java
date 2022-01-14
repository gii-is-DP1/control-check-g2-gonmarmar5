package org.springframework.samples.petclinic.feeding;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(value = "/feeding/create")
	public String processCreationForm(@Valid Feeding feeding,BindingResult result, Map<String, Object> model) {

		if(result.hasErrors()){
			Collection<FeedingType> ft = this.feedingService.getAllFeedingTypes();
			Collection<Feeding> f = this.feedingService.getAll();
			Collection<Pet> p = this.petService.getAllPets();

			model.put("feeding", f);
			model.put("feedingTypes", ft);
			model.put("pets", p);
			return "feedings/createOrUpdateFeedingForm";
		}
		else{
			try{
				feedingService.save(feeding);
				model.put("message", "Feeding saved");
			}
			catch (UnfeasibleFeedingException ex){
				result.reject("La mascota seleccionada no se le puede asignar el plan de alimentación especificado."); 
			}
		}

		return "welcome";
	}
}
