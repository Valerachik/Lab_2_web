package WebPrograming2Course.Services;
import WebPrograming2Course.DAO.PizzaOrderDAO;
import WebPrograming2Course.Entities.PizzaOrder;

import java.util.List;

public class PizzaOrderService {
    private PizzaOrderDAO pizzaOrderDAO = new PizzaOrderDAO();
    public List<PizzaOrder> getAllPizzaOrders() {
        return pizzaOrderDAO.GetAllOrders();
    }
    public void SavePizzaOrder(PizzaOrder order) {
        pizzaOrderDAO.SaveOrder(order);
    }
    public void UpdatePizzaOrder(PizzaOrder order) {
        pizzaOrderDAO.UpdateOrder(order);
    }
    public void deletePizzaOrder(int id) {
        pizzaOrderDAO.DeleteOrder(id);
    }
    public PizzaOrder GetPizzaOrderById(int id){
        return pizzaOrderDAO.GetOrderById(id);
    }
}
