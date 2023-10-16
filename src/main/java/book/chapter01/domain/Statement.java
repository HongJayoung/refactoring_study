package book.chapter01.domain;

import book.chapter01.dto.Invoice;
import book.chapter01.dto.Performance;
import book.chapter01.dto.Play;
import lombok.RequiredArgsConstructor;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

@RequiredArgsConstructor
public class Statement {

    private StatementData statementData;

    public Statement(StatementData statementData) {
        this.statementData = statementData;
    }

    //refactor: 명세서 클래스 추출
    public String readPlainText() throws Exception {
        String result = String.format("청구 내역 (고객명: %s)\n", statementData.getCustomer());

        for (Performance perf : statementData.getPerformances()) {
            // 청구 내역 출력
            result +=
                    String.format(
                            "%15s:%12s%4s석\n",
                            statementData.playFor(perf).getName(), usd(statementData.amountFor(perf)), perf.getAudience());
        }

        result += String.format("총액: %s\n", usd(statementData.totalAmount()));
        result += String.format("적립 포인트: %s점\n", statementData.totalVolumeCredits());
        return result;

    }

    //refactor: format 임시변수 함수 추출, 메서드 이름 변경
    private static String usd(long aNumber) {
        NumberFormat usdFormatter = NumberFormat.getCurrencyInstance(new Locale("en-US"));
        usdFormatter.setCurrency(Currency.getInstance("USD"));
        usdFormatter.setMinimumFractionDigits(2);

        return usdFormatter.format(aNumber / 100);
    }
}
