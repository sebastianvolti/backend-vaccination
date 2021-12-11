package core.reports.ejb;

import java.util.List;

import javax.ejb.Local;
import core.reports.entities.AgendaPublicEntity;

@Local
public interface ReportManagerEJBLocal {
 	public List<AgendaPublicEntity> getPublicAvailableAgendas();
}
