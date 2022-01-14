package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType>{

    @Autowired
    FeedingRepository feedingRepository;

    @Override
    public String print(FeedingType object, Locale locale) {
        return object.getName();
    }

    @Override
    public FeedingType parse(String text, Locale locale) throws ParseException {
        for (FeedingType pt : this.feedingRepository.findAllFeedingTypes()){
            if(pt.getName().equals(text))
                return pt;
        }
        throw new ParseException("No se encuentra el tipo : "+text, 0);
    }
    
}
