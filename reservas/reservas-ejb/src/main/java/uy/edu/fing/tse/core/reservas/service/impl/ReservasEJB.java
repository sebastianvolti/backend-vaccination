package uy.edu.fing.tse.core.reservas.service.impl;

import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;

import uy.edu.fing.tse.core.reservas.dao.IReservaDAO;
import uy.edu.fing.tse.core.reservas.dao.ISheetDAO;
import uy.edu.fing.tse.core.reservas.dao.ISolicitudesDAO;
import uy.edu.fing.tse.core.reservas.dto.DateHistorgramDTO;
import uy.edu.fing.tse.core.reservas.dto.ReservaDTO;
import uy.edu.fing.tse.core.reservas.dto.ReservaVacunatorioDTO;
import uy.edu.fing.tse.core.reservas.dto.ResultReservas;
import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;
import uy.edu.fing.tse.core.reservas.enums.Horario;
import uy.edu.fing.tse.core.reservas.enums.ReservaStatus;
import uy.edu.fing.tse.core.reservas.enums.SolicitudStatus;
import uy.edu.fing.tse.core.reservas.service.IReservasEJB;
import uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceRemote;

@Stateless
public class ReservasEJB implements IReservasEJB {
	private static final DateTimeFormatter parseDayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final DateTimeFormatter agendaDayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private static final String HIDDEN_SHEET_NAME = "hiddenSheet";

	private static final String AGENDA_SHEET_NAME = "Agenda";


	private static final Random random = new Random();

	@EJB
	ISolicitudesDAO solicitudesDAO;

	@EJB
	IReservaDAO reservaDAO;

	@EJB
	ISheetDAO sheetDAO;



	@Override
	public int liberarCupos(Long idAgenda, Long cantCupos) {
		List<SolicitudDTO> solicitudes = this.solicitudesDAO.getSolicitudesPendientesPorAgendaYEstado(idAgenda, SolicitudStatus.PENDING.name());


		int cuposLiberados = 0;

		while(cantCupos > 0 && !solicitudes.isEmpty()) {
			int cupo = random.nextInt(solicitudes.size());

			SolicitudDTO solicitud = solicitudes.get(cupo);
			solicitud.setStatus(SolicitudStatus.ASSIGNED);

			ReservaDTO reserva = new ReservaDTO();
			reserva.setDosis(1);
			reserva.setSolicitud(solicitud);
			reserva.setStatus(ReservaStatus.PENDING);

			ReservaVacunatorioDTO slot = this.getReservaVacunatorio(solicitud.getIdVacunatorio(), solicitud.getHorario());

			reserva.setFecha(slot.getFecha());
			reserva.setVacunatorioId(slot.getVacunatorioId());


			this.solicitudesDAO.actualizarSolicitud(solicitud);
			this.reservaDAO.crearReserva(reserva);
			this.sendReservaEmail(solicitud, reserva);

			cantCupos--;
			solicitudes.remove(cupo);
			cuposLiberados++;
		}


		return cuposLiberados;
	}

	@Override
	public void sendReservaEmail(SolicitudDTO solicitud, ReservaDTO reserva) {
		try {
			// Recipient's email ID needs to be mentioned.
			String to = solicitud.getEmail().trim();

			// Sender's email ID needs to be mentioned
			String from = "diego.silva.tse@gmail.com";

			// Assuming you are sending email from through gmails smtp
			String host = "smtp.gmail.com";

			// Get system properties
			Properties properties = System.getProperties();

			// Setup mail server
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.auth", "true");

			// Get the Session object.// and pass username and password
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(from, "tsepassword1");

				}
			});

			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

				// Set Subject: header field
				message.setSubject("Confirmación de Reserva - TSE");

				String msg = "Esta es una confirmación de su reserva de vacunación.\n\n";
				msg += "Código: " + solicitud.getCodigo();

				IVacunatoriosServiceRemote service = this.getVacunatoriosRemote();
				msg += "\nLugar: " + service.getVacunatorioLazyById(reserva.getVacunatorioId().intValue()).getNombre();

				msg+= "\nHora: " + reserva.getFecha();

				// Now set the actual message
				message.setText(msg);

				System.out.println("sending...");
				// Send message
				Transport.send(message);
				System.out.println("Sent message successfully....");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public List<ReservaDTO> getReservasByCodigo(String codigo){
		return this.reservaDAO.getReservasByCodigo(codigo);
	}

	private IVacunatoriosServiceRemote getVacunatoriosRemote() {
		Properties props = new Properties();

		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");   //$NON-NLS-1$
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");   //$NON-NLS-1$
		props.put(Context.PROVIDER_URL,"http-remoting://core-07.web.elasticloud.uy:80");
		props.put(Context.SECURITY_PRINCIPAL, "backoffice"); //$NON-NLS-1$
		props.put(Context.SECURITY_CREDENTIALS, "backoffice"); //$NON-NLS-1$

		Context ctx;
		try {
			ctx = new InitialContext(props);

			IVacunatoriosServiceRemote service =  (IVacunatoriosServiceRemote)ctx.lookup("ejb:nodosPerifericos/nodosPerifericosEJB/VacunatoriosService!uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceRemote"); //$NON-NLS-1$
			return service;

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


	public ReservaVacunatorioDTO getReservaVacunatorio(Long idVacunatorio, Horario horario) {
		//TODO: Para el prototipo mockeamos esta funcion, cuando agreguemos los vacunatorios hay que expandir esto.
		//El mock devuelve una fecha y hora pseudo random en el horario de preferencia.

		LocalDateTime targetDate = this.getStartDateTime(horario)
				.plusDays(random.nextInt(7) + 1)
				.plusHours(random.nextInt(5) + 1)
				.plusMinutes(random.nextInt(59) + 1);

		if(targetDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			targetDate.plusDays(1l); //no se vacunan los domingos
		}

		LocalDateTime timeSlot = targetDate.truncatedTo(ChronoUnit.HOURS)
				.plusMinutes(15 * (targetDate.getMinute() / 15));

		return new ReservaVacunatorioDTO(timeSlot, idVacunatorio);
	}

	private LocalDateTime getStartDateTime(Horario horario) {
		if(horario == Horario.MATUTINO) {
			return LocalDateTime.now().withHour(8).withMinute(0).withSecond(0).withNano(0);
		}else {
			return LocalDateTime.now().withHour(14).withMinute(0).withSecond(0).withNano(0);
		}
	}

	@Override
	public ResultReservas getReservasByAgenda(Long idAgenda) {
		return new ResultReservas(this.reservaDAO.getReservasByAgenda(idAgenda));
	}

	@Override
	public DateHistorgramDTO getDateHistogram() {
		DateHistorgramDTO histogram = new DateHistorgramDTO();

		Map<String, Long> result = this.reservaDAO.getDateHistogram(false);

		histogram.setHistogram(result);

		return histogram;
	}

	@Override
	public DateHistorgramDTO getDateHistogramVacunatorio(Long idVacunatorio) {
		DateHistorgramDTO histogram = new DateHistorgramDTO();

		Map<String, Long> result = this.reservaDAO.getDateHistogramVacunatorio(idVacunatorio);

		histogram.setHistogram(result);

		return histogram;
	}

	@Override
	public List<ReservaDTO> getReservasByVacunatorioAndDay(Long idVacunatorio, String day){
		LocalDate parsedDate = LocalDate.parse(day, parseDayFormatter);

		return this.reservaDAO.getReservasByVacunatorioAndDay(idVacunatorio, parsedDate);
	}

	@Override
	public XSSFWorkbook buildDownloadEmptyExcel(Long idVacunatorio, String day) {

		LocalDate parsedDate = LocalDate.parse(day, parseDayFormatter);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet hiddenSheet = workbook.createSheet(HIDDEN_SHEET_NAME);
		XSSFSheet agendaSheet = workbook.createSheet(AGENDA_SHEET_NAME);

		List<ReservaDTO> reservas = this.reservaDAO.getReservasPendingByVacunatorioAndDay(idVacunatorio, parsedDate);

		String dayString = parsedDate.format(agendaDayFormatter);
		this.populateAgendaHeaders(agendaSheet, dayString);

		this.populateAgendaSheet(agendaSheet, reservas);

		String sheetId = UUID.randomUUID().toString();

		String hash = this.calculateHashFromSheet(agendaSheet, sheetId);

		this.populateHiddenSheet(hiddenSheet, sheetId, hash);

		workbook.getSheetAt(0).setSelected(false);
		workbook.setActiveSheet(1);
		workbook.setSheetHidden(0, true);

		this.sheetDAO.createSession(sheetId, hash);

		return workbook;
	}

	private void populateAgendaHeaders(XSSFSheet agendaSheet, String day) {
		XSSFRow headers = agendaSheet.createRow(0);

		headers.createCell(0).setCellValue(day);
		headers.createCell(1).setCellValue("Codigo");
		headers.createCell(2).setCellValue("Cedula");
		headers.createCell(3).setCellValue("Dosis");
		headers.createCell(4).setCellValue("Vacunado/a");
	}

	private void populateAgendaSheet(XSSFSheet agendaSheet, List<ReservaDTO> reservas) {
		int rowNum = 1;

		for(ReservaDTO reserva : reservas) {
			XSSFRow row = agendaSheet.createRow(rowNum++);

			String ci = reserva.getSolicitud().getCi();
			String codigo = reserva.getSolicitud().getCodigo();
			String dosis = reserva.getDosis().toString();

			row.createCell(1).setCellValue(codigo);
			row.createCell(2).setCellValue(ci);
			row.createCell(3).setCellValue(dosis);
			row.createCell(4).setCellValue(false);
		}
	}

	private void populateHiddenSheet(XSSFSheet hiddenSheet, String sheetId, String hash) {

		XSSFRow row = hiddenSheet.createRow(0);

		row.createCell(0).setCellValue(sheetId);
		row.createCell(1).setCellValue(hash);
	}

	@Override
	public void parseAndExecutoUploadExcel(XSSFWorkbook workbook, Long idVacunatorio) throws Exception {
		XSSFSheet hiddenSheet = workbook.getSheet(HIDDEN_SHEET_NAME);
		XSSFSheet agendaSheet = workbook.getSheet(AGENDA_SHEET_NAME);

		String sheetId = this.getSheetIdFromSheet(hiddenSheet);

		String sheetHash = this.getHashFromSheet(hiddenSheet);

		String calculatedHash = this.calculateHashFromSheet(agendaSheet, sheetId);

		String storedHash = this.sheetDAO.getSession(sheetId);

		if(sheetHash.equals(calculatedHash) && calculatedHash.equals(storedHash)){
			this.executeSheetUpdate(agendaSheet, idVacunatorio);
		}else {
			System.out.println("Sheet inválida. sheetHash: " + sheetHash + ", calculatedHash: " + calculatedHash + ", storedHash: " + storedHash);
			throw new Exception("Invalid sheet");
		}

	}

	private void executeSheetUpdate(XSSFSheet agendaSheet, Long idVacunatorio) {
		int rowIndex = 1;
		boolean seguir = true;

		do {
			try {
				XSSFRow row = agendaSheet.getRow(rowIndex++);

				if(row == null) {
					seguir = false;
				}else {
					String codigo = row.getCell(1).getStringCellValue().trim();

					if(Strings.isNullOrEmpty(codigo)) {
						seguir = false;
					}else {
						String ci = row.getCell(2).getStringCellValue().trim();
						String dosis = row.getCell(3).getStringCellValue().trim();
						XSSFCell vacunadoCell = row.getCell(4);
						vacunadoCell.setCellType(CellType.STRING);
						String vacunado = row.getCell(4).getStringCellValue().trim();

						if(this.isVacunado(vacunado)) {
							ReservaDTO reserva = this.reservaDAO.getReservaByCodigoAndDosis(codigo, Integer.valueOf(dosis));

							reserva.setStatus(ReservaStatus.DONE);

							this.reservaDAO.actualizarReserva(reserva);

							ReservaDTO nextReserva = this.reservaDAO.getReservaByCodigoAndDosis(codigo, Integer.valueOf(dosis)+1);

							if(nextReserva == null) {
								SolicitudDTO solicitud = reserva.getSolicitud();
								solicitud.setStatus(SolicitudStatus.DONE);
								this.solicitudesDAO.actualizarSolicitud(solicitud);
							}
						}
					}
				}
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		} while(seguir);
	}

	private boolean isVacunado(String vacunado) {
		return !(Strings.isNullOrEmpty(vacunado) || vacunado.equalsIgnoreCase("false") || vacunado.equals("0") || vacunado.equalsIgnoreCase("no"));
	}

	private String calculateHashFromSheet(XSSFSheet agendaSheet, String sheetId) {
		int rowIndex = 1;
		boolean seguir = true;

		String concat = "";

		String day = agendaSheet.getRow(0).getCell(0).getStringCellValue();

		do {
			XSSFRow row = agendaSheet.getRow(rowIndex++);

			if(row == null) {
				seguir = false;
			}else {
				String codigo = row.getCell(1).getStringCellValue().trim();

				if(Strings.isNullOrEmpty(codigo)) {
					seguir = false;
				}else {
					String ci = row.getCell(2).getStringCellValue().trim();
					String dosis = row.getCell(3).getStringCellValue().trim();

					concat += codigo + ci + dosis;
				}
			}
		} while(seguir);


		return Hashing.sha256().hashString(concat + day + sheetId, StandardCharsets.UTF_8).toString();
	}

	private String getSheetIdFromSheet(XSSFSheet hiddenSheet) {
		return hiddenSheet.getRow(0).getCell(0).getStringCellValue();
	}

	private String getHashFromSheet(XSSFSheet hiddenSheet) {
		return hiddenSheet.getRow(0).getCell(1).getStringCellValue();
	}


}
