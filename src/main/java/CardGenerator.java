import java.util.Random;

/**
 * Created by RuLemur on 29.10.2017 in 18:55.
 * AccountsValidator
 */
public class CardGenerator {

    public static final String[] VISA_PREFIX_LIST = new String[]{"4539",
            "4556", "4916", "4532", "4929", "40240071", "4485", "4716", "4"};

    public static final String[] MASTERCARD_PREFIX_LIST = new String[]{"51",
            "52", "53", "54", "55"};

    public static final String[] SBERBANK_PREFIX_LIST = new String[]{"427683",
            "63900", "67758", "427901", "54693", "427644", "427601", "427901", "427631"};

    public static String generateCardNumber(String[] prefixList) {
        int randomArrayIndex = (int) Math.floor(Math.random() * prefixList.length);
        String cardNum = prefixList[randomArrayIndex];
        Random random = new Random();
        int numLength = 15 - cardNum.length(); // 15 - длинна карты минус контрольное число

        for (int i = 0; i < numLength; i++) {
            cardNum += random.nextInt(10);
        }
        int digit = 10 - getCheckNumber(cardNum);
        cardNum += digit == 10 ? 0 : digit;

        return cardNum;
    }

    private static int getCheckNumber(String cardNum) { //получаем контрольное число, используя алгоритм Луна
        char[] list = cardNum.toCharArray();
        int[] digits = new int[cardNum.length()];

        for (int i = 0; i < cardNum.length(); i++) {
            digits[i] = list[i] - '0'; //конвертируем массив символов в массив чисел
        }

        int sum = 0;

        for (int i = 0; i < digits.length; i++) {
            if (i % 2 == 0) {
                sum += (digits[i] * 2) > 9 ? (digits[i] * 2 - 9) : (digits[i] * 2);
            } else {
                sum += digits[i];
            }
        }
        return sum % 10;
    }

    public static boolean checkCardNumber(String cardNum) {
        char[] list = cardNum.toCharArray();
        int[] digits = new int[cardNum.length()];

        for (int i = 0; i < cardNum.length(); i++) {
            digits[i] = list[i] - '0';
        }

        int sum = 0;
        int length = digits.length;
        for (int i = 0; i < length; i++) {

            // get digits in reverse order
            int digit = digits[length - i - 1];

            // every 2nd number multiply with 2
            if (i % 2 == 1) {
                digit *= 2;
            }
            sum += digit > 9 ? digit - 9 : digit;
        }
        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        String card = generateCardNumber(SBERBANK_PREFIX_LIST);
        System.out.println(card);
        System.out.println(checkCardNumber(card));

    }


}
