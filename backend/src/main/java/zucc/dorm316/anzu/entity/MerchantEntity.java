package zucc.dorm316.anzu.entity;

import javax.persistence.*;

@Entity
@Table(name = "merchant", schema = "anzu", catalog = "")
public class MerchantEntity {
    private int id;
    private String account;
    private String password;
    private String merchantName;
    private double balance;
    private int adminFlag;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "merchant_name")
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Basic
    @Column(name = "balance")
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "admin_flag")
    public int getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(int adminFlag) {
        this.adminFlag = adminFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MerchantEntity that = (MerchantEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (adminFlag != that.adminFlag) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (merchantName != null ? !merchantName.equals(that.merchantName) : that.merchantName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        long temp;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (merchantName != null ? merchantName.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + adminFlag;
        return result;
    }
}
