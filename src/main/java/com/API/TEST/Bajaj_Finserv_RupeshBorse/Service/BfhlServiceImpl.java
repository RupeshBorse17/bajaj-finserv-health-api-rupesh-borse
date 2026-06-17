package com.API.TEST.Bajaj_Finserv_RupeshBorse.Service;

import com.API.TEST.Bajaj_Finserv_RupeshBorse.dto.RequestDto;
import com.API.TEST.Bajaj_Finserv_RupeshBorse.dto.ResponseDto;
import com.API.TEST.Bajaj_Finserv_RupeshBorse.dto.SummaryDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public ResponseDto process(RequestDto request, String requestId) {

        long startTime = System.currentTimeMillis();

        ResponseDto response = new ResponseDto();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();

        Map<String, Integer> alphabetFrequency = new HashMap<>();

        List<Double> numbers = new ArrayList<>();

        double sum = 0;

        int vowelCount = 0;
        int invalidCount = 0;

        boolean containsDuplicates = false;

        Set<String> uniqueValues = new LinkedHashSet<>();

        String longestAlphabeticValue = null;
        String shortestAlphabeticValue = null;

        for (String value : request.getData()) {

            if (value == null || value.trim().isEmpty()) {
                invalidCount++;
                continue;
            }

            if (!uniqueValues.add(value)) {
                containsDuplicates = true;
                continue;
            }

            // PURE NUMBER
            if (value.matches("-?\\d+(\\.\\d+)?")) {

                double num = Double.parseDouble(value);

                numbers.add(num);
                sum += num;

                if (num % 2 == 0) {
                    evenNumbers.add(value);
                } else {
                    oddNumbers.add(value);
                }
            }

            // PURE ALPHABET
            else if (value.matches("[a-zA-Z]+")) {

                String upper = value.toUpperCase();

                alphabets.add(upper);

                for (char c : upper.toCharArray()) {

                    String key = String.valueOf(c);

                    alphabetFrequency.put(
                            key,
                            alphabetFrequency.getOrDefault(key, 0) + 1
                    );

                    if ("AEIOU".indexOf(c) >= 0) {
                        vowelCount++;
                    }
                }

                if (longestAlphabeticValue == null ||
                        upper.length() > longestAlphabeticValue.length()) {
                    longestAlphabeticValue = upper;
                }

                if (shortestAlphabeticValue == null ||
                        upper.length() < shortestAlphabeticValue.length()) {
                    shortestAlphabeticValue = upper;
                }
            }

            // ALPHANUMERIC
            else if (value.matches(".*[a-zA-Z].*")
                    && value.matches(".*\\d.*")) {

                String letters =
                        value.replaceAll("[^a-zA-Z]", "")
                                .toUpperCase();

                if (!letters.isEmpty()) {

                    if (longestAlphabeticValue == null ||
                            letters.length() > longestAlphabeticValue.length()) {
                        longestAlphabeticValue = letters;
                    }

                    if (shortestAlphabeticValue == null ||
                            letters.length() < shortestAlphabeticValue.length()) {
                        shortestAlphabeticValue = letters;
                    }

                    for (char c : letters.toCharArray()) {

                        alphabets.add(String.valueOf(c));

                        String key = String.valueOf(c);

                        alphabetFrequency.put(
                                key,
                                alphabetFrequency.getOrDefault(key, 0) + 1
                        );

                        if ("AEIOU".indexOf(c) >= 0) {
                            vowelCount++;
                        }
                    }
                }

                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(value);

                while (matcher.find()) {

                    String numStr = matcher.group();

                    double num = Double.parseDouble(numStr);

                    numbers.add(num);
                    sum += num;

                    if (num % 2 == 0) {
                        evenNumbers.add(numStr);
                    } else {
                        oddNumbers.add(numStr);
                    }
                }
            }

            // SPECIAL CHARACTER
            else {

                specialCharacters.add(value);
            }
        }

        Collections.sort(numbers);

        List<String> sortedNumbers = new ArrayList<>();

        for (Double n : numbers) {

            if (n % 1 == 0) {
                sortedNumbers.add(String.valueOf(n.longValue()));
            } else {
                sortedNumbers.add(String.valueOf(n));
            }
        }

        SummaryDto summary = new SummaryDto();

        summary.setTotalElementsReceived(request.getData().size());

        summary.setValidElementsProcessed(uniqueValues.size());

        summary.setInvalidElementsIgnored(invalidCount);

        response.setSuccess(true);
        response.setRequestId(requestId);

        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);

        response.setAlphabetCount(alphabets.size());
        response.setNumberCount(numbers.size());
        response.setSpecialCharacterCount(specialCharacters.size());

        response.setSum(
                (sum % 1 == 0)
                        ? String.valueOf((long) sum)
                        : String.valueOf(sum)
        );

        response.setContainsDuplicates(containsDuplicates);

        response.setUniqueElementCount(uniqueValues.size());

        response.setAlphabetFrequency(alphabetFrequency);

        response.setSortedNumbers(sortedNumbers);

        response.setVowelCount(vowelCount);

        response.setLongestAlphabeticValue(longestAlphabeticValue);
        response.setShortestAlphabeticValue(shortestAlphabeticValue);

        if (!numbers.isEmpty()) {

            double max = Collections.max(numbers);
            double min = Collections.min(numbers);

            response.setLargestNumber(
                    max % 1 == 0
                            ? String.valueOf((long) max)
                            : String.valueOf(max)
            );

            response.setSmallestNumber(
                    min % 1 == 0
                            ? String.valueOf((long) min)
                            : String.valueOf(min)
            );
        }

        response.setSummary(summary);

        long endTime = System.currentTimeMillis();

        response.setProcessingTimeMs(endTime - startTime);

        return response;
    }
}