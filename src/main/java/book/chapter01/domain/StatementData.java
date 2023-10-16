package book.chapter01.domain;

import book.chapter01.dto.Invoice;
import book.chapter01.dto.Performance;
import book.chapter01.dto.Play;

import java.util.Arrays;

public class StatementData {

    private final Invoice invoice;
    private final Play[] plays;

    public StatementData(Invoice invoice, Play[] plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public Performance[] getPerformances(){
            return this.invoice.getPerformances();
    }

    public String getCustomer(){
            return this.invoice.getCustomer();
    }

    public int totalAmount() throws Exception {
        int result = 0;
        for (Performance perf : invoice.getPerformances()) {
            result += amountFor(perf);
        }
        return result;
    }

    public int totalVolumeCredits() {
        int result = 0;
        //refactor: volumeCredits 누적 부분 분리
        for (Performance perf : invoice.getPerformances()) {
            // 포인트 적립
            result += volumeCreditsFor(perf);
        }
        return result;
    }

    //refactor: 포인트 적립 계산 메서드 추출
    private int volumeCreditsFor(Performance performance) {
        return new PerformanceCalculator(performance, playFor(performance)).getVolumeCredits();
    }

    //refactor: play 변수 제거 (질의 함수로 변경)
    public Play playFor(Performance perf) {
        return Arrays.stream(plays)
                .filter(p -> p.getPlayId().equals(perf.getPlayId()))
                .findFirst()
                .get();
    }

    //refactor: switch 함수 추출
    public int amountFor(Performance performance) throws Exception {
        return new PerformanceCalculator(performance, playFor(performance)).getAmount();
    }
}
