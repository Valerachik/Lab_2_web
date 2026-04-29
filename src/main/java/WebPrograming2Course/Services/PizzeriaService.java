package WebPrograming2Course.Services;
import WebPrograming2Course.DAO.PizzeriaDAO;
import WebPrograming2Course.Entities.Pizza;
import WebPrograming2Course.DAO.PizzaDAO;
import WebPrograming2Course.Entities.Pizzeria;

import java.util.List;

public class PizzeriaService {
    private PizzeriaDAO pizzeriaDAO = new PizzeriaDAO();
    public List<Pizzeria> getAllPizzas() {
        return pizzeriaDAO.GetAllPizzerias();
    }
    public void SavePizza(Pizzeria pizzeria) {
        pizzeriaDAO.SavePizzeria(pizzeria);
    }
    public void UpdatePizzaName(Pizzeria pizzeria) {
        pizzeriaDAO.UpdatePizzeriaName(pizzeria.getId(), pizzeria.getName());
    }
    public void deletePizza(int id) {
        pizzeriaDAO.DeletePizzeria(id);
    }
}
