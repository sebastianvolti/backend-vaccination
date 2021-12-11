package uy.edu.fing.tse.core.reservas.dao.impl;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lte;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

import javax.ejb.Stateless;

import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;

import uy.edu.fing.tse.core.reservas.dao.IReservaDAO;
import uy.edu.fing.tse.core.reservas.dto.ReservaDTO;
import uy.edu.fing.tse.core.reservas.enums.ReservaStatus;

@Stateless
public class ReservaDAO implements IReservaDAO {

	@Override
	public void crearReserva(ReservaDTO reserva) {
		MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

		collection.insertOne(reserva);

		System.out.println("New Reserva: SOLICITUD=" + reserva.getSolicitud().getCodigo());
	}


	@Override
	public void actualizarReserva(ReservaDTO reserva) {
		MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

		collection.findOneAndReplace(and(eq("solicitud.codigo", reserva.getSolicitud().getCodigo()), eq("dosis", reserva.getDosis())), reserva);

		System.out.println("Reserva UPDATE: CODIGO=" + reserva.getSolicitud().getCodigo() + ", DOSIS=" + reserva.getDosis());
	}

	@Override
	public List<ReservaDTO> getReservasByCodigo(String codigo) {
		try {
			List<ReservaDTO> reservas = new ArrayList<>();

			MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

			Consumer<ReservaDTO> consumer = reserva -> reservas.add(reserva);

			collection.find(and(eq("solicitud.codigo", codigo))).forEach(consumer);;

			reservas.sort(new Comparator<ReservaDTO>() {
				@Override
				public int compare(ReservaDTO o1, ReservaDTO o2) {
					return o1.getDosis().compareTo(o2.getDosis());
				}
			});

			return reservas;

		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ReservaDTO getReservaByCodigoAndDosis(String codigo, Integer dosis) {
		try {
			MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

			ReservaDTO reserva = collection.find(and(eq("solicitud.codigo", codigo), eq("dosis", dosis))).first();

			return reserva;

		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ReservaDTO> getReservasByAgenda(Long idAgenda) {
		try {
			MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

			List<ReservaDTO> result = new ArrayList<>();

			Consumer<ReservaDTO> consumer = reserva -> result.add(reserva);


			return result;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return new ArrayList<>();
		}

	}

	@Override
	public Map<String, Long> getDateHistogram(boolean lastMonth){
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

			Comparator<String> comparator = new Comparator<String> () {
				@Override
				public int compare(String o1, String o2) {
					LocalDate date1 = LocalDate.parse(o1);
					LocalDate date2 = LocalDate.parse(o2);

					return date1.compareTo(date2);
				}
			};

			Map<String, Long> result = new TreeMap<>(comparator);

			Consumer<ReservaDTO> consumer = reserva -> {
				String day = reserva.getFecha().format(formatter);

				Long reservas = result.get(day);

				if(reservas == null) {
					reservas = 0L;
				}

				reservas = reservas + 1;
				result.put(day, reservas);
			};

			Bson filter;

			if(lastMonth) {
				LocalDateTime startTime = LocalDateTime.now().minusDays(30);
				filter = and(eq("status", ReservaStatus.DONE.name()), gte("fecha", startTime));
			}else {
				filter = eq("status", ReservaStatus.DONE.name());
			}

			collection.find(filter).forEach(consumer);

			return result;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return new HashMap<>();
		}
	}


	@Override
	public Map<String, Long> getDateHistogramVacunatorio(Long vacunatorioId){
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

			Map<String, Long> result = new HashMap<>();

			Consumer<ReservaDTO> consumer = reserva -> {
				String day = reserva.getFecha().format(formatter);

				Long reservas = result.get(day);

				if(reservas == null) {
					reservas = 0L;
				}else {
					reservas = reservas + 1;
				}

				result.put(day, reservas);
			};

			collection.find(and(eq("vacunatorioId", vacunatorioId), eq("status", ReservaStatus.DONE.name()))).forEach(consumer);

			return result;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return new HashMap<>();
		}
	}

	@Override
	public List<ReservaDTO> getReservasPendingByVacunatorioAndDay(Long idVacunatorio, LocalDate day){
		List<ReservaDTO> reservas = new ArrayList<>();

		MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

		Consumer<ReservaDTO> consumer = reserva -> reservas.add(reserva);

		LocalDateTime startTime = LocalDateTime.of(day, LocalTime.of(0, 0));
		LocalDateTime endTime = LocalDateTime.of(day, LocalTime.of(23, 59));

		System.out.println("startTime: " + startTime + ", endTime: " + endTime);

		Bson filter = and(eq("vacunatorioId", idVacunatorio), eq("status", ReservaStatus.PENDING.name()), gte("fecha", startTime), lte("fecha", endTime));

		System.out.println(filter.toString());
		collection.find(filter).forEach(consumer);

		System.out.println("RESULT: " + reservas + ", size: " + reservas.size());

		return reservas;
	}

	@Override
	public List<ReservaDTO> getReservasByVacunatorioAndDay(Long idVacunatorio, LocalDate day){
		List<ReservaDTO> reservas = new ArrayList<>();

		MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

		Consumer<ReservaDTO> consumer = reserva -> reservas.add(reserva);

		LocalDateTime startTime = LocalDateTime.of(day, LocalTime.of(0, 0));
		LocalDateTime endTime = LocalDateTime.of(day, LocalTime.of(23, 59));

		System.out.println("startTime: " + startTime + ", endTime: " + endTime);

		Bson filter = and(eq("vacunatorioId", idVacunatorio), gte("fecha", startTime), lte("fecha", endTime));

		System.out.println(filter.toString());
		collection.find(filter).forEach(consumer);

		System.out.println("RESULT: " + reservas + ", size: " + reservas.size());

		return reservas;
	}


	@Override
	public void eliminarReservasByCodigo(String codigo) {
		try {
			MongoCollection<ReservaDTO> collection = MongoConnection.getInstance().getReservasCollection();

			collection.deleteMany(eq("solicitud.codigo", codigo));
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}


}
