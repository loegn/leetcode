package oneThousandAndOneHundredToTwoHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @date : 2019/08/27 16:46:06
 * @author: liangenmao
 */
public class InvalidTransactions1169 {
    private static class TransactionBean {
        private String name;
        private Integer time;
        private Integer amount;
        private String city;
        private boolean invalid = false;

        public TransactionBean(String name, Integer time, Integer amount, String city) {
            this.name = name;
            this.time = time;
            this.amount = amount;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public boolean isInvalid() {
            return invalid;
        }

        public void setInvalid(boolean invalid) {
            this.invalid = invalid;
        }

        @Override
        public String toString() {
            char separator = ',';
            return name + separator + time + separator + amount + separator + city;
        }
    }

    public List<String> result1(String[] transactions) {
        List<String> result = new ArrayList<>();
//        name,list sort by time
        List<TransactionBean> beanList = new ArrayList<>(transactions.length);
        for (String transaction : transactions) {
            String[] value = transaction.split(",");
            TransactionBean transactionBean = new TransactionBean(value[0], Integer.parseInt(value[1]),
                    Integer.parseInt(value[2]), value[3]);
            //可优化为Map<name，List<TransactionBean>>
            beanList.add(transactionBean);
        }
        beanList.sort(Comparator.comparingInt(TransactionBean::getTime));
        Queue<TransactionBean> queue = new LinkedList<>();
        for (TransactionBean transactionBean : beanList) {
            Integer time = transactionBean.getTime();
            while (!queue.isEmpty() && queue.peek().getTime() < time - 60) {
                queue.remove();
            }
            for (TransactionBean bean : queue) {
                if (Objects.equals(bean.getName(), transactionBean.getName())
                        && !Objects.equals(bean.getCity(), transactionBean.getCity())) {
                    bean.setInvalid(true);
                    transactionBean.setInvalid(true);
                }
            }
            queue.offer(transactionBean);
        }
        result = beanList.stream()
                .filter((transactionBean -> transactionBean.getAmount() >= 1000 || transactionBean.isInvalid()))
                .map(TransactionBean::toString)
                .collect(Collectors.toList());
        return result;
    }

    @Test
    public void invalidTransactions() {
        String[] transactions = {"alice,20,800,mtv", "alice,50,100,beijing"};
        Object result = result1(transactions);
        PrintUtils.print(result);
    }
}
