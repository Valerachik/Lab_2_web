package WebPrograming2Course;
import WebPrograming2Course.Entities.*;
import WebPrograming2Course.Services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private PizzaOrderService orderService;
    private CustomerService customerService;
    private PizzeriaService pizzeriaService;
    private PizzaService pizzaService;

    @Override
    public void init() {
        orderService = new PizzaOrderService();
        customerService = new CustomerService();
        pizzeriaService = new PizzeriaService();
        pizzaService = new PizzaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "new":
                    showForm(request, response, null);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    PizzaOrder existingOrder = orderService.GetPizzaOrderById(id);
                    showForm(request, response, existingOrder);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    orderService.deletePizzaOrder(deleteId);
                    response.sendRedirect("orders");
                    break;
                default:
                    listOrders(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Сталася помилка при обробці запиту: " + ex.getMessage());
            request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<PizzaOrder> listOrders = orderService.getAllPizzaOrders();
        request.setAttribute("listOrders", listOrders);
        request.getRequestDispatcher("/MainMenu.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, PizzaOrder order)
            throws ServletException, IOException {
        request.setAttribute("order", order);
        request.setAttribute("listCustomers", customerService.getAllCustomers());
        request.setAttribute("listPizzerias", pizzeriaService.getAllPizzerias());
        request.setAttribute("listPizzas", pizzaService.getAllPizzas());
        request.getRequestDispatcher("/CreateOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            String idStr = request.getParameter("id");
            int customerId = Integer.parseInt(request.getParameter("customer_id"));
            int pizzeriaId = Integer.parseInt(request.getParameter("pizzeria_id"));
            String[] selectedPizzaIds = request.getParameterValues("pizza_ids");

            Customer customer = customerService.GetCustomerById(customerId);
            Pizzeria pizzeria = pizzeriaService.GetPizzeriaById(pizzeriaId);

            List<Pizza> selectedPizzas = new ArrayList<>();
            if (selectedPizzaIds != null) {
                for (String pId : selectedPizzaIds) {
                    selectedPizzas.add(pizzaService.GetPizzaById(Integer.parseInt(pId)));
                }
            }

            PizzaOrder order;
            if (idStr == null || idStr.isEmpty()) {
                order = new PizzaOrder();
            } else {
                order = orderService.GetPizzaOrderById(Integer.parseInt(idStr));
            }

            order.setCustomer(customer);
            order.setPizzeria(pizzeria);
            order.setPizzas(selectedPizzas);

            if (idStr == null || idStr.isEmpty()) {
                orderService.SavePizzaOrder(order);
            } else {
                orderService.UpdatePizzaOrder(order);
            }

            response.sendRedirect("orders");

        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Помилка збереження даних: Перевірте правильність введення.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}