package com.example.checkstring.utils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StringUtils {

    private static final Logger LOGGER = Logger.getLogger("StringUtils");

    /**
     * Проверка корректности ввода скобок в строке
     *
     * @param str     строка подлежащая проверке
     * @param symbols скобки которые необходимо проерить
     *                (Указываются открывающаяся, затем закрывающаяся скобка.
     *                Если необходимо несколко, то резделяем ;)
     * @return true если скобки введены корректно
     */
    public static boolean check(String str, String symbols) {
        LOGGER.log(Level.INFO, "Input string {0}", str);
        if (isEmptyString(str)) {
            LOGGER.log(Level.INFO, "Input string is empty");
            return true;
        }
        if (isEmptyString(symbols)) {
            //если строка с допустимыми скобками пришла пустой, то подставляем скобки по умолчанию
            LOGGER.log(Level.INFO, "Setting default symbols");
            symbols = "{};()";
        }
        LOGGER.log(Level.INFO, "Setting symbols: {0}", symbols);
        //создаем стек, в который будем записывать открывающиеся скобки
        Stack<Character> hash = new Stack<>();
        //сохраняем мап с символами открытия/закрытия
        Map<Character, Character> map = getOpenAndCloseSymbols(symbols);
        //проходим каждый символ строки
        for (char symbol : str.toCharArray()) {
            if (map.containsValue(symbol)) {
                //если символ открывающий, добавляем в stack
                hash.push(symbol);
                continue;
            }
            if (map.containsKey(symbol)) {
                //если символ закрывающий
                if (!hash.isEmpty() && Objects.equals(map.get(symbol), hash.peek())) {
                    //закрывающий символ присутсвует в stack, то удаляем его
                    hash.pop();
                    continue;
                }
                //закрывающий символ не соответствует открывающему строка неверна
                LOGGER.log(Level.INFO, "String not correctly");
                return false;
            }
        }
        LOGGER.log(Level.INFO, "Hash: {0}", hash.stream().map(Object::toString).collect(Collectors.joining(" ")));
        return hash.isEmpty();//если stack пуст, то скобки в строке введены корректно
    }

    /**
     * @param symbols строка с допустимыми скобками
     * @return Мап с ключем закрывающего символа, значение открывающего
     */
    private static Map<Character, Character> getOpenAndCloseSymbols(String symbols) {
        return Arrays.stream(symbols.split(";")).
                filter(a -> a.length() == 2).
                collect(Collectors.toMap(a -> a.charAt(1), a -> a.charAt(0)));
    }

    /**
     * Проверка строки на пустоту
     *
     * @param str строка
     * @return true если строка пустая
     */
    private static boolean isEmptyString(String str) {
        return str == null || str.isEmpty();
    }
}