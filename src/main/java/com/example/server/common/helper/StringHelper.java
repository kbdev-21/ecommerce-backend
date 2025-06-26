package com.example.server.common.helper;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringHelper {
    public static String normalizedVietnameseText(String text) {
        if (text == null) {
            return "";
        }

        String normalized = text.trim().toLowerCase();
        // Normalize to decomposed form (separate base characters and diacritics)
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD);
        // Remove diacritical marks
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        normalized = pattern.matcher(normalized).replaceAll("");

        // Handle special Vietnamese characters
        normalized = normalized.replace('đ', 'd');
        normalized = normalized.replace('Đ', 'd');

        return normalized;
    }
}
