<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>
    
   	<h1>Hello Java Web App 3.1</h1>
   	
   	<%@include file="includes/mensaje.jsp"%>
   	
   		<p class="h3 text-danger"><fmt:message key="menu.ejemplos" /></p>
	<p class="h3 text-danger"><fmt:message key="menu.inicio" /></p>
   	
   	
   	<ul class="list-group">
	  <li class="list-group-item"><a href="ejemplos/basico.jsp">Ejemplo Servlet Basico sin JSP</a></li>
	  <li class="list-group-item"><a href="ejemplos/basico2.jsp">Ejemplo Servlet Basico con JSP</a></li>
	  <li class="list-group-item"><a href="ejemplos/basico3.jsp">Ejemplo Servlet Response Content Type</a></li>
	  <li class="list-group-item"><a href="ejemplos/basico4.jsp">GET y POST</a></li>
	  <li class="list-group-item"><a href="nombres">Listado Nombres</a></li>
	  <li class="list-group-item">JSP</li>
	  <li class="list-group-item"><a href="i18n">i18n - multiidoma</a></li>
	  <li class="list-group-item"><a href="ejemplos/calculadora.jsp">Calculadora</a></li>
	  <li class="list-group-item"><a href="ejemplos/el.jsp">EL - Expression Language</a></li>
	  <li class="list-group-item"><a href="jstl">JSTL - JAva Server Tag Libraries</a></li>
	  <li class="list-group-item">Session</li>
	  <li class="list-group-item">Cookies</li>
	  <li class="list-group-item">Filtros</li>
	  <li class="list-group-item">Listeners o escuchadores de eventos</li>
	</ul>
    	    	
<%@include file="includes/footer.jsp"%>