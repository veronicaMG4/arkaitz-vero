package com.ipartek.formacion.controller.backoffice;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.ipartek.formacion.controller.pojo.Alert;
import com.ipartek.formacion.model.dao.UsuarioDAO;
import com.ipartek.formacion.model.pojo.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/backoffice/usuario")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_INDEX = "usuario/index.jsp";
	public static final String VIEW_FORM = "usuario/formulario.jsp";
	public static String view = VIEW_INDEX;

	public static final String OP_LISTAR = "0";
	public static final String OP_GUARDAR = "1";
	public static final String OP_BUSCAR = "2";
	public static final String OP_NUEVO = "3";
	public static final String OP_ELIMINAR = "4";
	public static final String OP_DETALLE = "5";

	private static final UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();

	private Validator validator;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if (op == null) {
			op = OP_LISTAR;
		}

		switch (op) {
		case OP_DETALLE:
			detalle(request, response);
			break;

		case OP_BUSCAR:
			buscar(request, response);
			break;

		case OP_GUARDAR:
			guardar(request, response);
			break;

		case OP_ELIMINAR:
			eliminar(request, response);
			break;

		case OP_NUEVO:
			nuevo(request, response);
			break;

		default:
			listar(request, response);
			break;
		}

		request.getRequestDispatcher(view).forward(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("usuarioEditar", new Usuario());
		view = VIEW_FORM;
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) {

		String nombreBuscar = request.getParameter("nombreBuscar");
		request.setAttribute("usuarios", usuarioDAO.getAllByNombre(nombreBuscar));
		view = VIEW_INDEX;
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {

		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);

		if (usuarioDAO.delete(id)) {
			request.setAttribute("mensaje", new Alert("success", "Registro Eliminado"));
		} else {
			request.setAttribute("mensaje", new Alert("danger", "ERROR, no se pudo eliminar"));
		}

		listar(request, response);
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) {

		String sid = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String contrasenya = request.getParameter("contrasenya");

		Usuario u = new Usuario();
		u.setId(Integer.parseInt(sid));
		u.setNombre(nombre);
		u.setContrasenya(contrasenya);

		Set<ConstraintViolation<Usuario>> violations = validator.validate(u);
		if (violations.isEmpty()) {

			try {

				if (u.getId() == -1) {
					usuarioDAO.crear(u);
				} else {
					usuarioDAO.modificar(u);
				}
				request.setAttribute("mensaje", new Alert("success", "Registro creado con exito"));

			} catch (Exception e) {

				request.setAttribute("mensaje", new Alert("danger", "Tenemos un problema, el codigo ya existe"));
			}

		} else { // hay violaciones de las validaciones

			String mensaje = "";

			for (ConstraintViolation<Usuario> violation : violations) {
				mensaje += violation.getPropertyPath() + ": " + violation.getMessage() + "<br>";
			}
			request.setAttribute("mensaje", new Alert("warning", mensaje));
		}
		request.setAttribute("usuarioEditar", u);
		view = VIEW_FORM;
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {

		String sVisible = request.getParameter("visible");

		if (sVisible != null) {
			boolean visible = Boolean.parseBoolean(sVisible);
			request.setAttribute("usuarios", usuarioDAO.getAllVisible(visible));
		} else {
			request.setAttribute("usuarios", usuarioDAO.getAll());
		}
		view = VIEW_INDEX;
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {

		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);

		Usuario u = usuarioDAO.getById(id);
		request.setAttribute("usuarioEditar", u);
		view = VIEW_FORM;
	}
}