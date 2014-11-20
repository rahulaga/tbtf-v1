package com.irahul.tbtf.http.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.irahul.tbtf.service.exception.TBTFException;

/**
 * Return HTTP 409 with response body 
 * 
 * @author rahul
 *
 */
@Provider
@Component
public class TBTFExceptionMapper implements ExceptionMapper<TBTFException>{

	@Override
	public Response toResponse(TBTFException ex) {
		return Response.status(Status.CONFLICT).entity(new HttpError(ex)).build();
	}

}
