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
    private int volumeCreditsFor(Performance aPerformance) {
        int result = 0;
        result += Math.max(aPerformance.getAudience() - 30, 0);

        // 희극 관객 5명마다 추가 포인트 제공
        if (playFor(aPerformance).getType().equals("comedy")) {
            result += Math.floor(aPerformance.getAudience() / 5);
        }
        return result;
    }

    //refactor: play 변수 제거 (질의 함수로 변경)
    public Play playFor(Performance perf) {
        return Arrays.stream(plays)
                .filter(p -> p.getPlayId().equals(perf.getPlayId()))
                .findFirst()
                .get();
    }

    //refactor: switch 함수 추출
    public int amountFor(Performance aPerformance) throws Exception {
        //refactor: 명시적인 이름 사용하기
        //totalAmout => result
        int result = 0;

        switch (playFor(aPerformance).getType()) {
            case "tragedy":
                result = 40000;
                if (aPerformance.getAudience() > 30) {
                    result += 1000 * (aPerformance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.getAudience() > 20) {
                    result += 10000 + 500 * (aPerformance.getAudience() - 20);
                }
                result += 300 * aPerformance.getAudience();
                break;
            default:
                throw new Exception(String.format("알 수 없는 장르: %s", playFor(aPerformance).getType()));
        }
        return result;
    }
}
