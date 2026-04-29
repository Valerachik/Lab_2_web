package WebPrograming2Course.Services;
import WebPrograming2Course.Entities.Customer;
import WebPrograming2Course.DAO.CustomerDAO;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();
    public List<Customer> getAllPizzas() {
        return customerDAO.GetAllCustomers();
    }
    public void SavePizza(Customer customer) {
        customerDAO.SaveCustomer(customer);
    }
    public void UpdatePizzaName(Customer customer) {
        customerDAO.UpdateCustomerName(customer.getId(), customer.getName());
    }
    public void UpdatePizzaSurName(Customer customer) {
        customerDAO.UpdateCustomerSurName(customer.getId(), customer.getSurname());
    }
    public void UpdatePizzaPhone(Customer customer) {
        customerDAO.UpdateCustomerPhone(customer.getId(), customer.getPhone());
    }
    public void deletePizza(int id) {
        customerDAO.DeleteCustomer(id);
    }
}
