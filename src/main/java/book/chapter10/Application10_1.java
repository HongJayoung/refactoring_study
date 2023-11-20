package book.chapter10;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application10_1 {

    private static final Plan plan = new Plan();

    public static void main(String[] args) {
        PaymentRequest request = new PaymentRequest();

        LocalDate paymentDate = request.getPaymentDateTime().toLocalDate();
        int totalAmount = request.getAmount() * request.getQuantity();

        double charge;
        if (!paymentDate.isBefore(plan.getSummerStart()) && !paymentDate.isAfter(plan.getSummerEnd())) {
            charge = totalAmount * plan.getSummerRate();
        } else {
            charge = totalAmount * plan.getRegularRate() + plan.getRegularServiceCharge();
        }

        System.out.println("charge = " + charge);
    }
}

@Getter
class PaymentRequest {
    private final LocalDateTime paymentDateTime = LocalDateTime.now();
    private final int amount = 100000;
    private final int quantity = 10;
}

@Getter
class Plan {
    private final LocalDate summerStart = LocalDate.of(2023, 7, 1);
    private final LocalDate summerEnd = LocalDate.of(2023, 9, 30);
    private final double summerRate = 0.5;
    private final double regularRate = 0.95;
    private final int regularServiceCharge = 3000;
}