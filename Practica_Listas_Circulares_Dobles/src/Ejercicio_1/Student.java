package Ejercicio_1;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Student {
    private int studentCode;
    private String studentName;

    private LocalDateTime paymentDate;
    private BigDecimal monthlyFee;

    public Student(int code, String name, LocalDateTime date, BigDecimal fee) {
        this.studentCode = code;
        this.studentName = name;
        this.paymentDate = date;
        this.monthlyFee = fee;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    @Override
    public String toString() {
        return "Student => studentCode=" + studentCode +
                ", studentName='" + studentName + '\'' +
                ", paymentDate=" + paymentDate + ", monthlyFee=" + monthlyFee;
    }
}
