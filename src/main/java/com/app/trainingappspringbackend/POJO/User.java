package com.app.trainingappspringbackend.POJO;

import com.app.trainingappspringbackend.utils.Calculator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String gender;
    private LocalDate dob;
    private int height;
    private double currentWeight;
    private double targetWeight;
    private double weightPerWeek;
    private String activityLevel;
    @Transient
    private int age;
    @Transient
    private int caloricNeeds;
    @Transient
    private String dietStatus;
    @Transient
    private LocalDate dietFinish;

    public int getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
    public int getCaloricNeeds(){
        double bmr =
                Calculator.getBmrByGender(gender, height, currentWeight, age);
        double caloricNeeds =
                Calculator.getBmrWithActivityLevel(activityLevel, bmr);
        return (int)  Calculator.getBmrWithDietStatus(caloricNeeds,
                                getDietStatus(), weightPerWeek);
    }
    public String getDietStatus(){
        return Calculator.calculateDietStatus(currentWeight, targetWeight);
    }

    public LocalDate getDietFinish() {
        return Calculator.getDietFinishDate(currentWeight, targetWeight, weightPerWeek);
    }
    public void setWeightPerWeek(double weightPerWeek) {
        if( currentWeight == targetWeight ) weightPerWeek = 0.0d;
        this.weightPerWeek = weightPerWeek;
    }
}
