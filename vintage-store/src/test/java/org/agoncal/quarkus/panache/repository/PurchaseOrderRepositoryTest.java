package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.inject.Inject;
import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.jpa.Customer;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Language;
import org.agoncal.quarkus.panache.model.OrderLine;
import org.agoncal.quarkus.panache.model.Publisher;
import org.agoncal.quarkus.panache.model.PurchaseOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PurchaseOrderRepositoryTest {

    @Inject
    CustomerRepository customerRepository;

    @Test
    @TestTransaction
    public void createAndFindPurchaseOrder() {
        Artist artist = new Artist("artist name", "artist bio");
        Publisher publisher = new Publisher("publisher name");
        Book book = new Book();
        book.title="book title";
        book.nbOfPages = 500;
        book.language = Language.FRENCH;
        book.price = BigDecimal.TEN;
        book.isbn = "isbn";

        // Set relationship:
        book.artist = artist;
        book.publisher = publisher;

        book.persist();

        Customer customer = new Customer("customer first name", "customer last name", "customer email");
        customerRepository.persist(customer);
        OrderLine orderLine= new OrderLine();
        orderLine.item = book;
        orderLine.quantity = 2;


        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.customer = customer;
        purchaseOrder.date = LocalDate.now();
        purchaseOrder.addOrderLine(orderLine);

        purchaseOrder.persist();
    }
}
//
