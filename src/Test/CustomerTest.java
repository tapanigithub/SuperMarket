import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer = new Customer("Jack");

    @Test
    @DisplayName("Money should be 100 at start")
    void testGetMoney() {
        assertEquals(customer.getMoney(),100);
    }

    @Test
    @DisplayName("Name should be Jack")
    void testGetName() {
        assertEquals(customer.name, customer.getName());
    }

    @Test
    @DisplayName("Name change should work")
    void testSetName() {
        customer.setName("John");
        assertEquals(customer.name, customer.getName());
    }

    @Test
    @DisplayName("Should afford 100")
    void testCanAfford() {
        assertTrue(customer.canAfford(100));
    }

    @Test
    @DisplayName("Should not afford 101")
    void testCantAfford() {
        assertFalse(customer.canAfford(101));
    }

    @Test
    @DisplayName("Transaction should work")
    void testTransaction() {
        customer.transaction(50); // 100 - 50
        assertEquals(customer.getMoney(), 50);
    }
}