package com.example.saes4_mobile.data;

public class SurveyIsCompleted {
    private static boolean isCompleted = false;

    public static boolean isSurveyCompleted() {
        return isCompleted;
    }

    public static void surveyCompleted() {
        isCompleted = true;
    }
}
