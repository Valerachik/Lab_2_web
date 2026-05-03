package WebPrograming2Course;

import WebPrograming2Course.Services.CustomerService;
import WebPrograming2Course.Services.PizzaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import WebPrograming2Course.Entities.Pizza;
import WebPrograming2Course.Entities.Customer;

@WebServlet("/pizzas")
public class PizzaServlet extends HttpServlet {
    private PizzaService pizzaService;
    private CustomerService customerService;

    @Override
    public void init() {
        pizzaService = new PizzaService();
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deletePizza(request, response);
                    break;
                default:
                    showNewForm(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    // private void listPizzas(HttpServletRequest request, HttpServletResponse response)
   //         throws ServletException, IOException {
   //     List<Pizza> listPizza = pizzaService.getAllPizzas();
  //      request.setAttribute("listPizza", listPizza);
  //      request.getRequestDispatcher("/pizza-list.jsp").forward(request, response);
  //  }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer> listCustomers = customerService.getAllCustomers();
        request.setAttribute("listCustomers", listCustomers);
        request.getRequestDispatcher("/Create order.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Pizza existingPizza = pizzaService.GetPizzaById(id);
        request.setAttribute("pizza", existingPizza);

        List<Customer> listCustomers = customerService.getAllCustomers();
        request.setAttribute("listCustomers", listCustomers);

        request.getRequestDispatcher("/Create order.jsp").forward(request, response);
    }

    private void deletePizza(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        pizzaService.deletePizza(id);
        response.sendRedirect("pizzas");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String customerIdStr = request.getParameter("customer_id"); // Беремо ID замовника з форми

        Customer selectedCustomer = null;
        if (customerIdStr != null && !customerIdStr.isEmpty()) {
            int customerId = Integer.parseInt(customerIdStr);
            selectedCustomer = customerService.GetCustomerById(customerId); // Знаходимо замовника в БД
        }

        if (idStr == null || idStr.isEmpty()) {
            // Створення нової піци
            Pizza newPizza = new Pizza();
            newPizza.setName(name);
            newPizza.setCustomer(selectedCustomer); // Прив'язуємо замовника!
            pizzaService.SavePizza(newPizza);
        } else {
            int id = Integer.parseInt(idStr);
            pizzaService.UpdatePizzaName(id,name);
        }

        response.sendRedirect("pizzas");
    }
}