package com.java.financeprojectapp.servicelayer.services;

import java.util.List;

import com.java.financeprojectapp.businesslayer.implementations.LoanApplicationBusinessComponent;
import com.java.financeprojectapp.entities.LoanApplication;
import com.java.financeprojectapp.servicelayer.entities.*;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/loan/applications")
public class LoanApplicationService {

	public LoanApplicationBusinessComponent labo = new LoanApplicationBusinessComponent();

	public LoanApplicationService() {
		super();
	}

	public LoanApplicationService(LoanApplicationBusinessComponent labo) {
		super();
		this.labo = labo;
	}
	

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public ServiceResponse<List<LoanApplication>> retrieveAllLoanApplications() {
		try {
			List<LoanApplication> list = labo.getAll();
			return new ServiceResponse<List<LoanApplication>>("records found", 200, list);
		} catch (Exception e) {
			return new ServiceResponse<List<LoanApplication>>(e.getMessage(), 200, null);
		}
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Secured({Role.CUSTOMER})
	public ServiceResponse<LoanApplication> retrieveLoanApplicationById(@PathParam("id") int id) throws Exception {
		try {
			LoanApplication l = labo.getById(id);
			return new ServiceResponse<LoanApplication>("record found", 200, l);
		} catch (Exception e) {
			return new ServiceResponse<LoanApplication>(e.getMessage(), 500, null);
		}
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<LoanApplication>> retrieveLoanApplicationByDate(@QueryParam("date") String date) throws Exception {
		try {
			List<LoanApplication> list = labo.getLoanApplicationsByDate(date);
			return new ServiceResponse<List<LoanApplication>>("records found", 200, list);
		} catch (Exception e) {
			return new ServiceResponse<List<LoanApplication>>(e.getMessage(), 200, null);
		}
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> addCustomer(LoanApplication la) throws Exception {
		try {
			Boolean flag = labo.add(la);
			return new ServiceResponse<Boolean>("record added", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}
	
	@POST
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> deleteCustomer(@PathParam("id") int id) throws Exception {
		try {
			Boolean flag = labo.remove(id);
			return new ServiceResponse<Boolean>("record deleted", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}
	
	@POST
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> updateCustomer(@PathParam("id") int id, LoanApplication la) throws Exception {
		try {
			Boolean flag = labo.modify(id, la);
			return new ServiceResponse<Boolean>("record updated", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}
}
