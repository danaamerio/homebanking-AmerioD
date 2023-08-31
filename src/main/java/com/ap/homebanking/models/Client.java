package com.ap.homebanking.models;
import com.ap.homebanking.Enum.RoleType;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String loan;
    private String password;
    private RoleType role;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy="client",fetch = FetchType.EAGER)
    private Set<ClientLoan> loans = new HashSet<>();


    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();
    public Client(){}

    public Client(String firstName, String lastName, String email, String loan, String password, RoleType role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.loan = loan;
        this.password = password;
        this.role = role;

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<ClientLoan> getLoans() {
        return loans;
    }

    public void setLoans(Set<ClientLoan> loans) {
        this.loans = loans;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public void addAccount (Account account){
        account.setClient(this);
        accounts.add(account);
    }

    public void addClientLoan (ClientLoan clientLoan){
        clientLoan.setClient(this);
        loans.add(clientLoan);
    }

    public void addCard(Card card){
        card.setClient(this);
        cards.add(card);
    }

    public void addAccounts(Account accountnew) {
        accountnew.setClient(this);
        accounts.add(accountnew);
    }
}
