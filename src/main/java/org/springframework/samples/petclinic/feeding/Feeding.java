package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feeding")
public class Feeding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    @Column(name = "start_date")
    LocalDate startDate;
    
    @NotNull
    @Min(1)
    @Column(name = "weeks_duration")
    double weeksDuration;

    @ManyToOne
    @NotNull
    Pet pet;   
}
