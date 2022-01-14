package org.springframework.samples.petclinic.feeding;

import java.security.Principal;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

public class FeedingController {
    
    @GetMapping({"/product/create"})
	public String initCreationForm(Map<String, Object> model,Principal principal) {
		return "feedings/createOrUpdateFeedingForm";
	}
}
