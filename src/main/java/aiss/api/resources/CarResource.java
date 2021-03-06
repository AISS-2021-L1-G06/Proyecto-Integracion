package aiss.api.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;

import aiss.model.Car;
import aiss.model.repository.CarDealershipRepository;
import aiss.model.repository.MapCarDealershipRepository;
import comparator.ComparatorNameCarDealership;
import comparator.ComparatorNameCarDealershipReversed;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;


import javassist.NotFoundException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



@Path("/cars")
public class CarResource {

	public static CarResource _instance=null;
	CarDealershipRepository repository;
	
	private CarResource(){
		repository=MapCarDealershipRepository.getInstance();
	}
	
	public static CarResource getInstance()
	{
		if(_instance==null)
			_instance=new CarResource();
		return _instance; 
	}
	
	/*@GET
	@Produces("application/json")
	public Collection<Car> getAll()
	{
		return repository.getAllCars();
	}*/
	
	//OBTIENE TODOS LOS COCHES
		@GET
		@Produces("application/json")
		public Collection<Car> getAll
		(@QueryParam("order") String order,
				@QueryParam("isEmpty")Boolean isEmpty,
				@QueryParam("brand")String brand,
				@QueryParam("model")String model,
				@QueryParam("licensePlate")String licensePlate ,
				@QueryParam("year")String year, 
				@QueryParam("colour")String colour,
				@QueryParam("limit")Integer limit,
				@QueryParam("offset")Integer offset) {

			List<Car> result = new ArrayList<Car>();
			for(Car car: repository.getAllCars()) {

				if((model==null || model.equals(car.getModel())) &&	
						(brand==null || brand.equals(car.getBrand())) &&		
						(year==null || year.equals(car.getYear()))&&		
						(licensePlate==null || licensePlate.equals(car.getLicensePlate())) &&
						(colour == null || colour.equals(car.getColour()))) {

					result.add(car);


				}
				
				if(offset!=null && offset>0) {
					for (int i= offset; i< result.size(); i++) {
						Car c = result.get(i);
						result.removeAll(result);
						result.add(c);
						
					}


				}

				if(limit!=null) {
					result = result.stream().limit(limit).collect(Collectors.toList());
				}




				/*if(order != null) {
					if(order.equals("brand")) {
						Collections.sort(result, new ComparatorNameCarDealership());
					}else if(order.equals("-name")) {
						Collections.sort(result, new ComparatorNameCarDealershipReversed());
					}else {
						throw new BadRequestException("The order parameter must be brand or -brand");
					}
				}*/

			}
			return result;
		}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Car get(@PathParam("id") String carId) throws NotFoundException
	{
		Car s = repository.getCar(carId);
		
		if(s == null) {
			throw new NotFoundException("The car with id="+ carId +" was not found");
		}
		
		return s;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addCar(@Context UriInfo uriInfo, Car car) {
		
		if(car.getModel()==null || "".equals(car.getModel())) {
			throw new BadRequestException("El nombre del coche no puede ser null");
		}
		
		repository.addCar(car);
		
		//Builds the response. Returns the car that has been added.
		ResponseBuilder resp = null;
		try {
			resp = Response.created(new URI("/cars/"+car.getId()));
		}catch(URISyntaxException e){
			e.printStackTrace();
		}
		resp.entity(car);
		return resp.build();
		
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updateCar(Car car) throws NotFoundException {
		
		Car oldCar = repository.getCar(car.getId());
		
		if(oldCar==null) {
			throw new NotFoundException("The car with id="+car.getId()+" was not found");
		}
		if(car.getModel()!=null) {
			oldCar.setModel(car.getModel());
		}
		if(car.getBrand()!=null) {
			oldCar.setBrand(car.getBrand());
		}
		if(car.getColour()!=null) {
			oldCar.setColour(car.getColour());
		}
		if(car.getLicensePlate()!=null) {
			oldCar.setLicensePlate(car.getLicensePlate());
		}
		if(car.getYear()!=null) {
			oldCar.setYear(car.getYear());
		}
		if(car.getId()!=null) {
			oldCar.setId(car.getId());
		}
		
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response removeCar(@PathParam("id") String carId) throws NotFoundException {
		
		Car toberemoved=repository.getCar(carId);
		if(toberemoved == null) {
			throw new NotFoundException("The car with id="+carId+" was not found");
		}
		else {
			repository.deleteCar(carId);
		}
		return Response.noContent().build();
	}
	
}
