package com.object;

public class ObjectApplication {

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare("USD", 100.0);
        System.out.println(payment);
    }
}
