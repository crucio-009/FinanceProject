package com.java.financeprojectapp.servicelayer.services;

import java.util.List;

import com.java.financeprojectapp.businesslayer.implementations.CustomerBusinessComponent;
import com.java.financeprojectapp.entities.Customer;
import com.java.financeprojectapp.servicelayer.entities.Role;
import com.java.financeprojectapp.servicelayer.entities.Secured;
import com.java.financeprojectapp.servicelayer.entities.ServiceResponse;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/customers")
public class CustomerService {

	public CustomerBusinessComponent cbo = new CustomerBusinessComponent();

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<List<Customer>> retrieveAllCustomers() {
		try {
			List<Customer> list = cbo.getAll();
			return new ServiceResponse<List<Customer>>("records found", 200, list);
		} catch (Exception e) {
			return new ServiceResponse<List<Customer>>(e.getMessage(), 500, null);
		}
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Customer> retrieveCustomerById(@PathParam("id") String id) throws Exception {
		try {
			Customer c = cbo.getById(id);
			return new ServiceResponse<Customer>("record found", 200, c);
		} catch (Exception e) {
			return new ServiceResponse<Customer>(e.getMessage(), 500, null);
		}
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Customer> retrieveCustomerByEmail(@QueryParam("email") String email) throws Exception {
		try {
			Customer c = cbo.searchCustomerByEmail(email);
			return new ServiceResponse<Customer>("record found", 200, c);
		} catch (Exception e) {
			return new ServiceResponse<Customer>(e.getMessage(), 500, null);
		}
	}

	@POST
	@Secured({Role.CLERK})
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> addCustomer(Customer c) throws Exception {
		try {
			Boolean flag = cbo.add(c);
			return new ServiceResponse<Boolean>("record added", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}

	@POST
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> deleteCustomer(@PathParam("id") String id) throws Exception {
		try {
			Boolean flag = cbo.remove(id);
			return new ServiceResponse<Boolean>("record deleted", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}

	@POST
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public ServiceResponse<Boolean> updateCustomer(@PathParam("id") String id, Customer c) throws Exception {
		try {
			Boolean flag = cbo.modify(id, c);
			return new ServiceResponse<Boolean>("record updated", 200, flag);
		} catch (Exception e) {
			return new ServiceResponse<Boolean>(e.getMessage(), 500, null);
		}
	}
}
