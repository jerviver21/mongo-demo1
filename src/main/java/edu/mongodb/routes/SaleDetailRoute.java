package edu.mongodb.routes;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleDetailRoute {
	public static final String CLINIC_PATH = "/clinics";
	public static final String CLINIC_ID_PATH = "/{clinicId}";
	public static final String PATIENT_PATH = "/patients";
	public static final String PATIENT_ID_PATH = "/{patientId}";
	public static final String SALE_PATH = "/sales";
	public static final String SALE_ID_PATH = "/{saleId}";
	public static final String DETAIL_PATH = "/details";
	public static final String DETAIL_ID_PATH = "/{detailId}";
	
	
	/*@Bean
	RouterFunction<ServerResponse> detailRoutes(SaleHandler handler ) {
		//The order is important to avoid confuse {id} with a route
		return RouterFunctions
				.route(RequestPredicates.POST(
						CLINIC_PATH+
						CLINIC_ID_PATH+
						PATIENT_PATH+
						PATIENT_ID_PATH+
						SALE_PATH+
						SALE_ID_PATH+
						DETAIL_PATH+
						DETAIL_ID_PATH)
						.and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), handler::save);
	}*/
}
