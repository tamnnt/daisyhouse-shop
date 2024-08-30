package LifeLeopard.TeamShop.Models;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "table_accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Account_id")
    private int AccountId;


//    @Column(name = "Username")
    @Column(unique = true,name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "Role_id")
    private Roles roles;

    @Column(name = "Status")
    private int status;

    @Column(name = "Verification_code")
    String verificationCode;
    @Column(name = "reset_pass_code")
    String resetPassCode;

    public Accounts() {

    }

    public Accounts(int accountId, String username, String password, Roles roles, int status) {
        this.AccountId = accountId;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status = status;
    }

    public Accounts(String username, String password, Roles roles, int status ,String verificationCode) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.status = status;
        this.verificationCode = verificationCode;
    }

    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getResetPassCode() {
        return resetPassCode;
    }

    public void setResetPassCode(String resetPassCode) {
        this.resetPassCode = resetPassCode;
    }

    public boolean isStatus(){
        if(this.status == 1 ){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "AccountId=" + AccountId +
                ", username='" +  username + '\'' +
                ", password='" + password + '\'' +
                ", rolesId=" + roles.getRoleId() +
                ", rolesName=" + roles.getRoleName() +
                ", status=" + status +
                '}';
    }
}
