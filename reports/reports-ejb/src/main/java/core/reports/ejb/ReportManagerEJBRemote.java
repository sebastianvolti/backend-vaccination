package core.reports.ejb;

import java.util.List;

import javax.ejb.Remote;

import core.reports.entities.AgendaPublicEntity;

@Remote
public interface ReportManagerEJBRemote {
	public List<AgendaPublicEntity> getPublicAvailableAgendas();
}
