package com.java.financeprojectapp.servicelayer.services;

import java.util.List;

import com.java.financeprojectapp.businesslayer.implementations.LoanBusinessComponent;
import com.java.financeprojectapp.entities.Loan;
import com.java.financeprojectapp.servicelayer.entities.ServiceResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/loans")
public class LoanService {

	public LoanBusinessComponent lbo = new LoanBusinessComponent();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<Loan>> retrieveAllLoans() {
		try {
			List<Loan> list = lbo.getAll();
			return new ServiceResponse<List<Loan>>("records found", 200, list);
		} catch (Exception e) {
			return new ServiceResponse<List<Loan>>(e.getMessage(), 200, null);
		}
	}
	
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Loan> retrieveLoanById(@PathParam("id") int id) throws Exception {
		try {
			Loan l = lbo.getById(id);
			return new ServiceResponse<Loan>("record found", 200, l);
		} catch (Exception e) {
			return new ServiceResponse<Loan>(e.getMessage(), 500, null);
		}
	}
	
	@GET
	@Path("/get/rate/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Float> retrieveInterestRate (@PathParam("id") int id) throws Exception {
		try {
			Float rate = lbo.getInterestRate(id);
			return new ServiceResponse<Float>("record found", 200, rate);
		} catch (Exception e) {
			return new ServiceResponse<Float>(e.getMessage(), 500, null);
		}
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> addLoan(Loan l) throws Exception {
		try {
			Boolean flag = lbo.add(l);
			return new ServiceResponse<Boolean>("record added", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}
	
	@POST
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> deleteLoan(@PathParam("id") int id) throws Exception {
		try {
			Boolean flag = lbo.remove(id);
			return new ServiceResponse<Boolean>("record deleted", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}
	
	@POST
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> updateLoan(@PathParam("id") int id, Loan l) throws Exception {
		try {
			Boolean flag = lbo.modify(id, l);
			return new ServiceResponse<Boolean>("record updated", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}

}
