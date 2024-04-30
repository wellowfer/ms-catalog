package br.com.fiap.catalog.main.utils;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Util {
    public static String toCpf(String cpf) {
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }

    public static String fromCpf(String cpf) {
        return cpf.replaceAll("\\.", "").replaceAll("-","");
    }

    public static Map<String, List<String>> errorBody(List<String> list) {
        Map<String, List<String>> body = new HashMap<>();
        body.put("errors", list);
        return body;
    }

    public static List<String> bindingResultErrors(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
