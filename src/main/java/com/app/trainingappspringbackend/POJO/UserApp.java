package com.app.trainingappspringbackend.POJO;

import com.app.trainingappspringbackend.utils.Calculator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApp implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public int getCaloricNeeds() {
        double bmr =
                Calculator.getBmrByGender(gender, height, currentWeight, age);
        double caloricNeeds =
                Calculator.getBmrWithActivityLevel(activityLevel, bmr);
        return (int) Calculator.getBmrWithDietStatus(caloricNeeds,
                getDietStatus(), weightPerWeek);
    }

    public String getDietStatus() {
        return Calculator.calculateDietStatus(currentWeight, targetWeight);
    }

    public LocalDate getDietFinish() {
        return Calculator.getDietFinishDate(currentWeight, targetWeight, weightPerWeek);
    }

    public void setWeightPerWeek(double weightPerWeek) {
        if (currentWeight == targetWeight) weightPerWeek = 0.0d;
        this.weightPerWeek = weightPerWeek;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
