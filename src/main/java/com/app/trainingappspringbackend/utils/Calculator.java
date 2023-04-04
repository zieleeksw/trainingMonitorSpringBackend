package com.app.trainingappspringbackend.utils;

import java.time.LocalDate;

public class Calculator {
    private Calculator() {
    }

    // BMR formula for men: 66 + (6.2 x weight in pounds) + (12.7 x height in inches) - (6.76 x age in years)
    // BMR formula for women: 655.1 + (4.35 x weight in pounds) + (4.7 x height in inches) - (4.7 x age in years)
    public static double getBmrByGender(String gender,
                                        double height,
                                        double weight,
                                        int age) {
        double bmr = 0.0d;
        if (gender.equals("Male")) {
            bmr = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age);
        } else if (gender.equals("Female")) {
            bmr = 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age);
        }
        return bmr;
    }

    public static double getBmrWithActivityLevel(String activityLevel, double bmr) {
        bmr = switch (activityLevel) {
            case "Little active" -> bmr * 1.2;
            case "Light active" -> bmr * 1.375;
            case "Moderate active" -> bmr * 1.55;
            case "Hard  active" -> bmr * 1.725;
            case "Very hard active" -> bmr * 1.9;
            default -> 0.0d;
        };
        return bmr;
    }

    public static double getBmrWithDietStatus(double caloricNeeds,
                                              String dietStatus,
                                              double weightPerWeek) {
        if (dietStatus.equals("Bulking")) {
            caloricNeeds = caloricNeeds + 7700 * weightPerWeek / 7;
        } else if (dietStatus.equals("Reduction")) {
            caloricNeeds = caloricNeeds - 7700 * weightPerWeek / 7;
        }
        return caloricNeeds;
    }

    public static String calculateDietStatus(double currentWeight, double targetWeight) {
        String status;
        if (currentWeight == targetWeight) status = "Weight maintenance";
        else if (currentWeight > targetWeight) status = "Reduction";
        else status = "Bulking";
        return status;
    }

    public static LocalDate getDietFinishDate(double currentWeight,
                                              double targetWeight,
                                              double weightPerWeek) {
        double weight;
        if (currentWeight > targetWeight) weight = currentWeight - targetWeight;
        else if (targetWeight > currentWeight) weight = targetWeight - currentWeight;
        else return LocalDate.now();
        double result = 0;
        int count = 0;
        do {
            result += weightPerWeek;
            count++;
        } while (result < weight);
        return LocalDate.now().plusWeeks(count);
    }
}