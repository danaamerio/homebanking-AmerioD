package com.ap.homebanking;
import com.ap.homebanking.Enum.CardColor;
import com.ap.homebanking.Enum.CardType;
import com.ap.homebanking.Enum.RoleType;
import com.ap.homebanking.Enum.TransactionType;
import com.ap.homebanking.models.*;
import com.ap.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return (args -> {

            Account account1 = new Account("VIN001", LocalDate.now(), 5000);
            Account account2 = new Account("VIN002", LocalDate.now(), 7500);

            Client client1 = new Client("Melba", "Morel", "melba@mindhub.com", "Mortgage", passwordEncoder.encode("1234"), RoleType.CLIENT);
            Client client2 = new Client("Dana","Amerio","danaamerio07@gmail.com", "personal",passwordEncoder.encode("4567"), RoleType.CLIENT);

            clientRepository.save(client1);
            clientRepository.save(client2);

            client1.addAccount(account1);
            client1.addAccount(account2);

            accountRepository.save(account1);
            accountRepository.save(account2);

            ///////////////////////////////////////////

            Transaction transaction1 = new Transaction(TransactionType.DEBITO, 100000.0, "pago alquiler", LocalDateTime.now());
            Transaction transaction2 = new Transaction(TransactionType.CREDITO, 150000.0, "sueldo", LocalDateTime.now());

            account1.addTransaction(transaction1);
            account1.addTransaction(transaction2);
            account2.addTransaction(transaction1);
            account2.addTransaction(transaction2);

            transactionRepository.save(transaction1);
            transactionRepository.save(transaction2);

            //////////////////////////////////////////////////////

            Loan loan1 = new Loan("Mortgage", 500000.00, List.of(12, 24, 36, 48, 60));
            Loan loan2 = new Loan("Personal", 100000.00, List.of(6, 12, 24));
            Loan loan3 = new Loan("Automotive", 300000.00, List.of(6, 12, 24, 36));

            loanRepository.save(loan1);
            loanRepository.save(loan2);
            loanRepository.save(loan3);

            ClientLoan clientLoan1 = new ClientLoan(400000.0, 48, client1, loan1);

            client1.addClientLoan(clientLoan1);

            clientRepository.save(client1);

            /////////////////////////////////////////

            Card card1 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.DEBITO, CardColor.GOLD, "4456 5544 6789 7123", "254", LocalDate.now(), LocalDate.now().plusYears(5));
            Card card2 = new Card(client1.getFirstName()+" "+client1.getLastName(), CardType.CREDITO, CardColor.TITANIUM, "7890 4244 7611 7895", "997", LocalDate.now(), LocalDate.now().plusYears(5));

            client1.addCard(card1);
            client1.addCard(card2);

            cardRepository.save(card1);
            cardRepository.save(card2);

        });

	}
}
