package core.reports.ejb;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import core.reports.entities.AgendaPublicEntity;


@Stateless
public class ReportManagerEJB implements ReportManagerEJBLocal, ReportManagerEJBRemote {
	
	
    /**
     * Default constructor. 
     */
    public ReportManagerEJB() {
    }
    
	@Override
	public List<AgendaPublicEntity> getPublicAvailableAgendas() {
		List<AgendaPublicEntity> mockAgendaList = new ArrayList<>();
		mockAgendaList.add(new AgendaPublicEntity("AG1234", "Montevideo", "Hospital Clínicas", (long) 10500, "18-30", "Sinovac"));
		mockAgendaList.add(new AgendaPublicEntity("AG2234", "Montevideo", "Antel Arena", (long) 8000, "18-30", "Sinovac"));
		mockAgendaList.add(new AgendaPublicEntity("AG3234", "Montevideo", "Palacio Peñarol", (long) 5500, "50-70", "Pizer"));
		mockAgendaList.add(new AgendaPublicEntity("AG4234", "Montevideo", "Facultad de Ingeniería", (long) 1000, "70-90", "Pizer"));
		mockAgendaList.add(new AgendaPublicEntity("AG5234", "Maldonado", "Hotel Argentino", (long) 600, "18-30", "Sinovac"));
		mockAgendaList.add(new AgendaPublicEntity("AG6234", "Maldonado", "Terminal Pan de Azucar", (long) 500, "18-30", "Sinovac"));
		mockAgendaList.add(new AgendaPublicEntity("AG7234", "Canelones", "Campeón Del Siglo", (long) 700, "18-30", "Sinovac"));
		mockAgendaList.add(new AgendaPublicEntity("AG8234", "Canelones", "Colegio Medanos", (long) 800, "18-30", "Sinovac"));
		mockAgendaList.add(new AgendaPublicEntity("AG9234", "Canelones", "Colegio Sagrado", (long) 7900, "70-90", "Pizer"));
		mockAgendaList.add(new AgendaPublicEntity("AG0234", "Canelones", "Iglesia Canaria", (long) 100, "18-30", "Sinovac"));
		return mockAgendaList;
	
	}
	
	
}
