<%@page import="com.ipartek.formacion.controller.InicioController"%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>

	estoy aqui
	<h1>${video.nombre}</h1>
	
	
	<div class="embed-responsive embed-responsive-16by9">
		
				<iframe class="embed-responsive-item"
				        src="https://www.youtube.com/embed/${video.codigo}" 
				        frameborder="0" 
				        allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
				        allowfullscreen></iframe>
			</div>        
		</div>
	</div>

	
	
	<div class="row">
		<div class="col">
		
			<%@include file="includes/mensaje.jsp"%>
			
			
			
			
		
			
			<form action="inicio" method="post" class="mb-2">
			<div class="form-group">
				
					<label for="categoria_id">Categoria:</label>
					<select name="categoria_id" id="categoria_id" class="form-control" disabled>
					  <c:forEach items="${categorias}" var="c">
					  		<option value="${c.id}" ${(c.id == video.categoria.id)?"selected":"" }> ${c.nombre}</option>					  	
					  </c:forEach>				  
					</select>
				</div>	
								
				<div class="form-group">
					<label for="usuario_id">Usuario:</label>
					<select name="usuario_id" id="usuario_id" class="form-control" disabled>
					
						<c:forEach items="${usuarios}" var="u">		
							<% //modificar video %>
							<c:if test="${video.id != -1}">
								<option value="${u.id}" ${(u.id == video.usuario.id)?"selected":"" }> ${u.nombre}</option>					  		
						  	</c:if>
						  	
						    <% //nuevo video %>
						 	<c:if test="${video.id == -1}">
						 		<option value="${u.id}" ${(u.id == sessionScope.usuario.id)?"selected":"" }> ${u.nombre}</option>					  		
						  	</c:if>
					   </c:forEach>
					</select>
				</div>
				<a href="inicio">
				<img src="resources/img/flecha_volver.png" width="50" height="50">
				</a>
				<a href="inicio">
				<img src="resources/img/like.png" width="50" height="50">
				</a>
			</form>
		</div>
		<div class="col">	
		
		
			
	
<%@include file="includes/footer.jsp"%>