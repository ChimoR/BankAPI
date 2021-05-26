package service;

import app.Initializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Утилитный класс для генерации свойст карты
 */
public class PropertyGenerator {

    /**
     * Метод генерирующий свойства карты
     * @param kindOfProperty вид свойства. CVV, number или  date
     * @return свойство в виде строки
     */
    public static String generateCardProperties(String kindOfProperty) {
        Random random = new Random();

        if (kindOfProperty.equals("CVV")) {
            StringBuilder sb = new StringBuilder(3);
            for (int i = 0; i < 3; i++) {
                int randomInt = 49 + (int) (random.nextFloat() * (57 - 49 + 1));
                sb.append((char) randomInt);
            }
            return sb.toString();
        }

        if (kindOfProperty.equals("number")) {
            StringBuilder sb = new StringBuilder(10);
            for (int i = 0; i < 10; i++) {
                int randomInt = 49 + (int) (random.nextFloat() * (57 - 49 + 1));
                sb.append((char) randomInt);
            }

            while (Initializer.existingCardNumbers.contains(sb.toString())) {
                sb = new StringBuilder(10);
                for (int i = 0; i < 10; i++) {
                    int randomInt = 49 + (int) (random.nextFloat() * (57 - 49 + 1));
                    sb.append((char) randomInt);
                }
            }
            Initializer.existingCardNumbers.add(sb.toString());
            return sb.toString();
        }

        if (kindOfProperty.equals("date")) {
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String dateString = format.format(date);
            StringBuilder sb = new StringBuilder(dateString);
            int newYear = Character.getNumericValue(dateString.charAt(dateString.length() - 1)) + 3;
            return sb.replace(sb.length() - 1, sb.length(), String.valueOf(newYear)).toString();
        }

        return "Invalid data";
    }
}
