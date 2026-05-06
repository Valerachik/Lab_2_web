package WebPrograming2Course.Services;
import WebPrograming2Course.DAO.PizzeriaDAO;
import WebPrograming2Course.Entities.Pizzeria;

import java.util.List;

public class PizzeriaService {
    private PizzeriaDAO pizzeriaDAO = new PizzeriaDAO();
    public List<Pizzeria> getAllPizzerias() {
        return pizzeriaDAO.GetAllPizzerias();
    }
    public void SavePizzeria(Pizzeria pizzeria) {
        pizzeriaDAO.SavePizzeria(pizzeria);
    }
    public void UpdatePizzeriaName(Pizzeria pizzeria) {
        pizzeriaDAO.UpdatePizzeriaName(pizzeria.getId(), pizzeria.getName());
    }
    public void deletePizzeria(int id) {
        pizzeriaDAO.DeletePizzeria(id);
    }
    public Pizzeria GetPizzeriaById(int id){
        return pizzeriaDAO.GetPizzeriaById(id);
    }
}
