package com.creditunion.services;

import com.creditunion.entities.Student;
import com.creditunion.entities.Loan;
import com.creditunion.entities.Deposit;
import com.creditunion.services.StudentService;
import com.creditunion.services.LoanService;
import com.creditunion.services.DepositService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/creditunion")
public class CreditUnionService {

    private final StudentService studentService = new StudentService();
    private final LoanService loanService = new LoanService();
    private final DepositService depositService = new DepositService();

    // Student 
    
    @POST
    @Path("/students")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
        studentService.createStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @GET
    @Path("/students/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") int id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(student).build();
    }

    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PUT
    @Path("/students/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") int id, Student student) {
        student.setId(id); 
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedStudent).build();
    }

    @DELETE
    @Path("/students/{id}")
    public Response deleteStudent(@PathParam("id") int id) {
        boolean deleted = studentService.deleteStudent(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    // Loan 

    @POST
    @Path("/loans")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLoan(Loan loan) {
        loanService.createLoan(loan);
        return Response.status(Response.Status.CREATED).entity(loan).build();
    }

    @GET
    @Path("/loans/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoanById(@PathParam("id") int id) {
        Loan loan = loanService.getLoanById(id);
        if (loan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(loan).build();
    }

    @GET
    @Path("/loans")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PUT
    @Path("/loans/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLoan(@PathParam("id") int id, Loan loan) {
        loan.setId(id);
        Loan updatedLoan = loanService.updateLoan(loan);
        if (updatedLoan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedLoan).build();
    }

    @DELETE
    @Path("/loans/{id}")
    public Response deleteLoan(@PathParam("id") int id) {
        boolean deleted = loanService.deleteLoan(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    // Deposit

    @POST
    @Path("/loans/{loanId}/deposits")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDeposit(@PathParam("loanId") int loanId, Deposit deposit) {
        Loan loan = loanService.getLoanById(loanId);
        if (loan == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Loan not found").build();
        }
        deposit.setLoan(loan); // Associate the deposit with the loan
        depositService.createDeposit(deposit);
        return Response.status(Response.Status.CREATED).entity(deposit).build();
    }

    @GET
    @Path("/deposits/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepositById(@PathParam("id") int id) {
        Deposit deposit = depositService.getDepositById(id);
        if (deposit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(deposit).build();
    }

    @GET
    @Path("/deposits")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Deposit> getAllDeposits() {
        return depositService.getAllDeposits();
    }

    @PUT
    @Path("/deposits/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDeposit(@PathParam("id") int id, Deposit deposit) {
        deposit.setId(id);
        Deposit updatedDeposit = depositService.updateDeposit(deposit);
        if (updatedDeposit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedDeposit).build();
    }

    @DELETE
    @Path("/deposits/{id}")
    public Response deleteDeposit(@PathParam("id") int id) {
        boolean deleted = depositService.deleteDeposit(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
