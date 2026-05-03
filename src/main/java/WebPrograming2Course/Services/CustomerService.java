package WebPrograming2Course.Services;
import WebPrograming2Course.Entities.Customer;
import WebPrograming2Course.DAO.CustomerDAO;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();
    public List<Customer> getAllCustomers() {
        return customerDAO.GetAllCustomers();
    }
    public void SaveCustomer(Customer customer) {
        customerDAO.SaveCustomer(customer);
    }
    public void UpdateCustomerName(Customer customer) {
        customerDAO.UpdateCustomerName(customer.getId(), customer.getName());
    }
    public void UpdateCustomerSurName(Customer customer) {
        customerDAO.UpdateCustomerSurName(customer.getId(), customer.getSurname());
    }
    public void UpdateCustomerPhone(Customer customer) {
        customerDAO.UpdateCustomerPhone(customer.getId(), customer.getPhone());
    }
    public void deleteCustomer(int id) {
        customerDAO.DeleteCustomer(id);
    }
    public Customer GetCustomerById(int id){
        return customerDAO.GetCustomerById(id);
    }
}
