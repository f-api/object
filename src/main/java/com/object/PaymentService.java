package com.object;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

public class PaymentService {

    @SneakyThrows
    public Payment prepare(String currency, Double foreignCurrencyAmount) {
        // 달러-원화 실시간 환율 가져오기
        URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = bufferedReader.lines().collect(Collectors.joining());
        bufferedReader.close();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Double> rates = objectMapper.convertValue(objectMapper.readTree(response).get("rates"), new TypeReference<>() {});
        Double exRate = rates.get("KRW");

        // 금액 계산
        Double convertedAmount = foreignCurrencyAmount * exRate;

        return new Payment(currency, foreignCurrencyAmount, exRate, convertedAmount);
    }
}
