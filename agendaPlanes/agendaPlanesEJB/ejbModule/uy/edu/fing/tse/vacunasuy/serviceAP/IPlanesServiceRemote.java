package uy.edu.fing.tse.vacunasuy.serviceAP;

import java.util.List;

import uy.edu.fing.tse.vacunasuy.dtos.DisponibilidadDTO;
import uy.edu.fing.tse.vacunasuy.dtos.ReporteDTO;
import uy.edu.fing.tse.vacunasuy.entity.Agenda;
import uy.edu.fing.tse.vacunasuy.entity.Plan;



public interface IPlanesServiceRemote {
	List<Plan> getPlanesAll();
	Plan guardarPlan(Plan planSeleccionado);
	Plan getPlanLazyById(Integer planId);
	void eliminarPlan(Integer id);
	List<Agenda> getAgendasByPlanId(Integer id);
	Agenda getAgendaLazyById(Integer id);
	Agenda guardarAgenda(Agenda agendaSeleccionada);
	void eliminarAgenda(Integer id);
	List<DisponibilidadDTO> obtenerDisponibilidad(String ci, Integer enfermedadId,Integer vacunatorioId, Boolean horarioMatutino, Integer nroDosisDesde);
	int liberarDisponibilidad(Integer idDisponibilidad);
	List<ReporteDTO> getReporteStockH();
}
