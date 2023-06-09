<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Inserisci Nuovo Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
  <div class="container">
    <spring:hasBindErrors name="insert_utente_attr">
      <div class="alert alert-danger" role="alert">
        Attenzione!! Sono presenti errori di validazione
      </div>
    </spring:hasBindErrors>

    <div class="alert alert-danger alert-dismissible fade show ${errorMessage == null ? 'd-none' : ''}" role="alert">
      ${errorMessage}
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <div class="card">
      <div class="card-header">
        <h5>ricarica/preleva</h5>
      </div>
      <div class="card-body">

        <form:form modelAttribute="credito_utente_attr" method="post" action="${pageContext.request.contextPath}/utente/ricarica/caricaCredit" novalidate="novalidate" class="row g-3">

          <div class="col-md-6">
            <label for="creditoResiduo" class="form-label">Credito da ricaricare/prelevare: <span class="text-danger"></span></label>

            <input type="number" name="creditoResiduo" id="creditoResiduo" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la ricarica" required>

          </div>



          <div class="col-12">
            <button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
            <input class="btn btn-outline-warning" type="reset" value="Ripulisci">
          </div>

        </form:form>


        <!-- end card-body -->
      </div>
      <!-- end card -->
    </div>


    <!-- end container -->
  </div>
</main>
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>
